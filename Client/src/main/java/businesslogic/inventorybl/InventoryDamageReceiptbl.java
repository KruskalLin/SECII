package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryDamageReceiptblService;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import po.BusinessConditionPO;
import po.receiptPO.InventoryDamageReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryDamageReceiptbl extends Receiptbl<InventoryDamageReceiptVO,InventoryDamageReceiptPO> implements InventoryDamageReceiptblService{
    GoodsInventoryUpdateInfo info;
    Goodsbl goodsbl;
    BusinessConditionbl businessConditionbl;

    public InventoryDamageReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryDamageReceiptVO.class, InventoryDamageReceiptPO.class);
        info = new GoodsInventoryUpdate();
        businessConditionbl = new BusinessConditionbl();
        goodsbl = new Goodsbl();
    }


//    @Override
//    public ResultMessage approve(InventoryDamageReceiptVO receiptVO) throws RemoteException {
//        receiptVO.setReceiptState(ReceiptState.APPROVED);
//
//        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();
//
//        info.goodsDamageUpdate(list);
//
//        BusinessConditionPO businessConditionPO = new BusinessConditionPO();
//
//        double damageCost = getDamageCost(receiptVO.getItems());
//
//        businessConditionPO.setDamageCost(damageCost);
//
//        businessConditionbl.insert(businessConditionPO);
//
//        return ResultMessage.SUCCESS;
//
//    }

    public double getDamageCost(List<ReceiptGoodsItemVO> list) throws RemoteException{
        double sum = 0;

        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            int damageSum = vo.getInventoryNum()-vo.getFactNum();
            sum += (goodsVO.getPurPrice()*damageSum);
        }



        return sum;
    }

    @Override
    protected void updateInfoAfterApproval(InventoryDamageReceiptVO receiptVO) throws RemoteException {
        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();
        info.goodsDamageUpdate(list);

        BusinessConditionPO businessConditionPO = new BusinessConditionPO();
        double damageCost = getDamageCost(receiptVO.getItems());
        businessConditionPO.setDamageCost(damageCost);
        businessConditionPO.setDate(receiptVO.getLastModifiedTime());

        businessConditionbl.insert(businessConditionPO);
    }
}
