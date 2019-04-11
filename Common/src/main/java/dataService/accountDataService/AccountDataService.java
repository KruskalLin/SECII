package dataService.accountDataService;

import po.AccountPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface AccountDataService extends Remote{

    public int getID (String name)throws RemoteException;

    public AccountPO getAccountByName(String name)throws RemoteException;

    public ResultMessage insert(AccountPO accountPO)throws RemoteException;

    public ResultMessage delete(int ID)throws RemoteException;

    public ResultMessage update(AccountPO accountPO)throws RemoteException;

    public ArrayList<AccountPO> selectInEffect(String keyword)throws RemoteException;

    public ArrayList<AccountPO> getAll()throws RemoteException;
}
