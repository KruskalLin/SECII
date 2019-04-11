package po.receiptPO;

import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class SalesReceiptPO extends ReceiptPO {
    private int memberId;
    private String clerkName; // 业务员
    private String stockName;
    private ReceiptGoodsItemPO[] goodsList;
    private double discountAmount;
    private double tokenAmount;
    private double originSum;
    private String comment;

    public SalesReceiptPO() {
    }

    public SalesReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberid, String clerkName, String stockName, ReceiptGoodsItemPO[] goodsList, double discountAmount, double tokenAmount, double originSum, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberid;
        this.clerkName = clerkName;
        this.stockName = stockName;
        this.goodsList = goodsList;
        this.discountAmount = discountAmount;
        this.tokenAmount = tokenAmount;
        this.originSum = originSum;
        this.comment = comment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public ReceiptGoodsItemPO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ReceiptGoodsItemPO[] goodsList) {
        this.goodsList = goodsList;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double gettokenAmount() {
        return tokenAmount;
    }

    public void settokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public double getOriginSum() {
        return originSum;
    }

    public void setOriginSum(double originSum) {
        this.originSum = originSum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SalesReceiptPO{" +
                "Id=" + getDayId() + ", " +
                "memberId=" + memberId +
                ", clerkName='" + clerkName + '\'' +
                ", stockName='" + stockName + '\'' +
                ", goodsList=" + Arrays.toString(goodsList) +
                ", discountAmount=" + discountAmount +
                ", tokenAmount=" + tokenAmount +
                ", originSum=" + originSum +
                ", comment='" + comment + '\'' +
                '}';
    }
}
