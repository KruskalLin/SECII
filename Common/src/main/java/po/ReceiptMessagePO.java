package po;

import util.MessagePurpose;
import util.BillType;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReceiptMessagePO implements Serializable {
    private int id;

    private int fromUserId;
    private int toUserId;
    private UserCategory toUserCategory;

    private MessagePurpose messagePurpose;

    private LocalDateTime createTime;

    private int receiptDayId;
    private LocalDateTime receiptCreateTime;
    private BillType billType;


    public ReceiptMessagePO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public MessagePurpose getMessagePurpose() {
        return messagePurpose;
    }

    public void setMessagePurpose(MessagePurpose messagePurpose) {
        this.messagePurpose = messagePurpose;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getReceiptDayId() {
        return receiptDayId;
    }

    public void setReceiptDayId(int receiptDayId) {
        this.receiptDayId = receiptDayId;
    }

    public LocalDateTime getReceiptCreateTime() {
        return receiptCreateTime;
    }

    public void setReceiptCreateTime(LocalDateTime receiptCreateTime) {
        this.receiptCreateTime = receiptCreateTime;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public UserCategory getToUserCategory() {
        return toUserCategory;
    }

    public void setToUserCategory(UserCategory toUserCategory) {
        this.toUserCategory = toUserCategory;
    }
}
