package dataService.test;

import com.sun.org.apache.regexp.internal.RE;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestdataService1 extends Remote{
    public String test() throws RemoteException;
}
