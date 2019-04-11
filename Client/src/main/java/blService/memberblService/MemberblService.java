package blService.memberblService;

import util.MemberSearchCondition;
import util.ResultMessage;
import vo.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface MemberblService extends Remote {
    public MemberVO getNew() throws RemoteException;
    public ResultMessage delete(int id) throws RemoteException;
    public ResultMessage update(MemberVO MemberVO) throws RemoteException;
    public ArrayList<MemberListVO> search(MemberSearchCondition memberSearchCondition) throws RemoteException;
    public MemberVO showDetail(int id) throws RemoteException;


}
