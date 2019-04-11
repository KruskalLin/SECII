package blService.logblService;
import po.LogPO;
import po.ReceiptGoodsItemPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.*;
public interface LogblService {
    public void insert(LogPO logPO) throws RemoteException;
    public ArrayList<LogPO> getList() throws RemoteException;
}
