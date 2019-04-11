package po.receiptPO;

import util.ReceiptState;

import java.time.LocalDateTime;

public class InventoryGiftReceiptPO extends InventoryReceiptPO {
    public InventoryGiftReceiptPO() {
    }

    public InventoryGiftReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                  ReceiptState receiptState, String clerkName, InventoryReceiptGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, clerkName, goodsList, comment);
    }
}
