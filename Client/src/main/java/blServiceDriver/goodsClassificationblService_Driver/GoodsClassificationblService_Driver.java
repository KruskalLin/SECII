/*
package blServiceDriver.goodsClassificationblService_Driver;

import blServiceStub.goodsClassificationblService_Stub.GoodsClassificationblService_Stub;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.Set;

public class GoodsClassificationblService_Driver {
    private GoodsClassificationblService_Stub GoodsClassificationBLService_stub = new GoodsClassificationblService_Stub();
    private String upID = "123";
    private Set<GoodsClassificationVO> test1;
    private String test2;
    private ResultMessage test3;
    private ResultMessage test4;
    private ResultMessage test5;
    public void drive(){
        test1 = GoodsClassificationBLService_stub.show();
        if(test1!=null)
            System.out.println("success");
        else
            System.out.println("failed");

        test2 = GoodsClassificationBLService_stub.getID(upID);
        if(test2!=null)
            System.out.println("success");
        else
            System.out.println("failed");

        test3 = GoodsClassificationBLService_stub.addGoodsClassification("123", "123");
        if(test3==ResultMessage.SUCCESS)
            System.out.println("insert success");
        else
            System.out.println("insert failed");

        test4 = GoodsClassificationBLService_stub.deleteGoodsClassification("123");
        if(test4==ResultMessage.SUCCESS)
            System.out.println("delete success");
        else
            System.out.println("delete failed");

        test5 = GoodsClassificationBLService_stub.updateGoodsClassification("124", "123");
        if(test5==ResultMessage.SUCCESS)
            System.out.println("delete success");
        else
            System.out.println("delete failed");


    }
}
*/
