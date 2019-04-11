package businesslogic.accountbl;

import vo.AccountListVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class temp {

    public static void main(String[] args){
        try{
            Accountbl accountbl = new Accountbl();
            /*Set<AccountListVO> list = accountbl.getAll();
            for(AccountListVO vo:list){
                System.out.println(vo);
            }*/
            accountbl.incBalance(17,99);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
