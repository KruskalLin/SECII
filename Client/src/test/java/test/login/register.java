package test.login;

import blService.userblService.UserManagerblService;
import businesslogic.userbl.Loginbl;
import businesslogic.userbl.Userbl;
import util.UserCategory;
import vo.UserVO;

public class register {

    public static void main(String args[]){
        try {
            UserManagerblService userManagerblService = new Userbl();
            UserVO userVO = userManagerblService.getNew();
            userVO.setUsername("admin");
            userVO.setUsertype(UserCategory.UserManager);
            userVO.setPassword("admin");
            userManagerblService.update(userVO);

            UserVO userVO1 = userManagerblService.getNew();
            userVO1.setUsername("admin1");
            userVO1.setUsertype(UserCategory.Salesman);
            userVO1.setPassword("admin1");
            userManagerblService.update(userVO1);


            UserVO userVO2 = userManagerblService.getNew();
            userVO2.setUsername("admin2");
            userVO2.setUsertype(UserCategory.GeneralManager);
            userVO2.setPassword("admin2");
            userManagerblService.update(userVO2);

            UserVO userVO3 = userManagerblService.getNew();
            userVO3.setUsername("admin3");
            userVO3.setUsertype(UserCategory.Accountant);
            userVO3.setPassword("admin3");
            userManagerblService.update(userVO3);

            UserVO userVO4 = userManagerblService.getNew();
            userVO4.setUsername("admin4");
            userVO4.setUsertype(UserCategory.InventoryManager);
            userVO4.setPassword("admin4");
            userManagerblService.update(userVO4);

        }catch (Exception e) {
            e .printStackTrace();
        }
    }
}
