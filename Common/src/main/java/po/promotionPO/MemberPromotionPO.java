package po.promotionPO;

import po.promotionPO.PromotionPO;
import util.PromotionState;

import java.time.LocalDateTime;

public class MemberPromotionPO extends PromotionPO {
    private int requiredLevel; // the Required VIP Level
    private double discountFraction; // a fraction between 0 ~ 1
    private double tokenAmount;
    private PromotionGoodsItemPO[] gifts;


    public MemberPromotionPO() {
    }

    public MemberPromotionPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment, PromotionState promotionState, int requiredLevel, double discountFraction, double tokenAmount, PromotionGoodsItemPO[] gifts) {
        super(dayId, createTime, lastModifiedTime, beginTime, endTime, comment, promotionState);
        this.requiredLevel = requiredLevel;
        this.discountFraction = discountFraction;
        this.tokenAmount = tokenAmount;
        this.gifts = gifts;
    }

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

    public PromotionGoodsItemPO[] getGifts() {
        return gifts;
    }

    public void setGifts(PromotionGoodsItemPO[] gifts) {
        this.gifts = gifts;
    }
}
