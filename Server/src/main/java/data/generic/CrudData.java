package data.generic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CrudData<T> extends UnicastRemoteObject {

    protected CrudData() throws RemoteException {
    }
}
