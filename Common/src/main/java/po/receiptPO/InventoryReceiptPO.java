package po.receiptPO;

import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.Arrays;

public class InventoryReceiptPO extends ReceiptPO {
    private String clerkName; // 业务员

    private InventoryReceiptGoodsItemPO[] goodsList;

    /** 备注*/
    private String comment;
    public InventoryReceiptPO() {}

    public InventoryReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                              ReceiptState receiptState, String clerkName, InventoryReceiptGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.clerkName = clerkName;
        this.goodsList = goodsList;
        this.comment = comment;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public InventoryReceiptGoodsItemPO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(InventoryReceiptGoodsItemPO[] goodsList) {
        this.goodsList = goodsList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "InventoryReceiptPO{" +
                ", clerkName='" + clerkName + '\'' +
                ", goodsList=" + Arrays.toString(goodsList) +
                ", comment='" + comment + '\'' +
                '}';
    }
}
