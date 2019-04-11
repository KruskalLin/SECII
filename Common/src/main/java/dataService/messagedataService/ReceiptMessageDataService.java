package dataService.messagedataService;

import po.ReceiptMessagePO;
import util.UserCategory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReceiptMessageDataService extends Remote {
    void insert(ReceiptMessagePO messagePO) throws RemoteException;

    ArrayList<ReceiptMessagePO> selectByUser(int userId) throws RemoteException;

    ArrayList<ReceiptMessagePO> selectByUserCategory(UserCategory userCategory) throws RemoteException;

    ReceiptMessagePO selectByMold(ReceiptMessagePO messagePO) throws RemoteException;

    void delete(ReceiptMessagePO messagePO) throws RemoteException;
}
