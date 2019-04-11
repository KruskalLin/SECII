/*
package blServiceDriver.inventoryblService_Driver;

import blServiceStub.inventoryblService_Stub.InventoryblService_Stub;
import util.ResultMessage;
import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

public class InventoryblService_Driver {
    private InventoryblService_Stub inventoryBLService_Stub = new InventoryblService_Stub();
    private InventoryViewVO test1;
    private InventoryCheckVO test2;
    private String test3,test4,test5,test6;
    private ResultMessage test7,test8;

    public void drive(){
        test1 = inventoryBLService_Stub.viewInventory("2017-1-3", "2017-1-5");
        if(test1!=null)
            System.out.println("success");
        else
            System.out.println("failed");


        test3 = inventoryBLService_Stub.getGiftID();
        if(test3!=null)
            System.out.println("success");
        else
            System.out.println("failed");

        test4 = inventoryBLService_Stub.getLossID();
        if(test4!=null)
            System.out.println("success");
        else
            System.out.println("failed");

        test5 = inventoryBLService_Stub.getOverFlowID();
        if(test5!=null)
            System.out.println("success");
        else
            System.out.println("failed");

        test6 = inventoryBLService_Stub.getAlarmID();
        if(test6!=null)
            System.out.println("success");
        else
            System.out.println("failed");

        test7 = inventoryBLService_Stub.addGoods("1", 1);
        if(test7==ResultMessage.SUCCESS)
            System.out.println("success");
        else {
            System.out.println("failed");

            test8 = inventoryBLService_Stub.submit();
            if(test8==ResultMessage.SUCCESS)
                System.out.println("success");
            else
                System.out.println("failed");
        }

    }
}
*/
