package accountdata;

import data.accountdata.AccountData;
import org.junit.Test;
import po.AccountPO;

public class Accountdata {

    @Test
    public void test(){
        try {
            AccountData accountData = new AccountData();
            accountData.insert(new AccountPO(null,"测试1",10000));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
