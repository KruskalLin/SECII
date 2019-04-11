package businesslogic.promotionbl.testprmt;

import blService.businessblservice.BusinessProgressblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import util.ReceiptSearchCondition;

import java.rmi.RemoteException;

public class TestBusinessProgress {
    public static void main(String[] args) {
        BusinessProgressblService businessProgressblService = MyblServiceFactory.getService(BusinessProgressblService.class);
        try {
            businessProgressblService.search(new ReceiptSearchCondition());
            System.out.println("ok");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
