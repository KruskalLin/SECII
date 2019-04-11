package businesslogic.inventorybl;

import blService.businessblservice.BusinessSearchInfo;
import blService.inventoryblService.InventoryViewblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.goodsbl.Goodsbl;
import ui.salesui.ViewInfo;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockPurReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryViewbl implements InventoryViewblService {
    private Goodsbl goodsbl;

    public InventoryViewbl() throws RemoteException, NotBoundException, MalformedURLException {
        goodsbl = new Goodsbl();
    }

    @Override
    public InventoryViewVO inventoryView(LocalDate beginDate, LocalDate endDate) throws Exception {
        ViewInfo viewInfo = new ViewInfo();

        List<InventoryViewItemVO> list = viewInfo.view(LocalDateTime.of(beginDate,LocalTime.MIN),LocalDateTime.of(endDate,LocalTime.MIN));

        int totalSum = 0;

        for (InventoryViewItemVO vo:list) {
            String goodId = vo.getGoodId();
            GoodsVO goodsVO = goodsbl.showDetail(goodId);
            totalSum += goodsVO.getInventoryNum();
        }

        InventoryViewVO inventoryViewVO = new InventoryViewVO();

        inventoryViewVO.setViewList(new HashSet<>(list));

        inventoryViewVO.setTotalNum(totalSum);

        return inventoryViewVO;
    }
}
