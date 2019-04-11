package businesslogic.promotionbl.testprmt;

import blService.promotionblService.*;
import blService.salesblService.SalesSellblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.promotionbl.PromotionListbl;
import businesslogic.salesbl.SalesSellbl;
import vo.ListGoodsItemVO;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionVO;
import vo.promotionVO.TotalPromotionVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.util.ArrayList;

public class TestSelectInEffect {

    public static void main(String[] args) {
//        list.add(new ListGoodsItemVO("sabi", "4", "SABI", 50, 2, "nishisabi"));

//        SalesSellReceiptVO salesSellReceiptVO = new SalesSellReceiptVO("JHT-20170101-00023", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "awb", "awd", list, "awd", 1200000, 12, 12, null, 123);
//        SalesSellReceiptVO salesSellReceiptVO1 = new SalesSellReceiptVO("JHT-20170101-00011", 12, LocalDateTime.now(), LocalDateTime.now().minusDays(4), ReceiptState.PENDING, 1, "linyuchao", "awx", "21c", list, "x", 12, 132, 12, null, 123);
//        SalesSellReceiptVO salesSellReceiptVO2 = new SalesSellReceiptVO("JHT-20170101-00000", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "brb", "cva", list, "cc", 12, 12123, 12, null, 123);
//        SalesSellReceiptVO salesSellReceiptVO3 = new SalesSellReceiptVO("JHT-20170101-00000", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, 1, "linyuchao", "wad", "wadw", list, "beba", 12, 123, 12, null, 123);
//        SalesSellReceiptVO salesSellReceiptVO4 = new SalesSellReceiptVO("JHT-20170101-12333", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "awdaw", "awd", list, "qe1ewdawdwadjawidhzkhvkdhlwaclwawdawdawdhvilahwl", 12, 12, 12, null, 123);
//        SalesSellReceiptVO salesSellReceiptVO5 = new SalesSellReceiptVO("JHT-20170101-11515", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "", "", list, "2", 12, 12, 1882, null, 112223);

        try {
            // 这里测试match
            ArrayList<ListGoodsItemVO> list = new ArrayList<>();
            ListGoodsItemVO r1 = new ListGoodsItemVO("花灯3", "123", "大灯2", 29, 2, "nishisabi");
            ListGoodsItemVO r2 = new ListGoodsItemVO("花灯3", "123", "大灯2", 29, 2, "nishisabi");
            ListGoodsItemVO r3 = new ListGoodsItemVO("花灯3", "123", "大灯2", 29, 2, "nishisabi");
            ListGoodsItemVO r4 = new ListGoodsItemVO("花灯3", "123", "大灯2", 29, 2, "nishisabi");
            list.add(r1);
            list.add(r2);
            list.add(r3);
            list.add(r4);

            SalesSellblService salesSellblService= new SalesSellbl();
            SalesSellReceiptVO ssr = salesSellblService.getNew();
//            ssr.setMemberLevel(4);
            ssr.setItems(list);

            PromotionListblService plist = MyblServiceFactory.getService(PromotionListblService.class);

            PromotionblService<CombinePromotionVO> combinePromotionblService = MyblServiceFactory.getService(CombinePromotionblService.class);;
            PromotionblService<MemberPromotionVO> memberPromotionblService = MyblServiceFactory.getService(MemberPromotionblService.class);
            PromotionblService<TotalPromotionVO> totalPromotionblService = MyblServiceFactory.getService(TotalPromotionblService.class);

            combinePromotionblService.selectInEffect().forEach(x -> x.getGoodsCombination().forEach(c -> System.out.println(c.getName())));

            PromotionInfo promotionInfo = MyServiceFactory.getPromotionInfo();
            ArrayList<PromotionVO> promotionVOS =  promotionInfo.getMatch(ssr);
            System.out.println(promotionVOS.size());
            System.out.println(promotionVOS.get(0).totalReduce);



            // 这只是测试select in effect。但是后来发现好像用promotionSearchCondition比较好…那个selectInEffect未知错误
//            PromotionblService<CombinePromotionVO> cpbl = MyblServiceFactory.getService(CombinePromotionblService.class);
//            PromotionblService<MemberPromotionVO> mpbl = MyblServiceFactory.getService(MemberPromotionblService.class);
//            cpbl.selectInEffect();
//            PromotionSearchCondition promotionSearchCondition = new PromotionSearchCondition();
//            promotionSearchCondition.setBeginCeil(LocalDateTime.now());
//            promotionSearchCondition.setEndFloor(LocalDateTime.now());
//            System.out.println(mpbl.search(promotionSearchCondition).get(0).getGifts().get(0).getId());
//            System.out.println(mpbl.selectInEffect().get(0).getGifts().get(0).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
