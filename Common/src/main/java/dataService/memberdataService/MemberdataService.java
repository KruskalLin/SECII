package dataService.memberdataService;

import po.MemberPO;
import util.MemberSearchCondition;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface MemberdataService  extends Remote {
    public MemberPO getNew() throws RemoteException;
    public ResultMessage delete(int id) throws RemoteException;
    public ResultMessage update(MemberPO memberPO) throws RemoteException;
    public MemberPO showDetail(int id) throws RemoteException;
    public ArrayList<MemberPO> search(MemberSearchCondition memberSearchCondition) throws RemoteException;



}