package messageTest;

import blService.messageblService.ReceiptMessageblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import vo.ReceiptMessageVO;

import java.rmi.RemoteException;

public class ReceiptMessageTest {
    public static void main(String[] args) {
        try {
            ReceiptMessageblService messageblService = MyServiceFactory.getReceiptMessageblService();
//            ReceiptMessageVO m = messageblService.selectByUser(12).get(0);
//            messageblService.delete(m.toPO());

            System.out.println("fdas");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
