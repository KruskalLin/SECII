package ui.accountantui;

import vo.AccountListVO;

import java.util.ArrayList;
import java.util.List;

public class AccountChosenItem {

    private static ArrayList<AccountListVO> chosenitems = new ArrayList<>();
    static public void addItem(AccountListVO accountListVO){
        chosenitems.add(accountListVO);
    }
    static public void removeItem(AccountListVO accountListVO){
        chosenitems.remove(accountListVO);
    }
    static public List<AccountListVO> getList(){return chosenitems;}
}
