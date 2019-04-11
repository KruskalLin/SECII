package businesslogic.inventorybl.inventoryInfo;

import businesslogic.inventorybl.InventoryGiftReceiptbl;
import po.GoodsPO;
import po.ReceiptGoodsItemPO;
import ui.util.UserInfomation;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GiftReceiptInfoImpl implements InventoryGiftReceiptInfo {
    InventoryGiftReceiptbl giftReceiptbl;


    public GiftReceiptInfoImpl() throws RemoteException, NotBoundException, MalformedURLException {
        giftReceiptbl = new InventoryGiftReceiptbl();

    }

    @Override
    public void addInventoryGiftReceipt(List<PromotionGoodsItemVO> goodsList) throws RemoteException {
        List<ReceiptGoodsItemVO> list = new ArrayList<>();
        for (PromotionGoodsItemVO vo: goodsList) {
            ReceiptGoodsItemVO receiptVO = new ReceiptGoodsItemVO();
            receiptVO.setGoodsId(vo.getId());
            receiptVO.setGoodsName(vo.getName());
            receiptVO.setSendNum(vo.getNum());

            list.add(receiptVO);
        }

        InventoryGiftReceiptVO receiptVO = giftReceiptbl.getNew();
        receiptVO.setItems(list);
        receiptVO.setOperatorId(UserInfomation.userid);
        receiptVO.setReceiptState(ReceiptState.PENDING);
        giftReceiptbl.update(receiptVO);
    }

    /*
    构造赠送单的时候那些数据还需要再考虑考虑,赠送数量的接口还需要商量
     */
   /* @Override
    public void addInventoryGiftReceipt(List<GoodsPO> goodsList) throws RemoteException {
        List<InventroyGiftGoodsItemVO> list = new ArrayList<>(goodsList.size());

        for (GoodsPO po:goodsList) {
            list.add(new InventroyGiftGoodsItemVO(po.getId(),po.getGoodName(),po.getGoodType(),po.getInventoryNum(),0));
        }

       *//* InventoryGiftReceiptVO receiptVO = new InventoryGiftReceiptVO(null,0,null,null,
                ReceiptState.PENDING,list);
*//*
      //  giftReceiptbl.insert(receiptVO);
    }*/
}
