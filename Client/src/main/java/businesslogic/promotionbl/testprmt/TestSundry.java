package businesslogic.promotionbl.testprmt;

import blService.businessblservice.BusinessProgressblService;
import businesslogic.blServiceFactory.MyblServiceFactory;

public class TestSundry {
    public static void main(String[] args) {
        BusinessProgressblService b = MyblServiceFactory.getService(BusinessProgressblService.class);
        System.out.println(b);
    }
}
