package businesslogic.inventorybl;

import blService.inventoryblService.InventoryWarningReceiptblService;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import blService.goodsblService.GoodsSalesUpdateInfo;
import po.receiptPO.InventoryWarningReceiptPO;
import util.ResultMessage;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class InventoryWarningReceiptbl extends Receiptbl<InventoryWarningReceiptVO,InventoryWarningReceiptPO> implements InventoryWarningReceiptblService{
    GoodsSalesUpdateInfo info;

    public InventoryWarningReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryWarningReceiptVO.class,InventoryWarningReceiptPO.class);
        info = new GoodsSalesUpdate();
    }


//    @Override
//    public ResultMessage approve(InventoryWarningReceiptVO receiptVO) throws RemoteException {
//        /*InventoryWarningReceiptPO receiptPO = receiptVO.toPO();
//        receiptPO.setReceiptState(ReceiptState.APPROVED);
//        receiptDataService.update(receiptPO);
//
//        return ResultMessage.SUCCESS;
//        return*/
//        return ResultMessage.SUCCESS;
//
//    }

    @Override
    protected void updateInfoAfterApproval(InventoryWarningReceiptVO receiptVO) throws RemoteException {
    }
}
