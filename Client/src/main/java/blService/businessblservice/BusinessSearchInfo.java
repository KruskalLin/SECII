package blService.businessblservice;

import util.ReceiptSearchCondition;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BusinessSearchInfo<TV extends ReceiptVO> {
    ArrayList<TV> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
}
