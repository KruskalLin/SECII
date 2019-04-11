package userdata;

import com.sun.org.apache.regexp.internal.RE;
import data.salesdata.SalesRetReceiptData;
import data.userdata.UserData;
import dataService.userdataService.UserDataService;
import po.ReceiptGoodsItemPO;
import po.receiptPO.StockPurReceiptPO;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.UserSearchCondition;

import java.time.LocalDateTime;

public class Userdata {

    public static void main(String args[]){

        try{
            UserDataService userdata = new UserData();
            userdata.search(new UserSearchCondition());

            ReceiptGoodsItemPO receiptGoodsItemPO[] = new ReceiptGoodsItemPO[4];

            SalesRetReceiptData salesRetReceiptData = new SalesRetReceiptData();
            salesRetReceiptData.search(new ReceiptSearchCondition());

        //    receiptGoodsItemPO[0] = new ReceiptGoodsItemPO(2,3,5.5,"sabi");
          //  receiptGoodsItemPO[1] = new ReceiptGoodsItemPO(2,3,5.5,"sabi");
           // receiptGoodsItemPO[2] = new ReceiptGoodsItemPO(2,3,5.5,"sabi");
            //receiptGoodsItemPO[3] = new ReceiptGoodsItemPO(2,3,5.5,"sabi");
/*
            new StockPurReceiptPO(1,5, LocalDateTime.now(),LocalDateTime.now().plusDays(1), ReceiptState.PENDING,1,"aaa",receiptGoodsItemPO,40.1,"sabi");
            new StockPurReceiptPO(2,5, LocalDateTime.now(),LocalDateTime.now().plusDays(1), ReceiptState.PENDING,1,"aaa",receiptGoodsItemPO,40.1,"sabi");
            new StockPurReceiptPO(3,6, LocalDateTime.now(),LocalDateTime.now().plusDays(1), ReceiptState.DRAFT,1,"aaa",receiptGoodsItemPO,40.1,"sabi");
            new StockPurReceiptPO(4,7, LocalDateTime.now(),LocalDateTime.now().plusDays(1), ReceiptState.REJECTED,1,"aaa",receiptGoodsItemPO,40.1,"sabi");
            new StockPurReceiptPO(5,5, LocalDateTime.now(),LocalDateTime.now().plusDays(1), ReceiptState.PENDING,1,"aaa",receiptGoodsItemPO,40.1,"sabi");
            new StockPurReceiptPO(6,5, LocalDateTime.now(),LocalDateTime.now().plusDays(1), ReceiptState.APPROVED,1,"aaa",receiptGoodsItemPO,40.1,"sabi");

*/



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
