package blService.memberblService;

import util.ResultMessage;
import vo.MemberChooseVO;
import vo.MemberVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public interface MemberInfo extends Remote{

    public ResultMessage updateDebt(int memberId,double debt) throws RemoteException;
    public ResultMessage updateCredit(int memberId,double credit) throws RemoteException;
    public ArrayList<MemberVO> getALL() throws RemoteException;
    public MemberVO search(int id) throws RemoteException;
}
