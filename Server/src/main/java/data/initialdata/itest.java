package data.initialdata;

import po.AccountInitialPO.InitialAccountPO;
import po.AccountInitialPO.InitialMemberPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class itest {
    public static void main(String[] args){

        try{
            InitialData initialData =new InitialData();

            //initialData.initial("2011");
            //ArrayList<InitialAccountPO> list = initialData.showAccount("2018");
            ArrayList<InitialMemberPO> list = initialData.showMember("2018");
            System.out.println(list.size());
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

}
