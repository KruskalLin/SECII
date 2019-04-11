package mapper;

import po.UserPO;
import util.ResultMessage;
import util.UserSearchCondition;

import java.util.ArrayList;

public interface UserDataPOMapper {

    public int getId();
    public void insert(UserPO UserPO);
    public void delete(int id);
    public void update(UserPO UserPO);
    public UserPO showDetail(int id);
    public UserPO getPassword(String username);
    public ArrayList<UserPO> search(UserSearchCondition userSearchCondition);
}
