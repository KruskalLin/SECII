package data.salesdata;

import data.generic.ReceiptData;
import po.promotionPO.PromotionGoodsItemPO;
import po.ReceiptGoodsItemPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class TestSalesSellData {

    public static void test() throws RemoteException {
        ReceiptData<SalesSellReceiptPO> rdao = new SalesSellReceiptData();


        // insert
        SalesSellReceiptPO ssrp = rdao.getNew();
        ssrp.setOperatorId(1);
        ssrp.setReceiptState(ReceiptState.PENDING);
        ssrp.setClerkName("Wang Er Xiao");
        ssrp.setGoodsList(new ReceiptGoodsItemPO[]{new ReceiptGoodsItemPO("1", 1, 2, "first")});
        ssrp.setOriginSum(100);
        ssrp.setComment("如果建表的时候不明确说明utf8，之后能用吗");
        ssrp.setGifts(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO("0", 10), new PromotionGoodsItemPO("1", 2)});

        rdao.update(ssrp);

        System.out.println("insert: ");
        System.out.println(ssrp);
        System.out.println();


        // get by state and update
        SalesSellReceiptPO ssrpA = rdao.selectByState(ReceiptState.PENDING).get(0);
        ssrpA.setComment("get and update");

        rdao.update(ssrpA);

        System.out.println("get and update: ");
        System.out.println(ssrpA);
        System.out.println();


        // get by time span and delete
        LocalDateTime begin = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(2);

//        SalesSellReceiptPO ssrpB = rdao.selectBetween(begin, end).get(0);

//        SalesSellReceiptPO ssrpC = rdao.search(new ReceiptSearchCondition(null, LocalDateTime.now(), null, "Wang Er Xiao", null, null)).get(0);
        SalesSellReceiptPO ssrpC = rdao.search(new RespectiveReceiptSearchCondition()).get(0);
        System.out.println(ssrpC.getLastModifiedTime());

        System.out.println("get and delete: ");
        System.out.println(ssrpC);

        // delete
//        rdao.delete(ssrpC);



    }


    public static void main(String[] args) throws RemoteException{
        test();
        System.out.println("the end");
    }
}
