package businesslogic.promotionbl.testprmt;

import blService.stockblService.StockPurblService;
import blService.stockblService.StockRetblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import util.RespectiveReceiptSearchCondition;

import java.rmi.RemoteException;

public class MyblStockPurTest {
    public static void main(String[] args) {
        try {
            StockPurblService stockPurblService = MyblServiceFactory.getService(StockPurblService.class);
            StockRetblService stockRetblService = MyblServiceFactory.getService(StockRetblService.class);

//            stockPurblService.getNew();
//
//            StockPurReceiptVO sprp = stockPurblService.getNew();
//            sprp.setMemberId(111);
//            sprp.setStockName("北大仓");
//            sprp.setSum(10.0);
//            sprp.setComment("城葙冰");
//
//            stockPurblService.update(sprp);

            System.out.println(stockPurblService.search(new RespectiveReceiptSearchCondition()).get(0));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
