package data.billreceiptdata;

import po.CashItemPO;
import po.TransferItemPO;
import po.receiptPO.CashBillReceiptPO;
import po.receiptPO.ChargeBillReceiptPO;
import po.receiptPO.PaymentBillReceiptPO;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReceiptTest {

    public static void main(String[] args){
        try{
            //int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, TransferItemPO[] transferList, double sum
            LocalDateTime time = LocalDateTime.now();
            TransferItemPO temp[] = new TransferItemPO[2];
            temp[0] = new TransferItemPO(1,1,"1");
            temp[1] = new TransferItemPO(2,2,"3");

            CashItemPO temp1[] = new CashItemPO[2];
            temp1[0] = new CashItemPO("1",1,"1");
            temp1[1] = new CashItemPO("1",1,"1");

            temp[2] = new TransferItemPO(3,3,"2");
            PaymentBillReceiptData paymentBillReceiptData = new PaymentBillReceiptData();
            PaymentBillReceiptPO po =new PaymentBillReceiptPO();
            po = paymentBillReceiptData.getNew();
            System.out.println(po.getDayId());
            System.out.println(po.getClientId());
            po.setTransferList(temp);
            paymentBillReceiptData.update(po);
            ArrayList<PaymentBillReceiptPO> list = paymentBillReceiptData.search(new RespectiveReceiptSearchCondition(null,null,null,null,null,0,2));
            System.out.println(list.get(0).getTransferList()[0].getComment());
//            paymentBillReceiptData.insert(new PaymentBillReceiptPO(100,101,time,time, ReceiptState.DRAFT,100,temp,1000));

            //ChargeBillReceiptData chargeBillReceiptData = new ChargeBillReceiptData();
            //ChargeBillReceiptPO po1 = new ChargeBillReceiptPO();
            //po1 = chargeBillReceiptData.getNew();
            //po1.setTransferList(temp);
            //chargeBillReceiptData.update(po1);

//            CashBillReceiptData cashBillReceiptData = new CashBillReceiptData();
//            CashBillReceiptPO po2 = new CashBillReceiptPO();
//            po2 = cashBillReceiptData.getNew();
//            po2.setItemList(temp1);
//            cashBillReceiptData.update(po2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
