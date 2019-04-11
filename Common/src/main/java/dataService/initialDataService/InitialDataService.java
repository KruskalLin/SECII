package dataService.initialDataService;

import po.AccountInitialPO.InitialAccountPO;
import po.AccountInitialPO.InitialGoodsPO;
import po.AccountInitialPO.InitialMemberPO;
import po.AccountPO;
import po.GoodsClassificationPO;
import po.GoodsPO;
import po.MemberPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InitialDataService extends Remote {

    public void initial(String year)throws RemoteException;

    public ArrayList<InitialAccountPO> showAccount(String year)throws RemoteException;

    public ArrayList<InitialMemberPO> showMember(String year)throws RemoteException;



    public ArrayList<InitialGoodsPO> showGoods(String year)throws RemoteException;


}
