package dataService.logdataService;

import po.LogPO;
import po.MessagePO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface LogdataService extends Remote{
    public ResultMessage insert(LogPO logPO) throws RemoteException;
    public ArrayList<LogPO> getAll() throws RemoteException;

}
