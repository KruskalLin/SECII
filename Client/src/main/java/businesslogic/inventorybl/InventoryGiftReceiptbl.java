package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryGiftReceiptblService;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import po.BusinessConditionPO;
import po.receiptPO.InventoryGiftReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryGiftReceiptbl extends Receiptbl<InventoryGiftReceiptVO, InventoryGiftReceiptPO> implements InventoryGiftReceiptblService {
    private GoodsInventoryUpdateInfo info;
    private Goodsbl goodsbl;
    private BusinessConditionbl businessConditionbl;

    public InventoryGiftReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryGiftReceiptVO.class, InventoryGiftReceiptPO.class);
        info = new GoodsInventoryUpdate();
        goodsbl = new Goodsbl();
        businessConditionbl = new BusinessConditionbl();
    }

//    @Override
//    public ResultMessage approve(InventoryGiftReceiptVO receiptVO) throws RemoteException {
//        receiptVO.setReceiptState(ReceiptState.APPROVED);
//
//        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();
//
//        info.goodsGiftUpdate(list);
//
//        BusinessConditionPO businessConditionPO = new BusinessConditionPO();
//        double giftIncome = getGiftCost(receiptVO.getItems());
//        businessConditionPO.setOverFlowIncome(giftIncome);
//
//        businessConditionbl.insert(businessConditionPO);
//
//
//        return ResultMessage.SUCCESS;
//    }

    public double getGiftCost(List<ReceiptGoodsItemVO> list) throws RemoteException {
        double sum = 0;

        for (ReceiptGoodsItemVO vo : list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            sum += (goodsVO.getPurPrice() * vo.getSendNum());
        }

        return sum;
    }

    @Override
    protected void updateInfoAfterApproval(InventoryGiftReceiptVO receiptVO) throws RemoteException {
        List<ReceiptGoodsItemVO> list = receiptVO.getItems();

        info.goodsGiftUpdate(list);

        BusinessConditionPO businessConditionPO = new BusinessConditionPO();
        double giftCost = getGiftCost(receiptVO.getItems());
        businessConditionPO.setGiftCost(giftCost);
        businessConditionPO.setDate(receiptVO.getLastModifiedTime());

        businessConditionbl.insert(businessConditionPO);
    }
}
