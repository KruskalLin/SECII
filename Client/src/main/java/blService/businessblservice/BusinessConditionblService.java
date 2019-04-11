package blService.businessblservice;

import po.BusinessConditionPO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface BusinessConditionblService {
    BusinessConditionPO search(LocalDateTime begin, LocalDateTime end) throws RemoteException;
}
