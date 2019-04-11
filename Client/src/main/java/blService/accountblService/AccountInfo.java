package blService.accountblService;

import java.rmi.RemoteException;

public interface AccountInfo {

    public void decBalance(int id,double total)throws RemoteException;

    public void incBalance(int id,double total)throws RemoteException;

}
