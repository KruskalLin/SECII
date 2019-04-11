package businesslogic.userbl;

import blService.userblService.UserManagerblService;
import dataService.userdataService.UserDataService;
import po.UserPO;
import util.ResultMessage;
import util.UserSearchCondition;
import vo.UserListVO;
import vo.UserVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static ui.util.UserInfomation.url;

public class Userbl implements UserManagerblService {
    private UserDataService userDataService;

    public Userbl() throws RemoteException, MalformedURLException, NotBoundException {
        userDataService = (UserDataService) Naming.lookup( url+"UserData");
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException{
        return userDataService.delete(id);
    }

    @Override
    public ResultMessage update(UserVO UserVO) throws RemoteException{
        UserPO userPO = UserVO.toPO();
        return userDataService.update(userPO);
    }

    @Override
    public ArrayList<UserListVO> search(UserSearchCondition userSearchCondition) throws RemoteException{
        ArrayList<UserPO> arrayList = userDataService.search(userSearchCondition);
        System.out.println(arrayList);
        return arrayList.stream().map(t->{return (new UserVO(t)).toListVO();}).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UserVO showDetail(int id) throws RemoteException{
        return new UserVO(userDataService.showDetail(id));
    }

    @Override
    public UserVO getNew() throws RemoteException{
        return new UserVO(userDataService.getNew());
    }
}
