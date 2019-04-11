package blService.userblService;

import com.sun.org.apache.regexp.internal.RE;
import util.ResultMessage;
import util.UserCategory;
import vo.RegisterVO;
import vo.UserVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginblService extends Remote{
    public ResultMessage login(String id, String password) throws RemoteException;
    public UserVO getCategory(String username) throws RemoteException;
    public ResultMessage update(UserVO userVO) throws RemoteException;
}
