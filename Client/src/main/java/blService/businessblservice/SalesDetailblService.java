package blService.businessblservice;

import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SalesDetailblService {
    ArrayList<ListGoodsItemVO> searchSalesDetail(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
}
