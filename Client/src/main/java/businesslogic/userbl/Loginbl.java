package businesslogic.userbl;

import blService.userblService.LoginblService;
import blService.userblService.UserInfo;
import dataService.userdataService.LoginDataService;
import dataService.userdataService.UserDataService;
import po.UserPO;
import ui.util.UserInfomation;
import util.ResultMessage;
import util.UserCategory;
import vo.UserVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static ui.util.UserInfomation.url;

public class Loginbl implements LoginblService{

    private LoginDataService loginDataService;

    public Loginbl() throws RemoteException, NotBoundException, MalformedURLException {
        loginDataService = (LoginDataService) Naming.lookup( url+"LoginData");
        System.out.print(loginDataService);
    }

    @Override
    public ResultMessage login(String id, String password) throws RemoteException{
        return loginDataService.login(id,password);
    }

    @Override
    public UserVO getCategory(String username)throws RemoteException {
        UserVO userVO = new UserVO(loginDataService.getCategory(username));
        UserInfomation.userVO = userVO;
        return userVO;
    }

    @Override
    public ResultMessage update(UserVO userVO) throws RemoteException {
        loginDataService.update(userVO.toPO());
        return ResultMessage.SUCCESS;
    }



}
