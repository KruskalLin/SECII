package blService.inventoryblService;

import vo.inventoryVO.InventoryCheckVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface InventoryCheckblService {
    /**
     * 盘点的是当天的库存快照
     */
    public InventoryCheckVO inventoryCheck() throws RemoteException, NotBoundException, MalformedURLException;
}
