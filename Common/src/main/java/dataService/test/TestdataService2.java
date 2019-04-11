package dataService.test;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface TestdataService2 extends Remote{
    public String test() throws RemoteException;
}
