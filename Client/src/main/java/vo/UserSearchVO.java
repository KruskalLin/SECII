package vo;

import ui.userui.usermanagerui.UserListPane;
import util.UserCategory;

import java.util.HashSet;

public class UserSearchVO {
    HashSet<UserCategory> userCategories;

    public UserSearchVO(){ this.userCategories = new HashSet<>();}

    public HashSet<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(HashSet<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }
}
