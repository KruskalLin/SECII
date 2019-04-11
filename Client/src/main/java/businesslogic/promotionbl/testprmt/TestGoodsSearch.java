package businesslogic.promotionbl.testprmt;

import blService.goodsblService.goodsSearchInfo;
import businesslogic.blServiceFactory.MyServiceFactory;

public class TestGoodsSearch {
    public static void main(String[] args) {
        try {
            goodsSearchInfo goods = MyServiceFactory.getGoodsSearchInfo();
            System.out.println(goods.getGoodById("12432").getGoodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
