package po.receiptPO;

import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class SalesRetReceiptPO extends SalesReceiptPO {
    public SalesRetReceiptPO(){}
    public SalesRetReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String clerkName, String stockName, ReceiptGoodsItemPO[] goodsList, double discountAmount, double tokenAmount, double originSum, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, goodsList, discountAmount, tokenAmount, originSum, comment);

    }

}
