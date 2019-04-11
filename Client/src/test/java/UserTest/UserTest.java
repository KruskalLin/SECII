package UserTest;

import blService.userblService.UserManagerblService;
import dataService.userdataService.UserDataService;
import org.junit.Before;
import org.junit.Test;
import po.MemberPO;
import po.UserPO;
import util.UserCategory;
import util.UserSearchCondition;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static ui.util.UserInfomation.url;

public class UserTest {

    UserDataService userDataService;

    @Before
    public void setUp() throws Exception {
        userDataService = (UserDataService) Naming.lookup(url + "UserData");
    }

    @Test
    public void testInAndSee() throws Exception {
        UserPO userPO = userDataService.getNew();
        int id = userPO.getUserId();
        userPO.setUsername("lin");
        userPO.setComment("this is a da bao jian");
        userPO.setPassword("aaaa");
        userPO.setUsertype(UserCategory.GeneralManager);
        userDataService.update(userPO);
        UserPO user = userDataService.showDetail(id);

        assertEquals(user.getPassword(), userPO.getPassword());


    }

    //list.size>2
    @Test
    public void ListCheck() throws Exception {
        ArrayList<UserPO> list = userDataService.search(new UserSearchCondition());
        int id = 0;
        if (!list.isEmpty()) {
            id = list.get(1).getUserId();
            userDataService.delete(list.get(0).getUserId());
        }
        list = userDataService.search(new UserSearchCondition());
        assertEquals(id, list.get(0).getUserId());
    }

}

