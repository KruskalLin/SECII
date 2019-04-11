package blService.businessblservice;

import util.ReceiptSearchCondition;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BusinessProgressblService {
    ArrayList<ReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
}
