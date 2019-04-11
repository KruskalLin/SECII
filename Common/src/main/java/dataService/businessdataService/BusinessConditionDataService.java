package dataService.businessdataService;

import po.BusinessConditionPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface BusinessConditionDataService extends Remote {
    void insert(BusinessConditionPO businessConditionPO) throws RemoteException;
    ArrayList<BusinessConditionPO> select(LocalDateTime begin, LocalDateTime end) throws RemoteException;
}
