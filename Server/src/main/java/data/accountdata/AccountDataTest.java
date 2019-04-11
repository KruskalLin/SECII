package data.accountdata;


import po.AccountPO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDataTest {

    public static void main(String[] args){
        try{
            AccountData accountData = new AccountData();
            //AccountPO accountPO = new AccountPO(null,"lp",100.0);

            //accountData.insert(accountPO);
            //AccountPO accountPO = accountData.getAccountByName("lp3");
            //System.out.print(accountPO.getBalance());
            ArrayList<AccountPO> list = new ArrayList<>();
            list = accountData.getAll();
            System.out.print(list.size());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
