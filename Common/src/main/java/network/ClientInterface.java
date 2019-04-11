package network;

import po.ReceiptMessagePO;
import util.UserCategory;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    void receive(ReceiptMessagePO messagePO) throws RemoteException;

    int getUserId() throws RemoteException;
    UserCategory getUserCategory() throws RemoteException;
}
