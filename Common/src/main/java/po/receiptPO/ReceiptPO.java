package po.receiptPO;

import po.generic.ReceipishPO;
import util.ReceiptState;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class ReceiptPO extends ReceipishPO {
    private int operatorId;
    // 还是用的id，没有用String operatorName。但是下面业务员用的String，因为业务员不一定是系统里的吧？
    private ReceiptState receiptState;

//    private String comment;

    public ReceiptPO() {
    }

    // TODO 这个constructor保留只是为了其他类不会报错。因为顺序不一样……
    public ReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        super(dayId, createTime, lastModifiedTime);
        this.operatorId = operatorId;
        this.receiptState = receiptState;
    }

    public ReceiptPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, int operatorId, ReceiptState receiptState) {
        super(dayId, createTime, lastModifiedTime);
        this.operatorId = operatorId;
        this.receiptState = receiptState;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }
}
