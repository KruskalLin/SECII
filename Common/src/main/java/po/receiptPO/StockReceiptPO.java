package po.receiptPO;

import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class StockReceiptPO extends ReceiptPO{
    private int memberId;
    private String stockName;
    private ReceiptGoodsItemPO[] goodsList;
    private double originSum;
    private String comment;

    public StockReceiptPO(){}

    public StockReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String stockName, ReceiptGoodsItemPO[] goodsList, double originSum, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberId;
        this.stockName = stockName;
        this.goodsList = goodsList;
        this.comment = comment;
        this.originSum = originSum;
    }
    public ReceiptGoodsItemPO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ReceiptGoodsItemPO[] goodsList) {
        this.goodsList = goodsList;
    }

    public double getOriginSum() {
        return originSum;
    }

    public void setOriginSum(double originSum) {
        this.originSum = originSum;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
