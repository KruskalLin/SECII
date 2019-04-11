package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import po.BusinessConditionPO;
import po.receiptPO.InventoryOverflowReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryOverflowReceiptbl extends Receiptbl<InventoryOverflowReceiptVO,InventoryOverflowReceiptPO> implements InventoryOverflowReceiptblService {
    GoodsInventoryUpdateInfo info;
    Goodsbl goodsbl;
    BusinessConditionbl businessConditionbl;

    public InventoryOverflowReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryOverflowReceiptVO.class,InventoryOverflowReceiptPO.class);
        info = new GoodsInventoryUpdate();
        goodsbl = new Goodsbl();
        businessConditionbl = new BusinessConditionbl();
    }




//    @Override
//    public ResultMessage approve(InventoryOverflowReceiptVO receiptVO) throws RemoteException {
//        receiptVO.setReceiptState(ReceiptState.APPROVED);
//
//        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();
//
//        info.goodsDamageUpdate(list);
//
//        BusinessConditionPO businessConditionPO = new BusinessConditionPO();
//        double overFlowIncome = getOverFlowCost(receiptVO.getItems());
//        businessConditionPO.setOverFlowIncome(overFlowIncome);
//
//        businessConditionbl.insert(businessConditionPO);
//
//        return ResultMessage.SUCCESS;
//    }

    public double getOverFlowCost(List<ReceiptGoodsItemVO> list) throws RemoteException{
        double sum = 0;

        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            double overflowSum = vo.getFactNum()-vo.getInventoryNum();
            sum += (goodsVO.getPurPrice()*overflowSum);
        }



        return sum;
    }

    @Override
    protected void updateInfoAfterApproval(InventoryOverflowReceiptVO receiptVO) throws RemoteException {
        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();

        info.goodsDamageUpdate(list);

        BusinessConditionPO businessConditionPO = new BusinessConditionPO();
        double overFlowIncome = getOverFlowCost(receiptVO.getItems());
        businessConditionPO.setOverFlowIncome(overFlowIncome);
        businessConditionPO.setDate(receiptVO.getLastModifiedTime());

        businessConditionbl.insert(businessConditionPO);
    }
}
