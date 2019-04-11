package businesslogic.promotionbl.testprmt;

import businesslogic.billbl.PaymentBillReceiptbl;
import util.RespectiveReceiptSearchCondition;
import vo.billReceiptVO.PaymentReceiptVO;

import java.util.ArrayList;

public class MyTestPayment {

    public static void main(String[] args){
        try{
            PaymentBillReceiptbl paymentBillReceiptbl = new PaymentBillReceiptbl();
            ArrayList<PaymentReceiptVO> list = paymentBillReceiptbl.search(new RespectiveReceiptSearchCondition(null,null,null,null,null,null,null));
            System.out.println(list.size());
            System.out.println(list.get(0).getTransferList());
            System.out.println(list.get(0).getTransferList().get(0).getComment());

            paymentBillReceiptbl.delete(list.get(0));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}