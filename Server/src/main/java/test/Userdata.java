package test;

import data.memberdata.MemberData;
import data.stockdata.StockPurReceiptData;
import data.userdata.UserData;
import dataService.userdataService.UserDataService;
import po.MemberPO;
import po.UserPO;
import po.receiptPO.StockPurReceiptPO;
import util.MemberSearchCondition;
import util.RespectiveReceiptSearchCondition;
import util.UserCategory;
import util.UserSearchCondition;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Userdata {

    public static void main(String args[]){

        try{

            MemberData userdata = new MemberData();
            ArrayList<MemberPO> list = userdata.search(new MemberSearchCondition());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}