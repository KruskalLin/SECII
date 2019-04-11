package dataService.userdataService;

import com.sun.org.apache.regexp.internal.RE;
import po.UserPO;
import util.ResultMessage;
import util.UserCategory;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginDataService extends Remote{
    public ResultMessage login(String username,String password) throws RemoteException;
    public UserPO getCategory(String username) throws RemoteException;
    public ResultMessage update(UserPO userPO) throws RemoteException;
}
