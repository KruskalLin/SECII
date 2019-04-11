package po.promotionPO;


import util.PromotionState;

import java.time.LocalDateTime;

public class TotalPromotionPO extends PromotionPO {
    private double requiredTotal;
    private double tokenAmount;
    private PromotionGoodsItemPO[] gifts;

    public TotalPromotionPO() {
    }

    public TotalPromotionPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment, PromotionState promotionState, double requiredTotal, double tokenAmount, PromotionGoodsItemPO[] gifts) {
        super(dayId, createTime, lastModifiedTime, beginTime, endTime, comment, promotionState);
        this.requiredTotal = requiredTotal;
        this.tokenAmount = tokenAmount;
        this.gifts = gifts;
    }

    public double getRequiredTotal() {
        return requiredTotal;
    }

    public void setRequiredTotal(double requiredTotal) {
        this.requiredTotal = requiredTotal;
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
