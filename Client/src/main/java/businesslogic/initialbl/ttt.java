package businesslogic.initialbl;

import po.AccountPO;
import po.GoodsPO;

import java.util.ArrayList;

public class ttt {
    public static void main(String[] args)throws Exception{
        Initialbl initialbl = new Initialbl();
        //initialbl.initial("2018");
        //ArrayList<AccountPO> list = initialbl.showAccount("2018");
        ArrayList<GoodsPO> list = initialbl.showGoods("2018");
        System.out.println(list.size());
    }
}
