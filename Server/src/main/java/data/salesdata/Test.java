package data.salesdata;

import data.promotiondata.PromotionData;
import po.ReceiptGoodsItemPO;
import po.promotionPO.PromotionGoodsItemPO;
import po.receiptPO.SalesRetReceiptPO;
import po.receiptPO.SalesSellReceiptPO;

import java.rmi.RemoteException;

public class Test {
    public static void main(String[] args) throws RemoteException {

       SalesSellReceiptData salesSellReceiptData = new SalesSellReceiptData();
     //   SalesRetReceiptData salesRetReceiptData =new SalesRetReceiptData();

        ReceiptGoodsItemPO[] receiptGoodsItemPOS = new ReceiptGoodsItemPO[]{
                new ReceiptGoodsItemPO("root/17-1",21,300,""),
                new ReceiptGoodsItemPO("root/13/0-2",22,300,""),
                new ReceiptGoodsItemPO("root/17-2",24,303,""),
                //new ReceiptGoodsItemPO("125",20,300,""),
        };

        for (int i = 0; i < 3; i++) {
            SalesSellReceiptPO salesSellReceiptPO = salesSellReceiptData.getNew();
            salesSellReceiptPO.setGoodsList(receiptGoodsItemPOS);
            salesSellReceiptData.update(salesSellReceiptPO);
        }





    }
}
