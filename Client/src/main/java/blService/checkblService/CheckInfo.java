package blService.checkblService;

import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CheckInfo<TV extends ReceiptVO> {
    ResultMessage update(TV receiptVO) throws RemoteException;
    ResultMessage approve(TV receiptVO) throws RemoteException;
    ResultMessage reject(TV receiptVO) throws RemoteException;

    ArrayList<TV> selectPending() throws RemoteException;
}
