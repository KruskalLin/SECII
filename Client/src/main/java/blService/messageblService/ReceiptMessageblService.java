package blService.messageblService;

import po.ReceiptMessagePO;
import util.UserCategory;
import vo.ReceiptMessageVO;
import vo.UserVO;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReceiptMessageblService {
    void insert(ReceiptMessagePO messagePO) throws RemoteException;

    ArrayList<ReceiptMessageVO> selectByUser() throws RemoteException;

    void delete(ReceiptMessagePO messagePO) throws RemoteException;

    void deleteByReceipt(ReceiptVO receiptVO) throws RemoteException;
}
