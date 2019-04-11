package blService.accountblService;

import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface AccountblService {


    public ResultMessage add(AccountListVO accountListVO)throws RemoteException;
    public ResultMessage delete(int id)throws RemoteException;
    public ResultMessage update(AccountListVO accountListVO)throws RemoteException;
    //public Set<MemberListVO> search(MemberSearchVO memberSearchVO);
    //public MemberVO showDetail(int id);
    public Set<AccountListVO> getAll()throws RemoteException;

}
