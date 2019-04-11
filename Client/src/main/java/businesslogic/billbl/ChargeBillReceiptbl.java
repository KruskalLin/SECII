package businesslogic.billbl;

import blService.billblservice.ChargeBillReceiptblService;
import blService.memberblService.MemberInfo;
import businesslogic.accountbl.Accountbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.memberbl.MemberInfo_Impl;
import po.receiptPO.ChargeBillReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ChargeBillReceiptbl extends Receiptbl<ChargeReceiptVO, ChargeBillReceiptPO> implements ChargeBillReceiptblService {

    MemberInfo memberInfo;
    Accountbl accountbl;

    public ChargeBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(ChargeReceiptVO.class, ChargeBillReceiptPO.class);
        memberInfo = new MemberInfo_Impl();
        accountbl = new Accountbl();
    }

//    @Override
//    public ResultMessage approve(ChargeReceiptVO receiptVO) throws RemoteException {
//
//        memberInfo.updateDebt(receiptVO.getClientID(), receiptVO.getSum());
//
//
//        List<TransferItemVO> temp = receiptVO.getTransferList();
//
//
//        for (int i = 0; i < temp.size(); i++) {
//            accountbl.incBalance(temp.get(i).getAccountID(), temp.get(i).getSum());
//        }
//        receiptVO.setReceiptState(ReceiptState.APPROVED);
//        update(receiptVO);
//
//        return ResultMessage.SUCCESS;
//    }

    @Override
    protected void updateInfoAfterApproval(ChargeReceiptVO receiptVO) throws RemoteException {
        memberInfo.updateDebt(receiptVO.getClientID(), receiptVO.getSum());


        List<TransferItemVO> temp = receiptVO.getTransferList();


        for (int i = 0; i < temp.size(); i++) {
            accountbl.incBalance(temp.get(i).getAccountID(), temp.get(i).getSum());
        }
    }
}
