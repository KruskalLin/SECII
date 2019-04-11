package businesslogic.promotionbl;

import blService.promotionblService.*;
import businesslogic.blServiceFactory.MyblServiceFactory;
import util.PromotionSearchCondition;
import util.PromotionType;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.promotionVO.*;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PromotionListbl implements PromotionListblService, PromotionInfo {
    private PromotionblService<CombinePromotionVO> combinePromotionblService;
    private PromotionblService<MemberPromotionVO> memberPromotionblService;
    private PromotionblService<TotalPromotionVO> totalPromotionblService;


    // 和直接从工厂里拿没有区别。
    public PromotionListbl() throws RemoteException, NotBoundException, MalformedURLException {
        this.combinePromotionblService = MyblServiceFactory.getService(CombinePromotionblService.class);
        this.memberPromotionblService = MyblServiceFactory.getService(MemberPromotionblService.class);
        this.totalPromotionblService = MyblServiceFactory.getService(TotalPromotionblService.class);
    }

    @Override
    public ArrayList<PromotionVO> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException {
        ArrayList<PromotionVO> resultList = new ArrayList<>();

        if (promotionSearchCondition.getPromotionTypes().contains(PromotionType.COMBINE)) {
            ArrayList<CombinePromotionVO> cps = combinePromotionblService.search(promotionSearchCondition);
            resultList.addAll(cps);
        }
        if (promotionSearchCondition.getPromotionTypes().contains(PromotionType.MEMBER)) {
            ArrayList<MemberPromotionVO> mps = memberPromotionblService.search(promotionSearchCondition);
            resultList.addAll(mps);
        }
        if (promotionSearchCondition.getPromotionTypes().contains(PromotionType.TOTAL)) {
            ArrayList<TotalPromotionVO> tps = totalPromotionblService.search(promotionSearchCondition);
            resultList.addAll(tps);
        }

        return resultList;
    }

    @Override
    public ResultMessage delete(PromotionVO promotionVO) throws RemoteException, MalformedURLException, NotBoundException {
        return promotionVO.getService().delete(promotionVO);
    }

    @Override
    public ArrayList<PromotionVO> getMatch(SalesSellReceiptVO salesSellReceiptVO) throws RemoteException {
        ArrayList<PromotionVO> resultList = new ArrayList<>();

        resultList.addAll(combinePromotionblService.selectInEffect().stream().filter(c -> {
            for (PromotionGoodsItemVO pvo : c.getGoodsCombination()) {
                int numSum = salesSellReceiptVO.getItems().stream()
                        .filter(lg -> lg.getGoodsId().equals(pvo.getId()))
                        .mapToInt(ListGoodsItemVO::getGoodsNum).sum();
                if (numSum < pvo.getNum()) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toCollection(ArrayList::new)));

        resultList.addAll(memberPromotionblService.selectInEffect().stream()
                .filter(m -> salesSellReceiptVO.getMemberLevel() >= m.getRequiredLevel())
                .collect(Collectors.toCollection(ArrayList::new)));
        resultList.addAll(totalPromotionblService.selectInEffect().stream()
                .filter(t -> salesSellReceiptVO.getOriginSum() >= t.getRequiredTotal())
                .collect(Collectors.toCollection(ArrayList::new)));
        return resultList;
//        ArrayList<ListGoodsItemVO> boughtGoods = salesSellReceiptVO.getItems();
//
//        combinePromotionblService.selectInEffect().stream().max((c1, c2) -> {
//            Function<CombinePromotionVO, Double> fx1 = c -> { // fx这个函数返回该促销策略下的优惠值，下面两个同理
//                ArrayList<ListGoodsItemVO> copy = new ArrayList<>(boughtGoods); // 好像不用copy
//
//                c.getGoodsCombination().stream().mapToInt(g -> { // 找到最多支持的重数，即最少的的那个商品对应的倍数
//                    if (copy.stream().anyMatch(lg -> lg.getGoodsId().equals(g.getId()))) {
//                        return copy.stream().filter(lg -> lg.getGoodsId().equals(g.getId())).mapToInt(ListGoodsItemVO::getGoodsNum).sum() / g.getNum();
//                    }
//                    return 0;
//                }).min().ifPresent(c::setCount);
//
//                return c.totalReduce = c.getCount() * c.getDiscountAmount();
//            };
//
//            return fx1.apply(c1).compareTo(fx1.apply(c2));
//        }).ifPresent(resultList::add);
//
//        memberPromotionblService.selectInEffect().stream().filter(m -> salesSellReceiptVO.getMemberLevel() >= m.getRequiredLevel()).max((m1, m2) -> {
//            Function<MemberPromotionVO, Double> fx2 = m -> {
//                return m.totalReduce = (1 - m.getDiscountFraction()) * salesSellReceiptVO.getOriginSum()
//                        + m.getTokenAmount()
//                        + m.getGifts().stream().mapToDouble(mg -> mg.getNum() * mg.getUnitPrice()).sum();
//            };
//            return fx2.apply(m1).compareTo(fx2.apply(m2));
//        }).ifPresent(resultList::add);
//
//        totalPromotionblService.selectInEffect().stream().filter(t -> salesSellReceiptVO.getOriginSum() >= t.getRequiredTotal()).max((t1, t2) -> {
//            Function<TotalPromotionVO, Double> fx3 = t -> {
//                return t.totalReduce = t.getTokenAmount() + t.getGifts().stream().mapToDouble(mg -> mg.getNum() * mg.getUnitPrice()).sum();
//            };
//            return fx3.apply(t1).compareTo(fx3.apply(t2));
//        }).ifPresent(resultList::add);
//
//
//        // 下面写的很丑…但也不管了
//        if (resultList.isEmpty()) {
//            return resultList;
//        }
//
//        PromotionVO max = resultList.get(0);
//        for (int i = 0; i < resultList.size(); i++) {
//            if (resultList.get(i).totalReduce > max.totalReduce) {
//                max = resultList.get(i);
//            }
//        }
//        resultList.clear();
//        resultList.add(max);

//        ArrayList<PromotionVO> combineVOs = combinePromotionblService.selectInEffect();

    }
}
