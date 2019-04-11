package businesslogic.billbl;

import blService.billblservice.PaymentBillReceiptblService;
import blService.memberblService.MemberInfo;
import businesslogic.accountbl.Accountbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.memberbl.MemberInfo_Impl;
import po.receiptPO.PaymentBillReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class PaymentBillReceiptbl extends Receiptbl<PaymentReceiptVO,PaymentBillReceiptPO> implements PaymentBillReceiptblService {

    private MemberInfo memberInfo;
    private Accountbl accountbl;
    public PaymentBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(PaymentReceiptVO.class, PaymentBillReceiptPO.class);
        memberInfo = new MemberInfo_Impl();
        accountbl = new Accountbl();

    }

//    @Override
//    public ResultMessage approve(PaymentReceiptVO receiptVO) throws RemoteException {
//
//        memberInfo.updateCredit(receiptVO.getclientID(), receiptVO.getSum());
//
//        List<TransferItemVO> temp = receiptVO.getTransferList();
//        for (int i = 0; i < temp.size(); i++) {
//            accountbl.decBalance(temp.get(i).getAccountID(), temp.get(i).getSum());
//        }
//        receiptVO.setReceiptState(ReceiptState.APPROVED);
//        update(receiptVO);
//        return ResultMessage.SUCCESS;
//
//    }

    @Override
    protected void updateInfoAfterApproval(PaymentReceiptVO receiptVO) throws RemoteException {
        memberInfo.updateCredit(receiptVO.getclientID(), receiptVO.getSum());

        List<TransferItemVO> temp = receiptVO.getTransferList();
        for (int i = 0; i < temp.size(); i++) {
            accountbl.decBalance(temp.get(i).getAccountID(), temp.get(i).getSum());
        }
    }
}
