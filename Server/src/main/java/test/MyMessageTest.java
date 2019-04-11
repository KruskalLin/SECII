package test;

import data.messagedata.ReceiptMessageData;
import po.ReceiptMessagePO;
import util.BillType;
import util.MessagePurpose;
import util.UserCategory;

import java.rmi.RemoteException;

public class MyMessageTest {
    public static void main(String[] args) throws RemoteException {
        ReceiptMessageData mdao = new ReceiptMessageData();

        // insert test
//        ReceiptMessagePO rmpo = new ReceiptMessagePO();
//        rmpo.setFromUserId(11);
//        rmpo.setToUserCategory(UserCategory.GeneralManager);
//        rmpo.setMessagePurpose(MessagePurpose.Approve);
//        rmpo.setBillType(BillType.Cash);
//        mdao.insert(rmpo);

        // select test
        ReceiptMessagePO p;
        p = mdao.selectByUserCategory(UserCategory.GeneralManager).get(0);
        p = mdao.selectByMold(p);


        // delete test
//        mdao.delete(mdao.selectByUser(12).get(0));


        System.out.println("end");
    }
}
