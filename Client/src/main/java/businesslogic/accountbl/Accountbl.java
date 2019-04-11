package businesslogic.accountbl;

import blService.accountblService.AccountInfo;
import blService.accountblService.AccountblService;
import dataService.accountDataService.AccountDataService;
import po.AccountPO;
import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Accountbl implements AccountblService,AccountInfo{

    AccountDataService accountDataService;



    public Accountbl()throws NotBoundException,RemoteException,MalformedURLException{
        accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:1099/AccountData");
    }

    public Set<AccountListVO> getAll()throws RemoteException{

        ArrayList<AccountPO> POList = accountDataService.getAll();
        ArrayList<AccountListVO> VOList = new ArrayList<>();
        for(AccountPO po: POList){
            AccountListVO vo = new AccountListVO(po.getID(),po.getName(),po.getBalance());
            VOList.add(vo);
        }
        return new HashSet<AccountListVO>(VOList);

    }


    public ResultMessage add(AccountListVO accountListVO)throws RemoteException{
        AccountPO accountPO = new AccountPO(null,accountListVO.getName(),accountListVO.getBalance());
        return accountDataService.insert(accountPO);
    }

    public ResultMessage delete(int ID)throws RemoteException{
        return accountDataService.delete(ID);
    }

    public ResultMessage update(AccountListVO accountVO)throws RemoteException{
        AccountPO accountPO = new AccountPO(accountVO.getID(),accountVO.getName(),accountVO.getBalance());
        return accountDataService.update(accountPO);
    }

    public void decBalance(int id,double total)throws RemoteException{
        ArrayList<AccountPO> POList = accountDataService.getAll();
        AccountPO temp = new AccountPO();
        for(AccountPO po:POList){
            if(po.getID()==id){
                temp = new AccountPO(id,po.getName(),po.getBalance()-total);
            }
        }
        accountDataService.update(temp);
    }

    public void incBalance(int id,double total)throws RemoteException{
        ArrayList<AccountPO> POList = accountDataService.getAll();
        AccountPO temp = new AccountPO();
        for(AccountPO po:POList){
            if(po.getID()==id){
                temp = new AccountPO(id,po.getName(),po.getBalance()+total);
            }
        }
        accountDataService.update(temp);
    }



}
