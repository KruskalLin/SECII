package test;

import data.promotiondata.CombinePromotionData;
import data.promotiondata.MemberPromotionData;
import data.promotiondata.PromotionData;
import data.promotiondata.TotalPromotionData;
import dataService.promotiondataService.PromotionDataService;
import mapper.CombinePromotionPOMapper;
import mapper.MemberPromotionPOMapper;
import mapper.TotalPromotionPOMapper;
import po.promotionPO.CombinePromotionPO;
import po.promotionPO.MemberPromotionPO;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.TotalPromotionPO;
import util.PromotionSearchCondition;
import util.PromotionState;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestPromotionData {
    static void testM() throws RemoteException {
        PromotionData<MemberPromotionPO> mdao = new MemberPromotionData();

        // insert
        MemberPromotionPO mpo1 = mdao.getNew();
        mpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
        mpo1.setEndTime(LocalDateTime.of(2017, 12, 10, 0, 0));
        mpo1.setDiscountFraction(0.8);
        mpo1.setRequiredLevel(3);
        mpo1.setTokenAmount(5);
        mpo1.setComment("最新测试");
        mpo1.setGifts(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO("0", 10), new PromotionGoodsItemPO("a1", 2)});
        mpo1.setPromotionState(PromotionState.DRAFT);
        mdao.update(mpo1);


        // select
//        List<MemberPromotionPO> ml2 = mdao.selectInEffect();
//        ml2.forEach(m-> System.out.println(m.getDayId()));
//        mdao.search(new PromotionSearchCondition()).forEach(x -> System.out.println(x.getComment()));
        System.out.println(mdao.selectByMold(mpo1).getComment());


        System.out.println("finish testM");
    }

    static void testT() throws RemoteException {
//        TotalPromotionData tdao = new TotalPromotionData();

        PromotionData<TotalPromotionPO> tdao = new TotalPromotionData();

        // insert. (insert twice in a row. then commented)
        TotalPromotionPO tpo1 = tdao.getNew();
        tpo1.setCreateTime(LocalDateTime.now());
        tpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
        tpo1.setEndTime(LocalDateTime.of(2017, 12, 10, 0, 0));
        tpo1.setRequiredTotal(100);
        tpo1.setTokenAmount(5);
        tpo1.setGifts(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO("0", 10), new PromotionGoodsItemPO("1", 2)});
        tpo1.setComment("满"+tpo1.getDayId()+"00 减100"+"最新total");
        tpo1.setPromotionState(PromotionState.DRAFT);

        tdao.update(tpo1);

        System.out.println(tdao.selectByMold(tpo1).getComment());


        // select. (de-comment after insert)
//        List<TotalPromotionPO> ml2 = tdao.selectInEffect();
//
//        TotalPromotionPO tpo2 = ml2.get(1);
//
//        // test array storing
//        System.out.println("id" + tpo2.getGifts()[0].getId());
//
//        // update
//        tpo2.setRequiredTotal(200);
//        tdao.update(tpo2);
//
//        // delete
//        TotalPromotionPO tpo0 = ml2.get(0);
//        tdao.delete(tpo0);
//
//        // getDayId
//        System.out.println(tdao.getDayId());
//
//
//        System.out.println("finish testM");
    }

    static void testC() throws RemoteException{
        PromotionData<CombinePromotionPO> cdao = new CombinePromotionData();

//        CombinePromotionPO cpo1 = cdao.getNew();
//        cpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
//        cpo1.setEndTime(LocalDateTime.of(2017, 12, 30, 0, 0));
//        cpo1.setDiscountAmount(40);
//        cpo1.setGoodsCombination(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO("0", 10), new PromotionGoodsItemPO("1", 2)});
//        cpo1.setComment("皮包买三送一啦" + "后来Combine");
//        cpo1.setPromotionState(PromotionState.DRAFT);
//
//        cdao.update(cpo1);

        PromotionSearchCondition psc = new PromotionSearchCondition();
//        psc.setLastModifiedFloor(LocalDateTime.now().minusDays(30));
        ArrayList<CombinePromotionPO> cs =  cdao.search(psc);
        cs.forEach(c-> System.out.println(c.getDayId()));
        CombinePromotionPO c = new CombinePromotionPO();
        c.setCreateTime(LocalDateTime.now());
        c.setDayId(200000);
        System.out.println(cdao.selectByMold(c));
    }

    public static void main(String[] args) throws RemoteException{
//        testM();
//        testT();
//        testC();
//
//        System.out.println("end Main");

        PromotionDataService<CombinePromotionPO> pd = new CombinePromotionData();
        System.out.println(pd.selectInEffect().get(0).getGoodsCombination()[0].getId());
    }
}
