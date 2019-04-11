package businesslogic.inventorybl;

import blService.businessblservice.BusinessSearchInfo;
import blService.inventoryblService.InventoryCheckblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.goodsbl.Goodsbl;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryCheckbl implements InventoryCheckblService{
    Goodsbl goodsbl;
    public InventoryCheckbl() throws RemoteException, NotBoundException, MalformedURLException {
        goodsbl = new Goodsbl();
    }

    @Override
    public InventoryCheckVO inventoryCheck() throws RemoteException, NotBoundException, MalformedURLException {
        //销售单
        BusinessSearchInfo<SalesSellReceiptVO> salesReceiptVOBusinessSearchInfo = MyServiceFactory.getSalesSellSearchInfo();

        ReceiptSearchCondition receiptSearchCondition = new ReceiptSearchCondition();
        receiptSearchCondition.setBegin(LocalDateTime.now().plusDays(-1));
        receiptSearchCondition.setEnd(LocalDateTime.now());

        List<SalesSellReceiptVO> salesSellReceiptVOList = salesReceiptVOBusinessSearchInfo.search(receiptSearchCondition);

        InventoryCheckVO inventoryCheckVO = new InventoryCheckVO();
        Set<InventoryCheckItemVO> set = new HashSet<>();

        //销售数量金额
        for (SalesSellReceiptVO sellReceiptVO:salesSellReceiptVOList) {
            List<ListGoodsItemVO> goodsList = sellReceiptVO.getItems();
            for (int i = 0; i < goodsList.size(); i++) {
                ListGoodsItemVO goodsItemVO = goodsList.get(i);

                InventoryCheckItemVO itemVO = new InventoryCheckItemVO();

                itemVO.setGoodId(goodsItemVO.getGoodsId());
                GoodsVO goodsVO = goodsbl.showDetail(goodsItemVO.getGoodsId());
                itemVO.setGoodName(goodsVO.getGoodName());
                itemVO.setAvePrice(goodsItemVO.getPrice());
                itemVO.setInventoryNum(goodsItemVO.getGoodsNum());
                itemVO.setStockOutDate(sellReceiptVO.getLastModifiedTime().toLocalDate().toString());
                String receiptId = sellReceiptVO.getId();
                itemVO.setBatch(receiptId.split("-")[2]);
                itemVO.setBatchinventoryNum(goodsVO.getGoodType());

                int size = set.size();

                set.add(itemVO);
            }
        }

        inventoryCheckVO.setCheckList(set);
        return inventoryCheckVO;
    }
}
