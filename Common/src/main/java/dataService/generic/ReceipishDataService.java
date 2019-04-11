package dataService.generic;

import po.generic.ReceipishPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReceipishDataService<T extends ReceipishPO> extends Remote {
    T getNew() throws RemoteException;
    ResultMessage insert(T receipishPO) throws RemoteException;
    ResultMessage update(T receipishPO) throws RemoteException;
    ResultMessage delete(T receipishPO) throws RemoteException;
    T selectByMold(T receipishPO) throws RemoteException;
}
