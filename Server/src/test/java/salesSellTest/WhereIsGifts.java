package salesSellTest;

import data.salesdata.SalesSellReceiptData;
import po.receiptPO.SalesSellReceiptPO;
import util.RespectiveReceiptSearchCondition;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class WhereIsGifts {
    public static void main(String[] args) throws RemoteException {
        SalesSellReceiptData salesSellReceiptData = new SalesSellReceiptData();
        ArrayList<SalesSellReceiptPO> xx = salesSellReceiptData.search(new RespectiveReceiptSearchCondition());
        System.out.println("fdsa");
    }
}
