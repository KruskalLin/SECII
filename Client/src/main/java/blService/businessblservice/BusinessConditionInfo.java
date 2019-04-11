package blService.businessblservice;

import po.BusinessConditionPO;

import java.rmi.RemoteException;

public interface BusinessConditionInfo {
    void insert(BusinessConditionPO businessConditionPO) throws RemoteException;
}
