package vo.promotionVO;

import blService.promotionblService.MemberPromotionblService;
import blService.promotionblService.PromotionblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import javafx.scene.control.Label;
import po.promotionPO.MemberPromotionPO;
import po.promotionPO.PromotionGoodsItemPO;
import ui.mainui.PromotionCard;
import ui.managerui.promotionui.promotionDetailPane.MemberPromotionDetailPane;
import ui.managerui.promotionui.promotionDetailPane.PromotionDetailPane;
import ui.salesui.promotion.MyMemberPromotionCard;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


public class MemberPromotionVO extends PromotionVO {
    private int requiredLevel;
    private double discountFraction;
    private double tokenAmount;
    private ArrayList<PromotionGoodsItemVO> gifts;

    public MemberPromotionVO() {
    }

    public MemberPromotionVO(MemberPromotionPO promotionPO) {
        super(promotionPO);
        requiredLevel = promotionPO.getRequiredLevel();
        discountFraction = promotionPO.getDiscountFraction();
        tokenAmount = promotionPO.getTokenAmount();
        gifts = promotionPO.getGifts() == null ? new ArrayList<>()
                : Arrays.stream(promotionPO.getGifts()).map(PromotionGoodsItemVO::new).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    protected String getCodeName() {
        return "HYCX";
    }

    @Override
    public MemberPromotionPO toPO() {
        MemberPromotionPO result = toPromotionPO(MemberPromotionPO.class);
        result.setRequiredLevel(requiredLevel);
        result.setDiscountFraction(discountFraction);
        result.setTokenAmount(tokenAmount);
        result.setGifts(gifts == null ? null : gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
        return result;
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyblServiceFactory.getService(MemberPromotionblService.class);
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return new MemberPromotionDetailPane(this);
    }

//    @Override
//    public Label getInfoLabel() {
//        String content = "会员促销\n"
//                + "折扣：" + discountFraction + "\n"
//                + "代金卷：" + tokenAmount + "元\n"
//                + "赠品总值：" + gifts.stream().mapToDouble(g -> g.getNum() * g.getUnitPrice()).sum() + "元";
//
//        return new Label(content);
//    }


    @Override
    public PromotionCard getPromotionCard() {
        MyMemberPromotionCard result = new MyMemberPromotionCard(this);
        // TODO 这里的职责分配很有问题
        if (discountFraction < 1) {
            result.addDiscountAmount(discountFraction);
            return result;
        }
        if (tokenAmount > 0) {
            result.addTokenNum(tokenAmount);
            return result;
        }

        result.addGifts(gifts.stream().map(PromotionGoodsItemVO::toReceiptGoodsItemVO).collect(Collectors.toCollection(ArrayList::new)));
        return result;
    }

    @Override
    public void modifySalesSell(SalesSellReceiptVO salesSellReceiptVO) {
        salesSellReceiptVO.setDiscountAmount(salesSellReceiptVO.getOriginSum() * (1 - discountFraction));
        salesSellReceiptVO.setGiveTokenAmount(tokenAmount);
        salesSellReceiptVO.setGifts(gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
    }

    /**
     * getter and setter.
     * */

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public double getDiscountFraction() {
        return discountFraction;
    }

    public void setDiscountFraction(double discountFraction) {
        this.discountFraction = discountFraction;
    }

    public double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public ArrayList<PromotionGoodsItemVO> getGifts() {
        return gifts;
    }

    public void setGifts(Collection<PromotionGoodsItemVO> gifts) {
        this.gifts = new ArrayList<>(gifts);
    }
}
