package po.receiptPO;

import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class StockRetReceiptPO extends StockReceiptPO {
    public StockRetReceiptPO() {
    }

    public StockRetReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberid,String stockName, ReceiptGoodsItemPO[] goodsList,double sum, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberid, stockName, goodsList, sum, comment);
    }
}
