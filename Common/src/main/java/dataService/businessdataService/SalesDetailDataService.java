package dataService.businessdataService;

import po.SalesDetailPO;
import util.SalesDetailInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SalesDetailDataService extends Remote{

    public List<SalesDetailPO> select(SalesDetailInfo salesDetailInfo)throws RemoteException;
}
