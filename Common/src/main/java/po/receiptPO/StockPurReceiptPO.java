package po.receiptPO;

import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class StockPurReceiptPO extends StockReceiptPO {
    public StockPurReceiptPO() {
    }

    public StockPurReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String stockName, ReceiptGoodsItemPO[] goodsList, double originSum, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, stockName, goodsList, originSum, comment);
    }
}
