package businesslogic.billbl;

import blService.billblservice.CashBillReceiptblService;
import businesslogic.accountbl.Accountbl;
import businesslogic.genericbl.Receiptbl;
import po.receiptPO.CashBillReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.CashReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class CashBillReceiptbl extends Receiptbl<CashReceiptVO,CashBillReceiptPO> implements CashBillReceiptblService {

    Accountbl accountbl;
    public CashBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(CashReceiptVO.class, CashBillReceiptPO.class);
        accountbl = new Accountbl();
    }

//    @Override
//    public ResultMessage approve(CashReceiptVO receiptVO) throws RemoteException{
//        List<CashItemVO> list = receiptVO.getCashList();
//        double sum =0;
//        int id = receiptVO.getAccountID();
//        for(CashItemVO vo:list){
//            sum+=vo.getPrice();
//        }
//
//        accountbl.decBalance(id,sum);
//        receiptVO.setReceiptState(ReceiptState.APPROVED);
//        update(receiptVO);
//
//        return ResultMessage.SUCCESS;
//    }

    @Override
    protected void updateInfoAfterApproval(CashReceiptVO receiptVO) throws RemoteException {
        List<CashItemVO> list = receiptVO.getCashList();
        double sum =0;
        int id = receiptVO.getAccountID();
        for(CashItemVO vo:list){
            sum+=vo.getPrice();
        }

        accountbl.decBalance(id,sum);
    }
}
