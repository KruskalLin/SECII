package po.receiptPO;

import po.promotionPO.PromotionGoodsItemPO;
import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class SalesSellReceiptPO extends SalesReceiptPO {
    private PromotionGoodsItemPO[] gifts;
    private double giveTokenAmount;

    public SalesSellReceiptPO() {
    }

    public SalesSellReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientId, String clerkName, String stockName, ReceiptGoodsItemPO[] goodsList, double discountAmount, double tokenAmount, double originSum, String comment, PromotionGoodsItemPO[] gifts, double giveTokenAmount) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, clientId, clerkName, stockName, goodsList, discountAmount, tokenAmount, originSum, comment);
        this.gifts = gifts;
        this.giveTokenAmount = giveTokenAmount;
    }

    public PromotionGoodsItemPO[] getGifts() {
        return gifts;
    }

    public void setGifts(PromotionGoodsItemPO[] gifts) {
        this.gifts = gifts;
    }

    public double getGiveTokenAmount() {
        return giveTokenAmount;
    }

    public void setGiveTokenAmount(double giveTokenAmount) {
        this.giveTokenAmount = giveTokenAmount;
    }
}
