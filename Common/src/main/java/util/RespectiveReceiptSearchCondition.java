package util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RespectiveReceiptSearchCondition implements Serializable{
    private LocalDateTime createTimeFloor, createTimeCeil;
    private LocalDateTime lastModifiedTimeFloor, lastModifiedTimeCeil;
    private ReceiptState receiptState;
    private Integer operatorId;
    private Integer dayId;

    public RespectiveReceiptSearchCondition() {
    }

    public RespectiveReceiptSearchCondition(LocalDateTime createTimeFloor, LocalDateTime createTimeCeil, LocalDateTime lastModifiedTimeFloor, LocalDateTime lastModifiedTimeCeil, ReceiptState receiptState, Integer operatorId, Integer dayId) {
        this.createTimeFloor = createTimeFloor;
        this.createTimeCeil = createTimeCeil;
        this.lastModifiedTimeFloor = lastModifiedTimeFloor;
        this.lastModifiedTimeCeil = lastModifiedTimeCeil;
        this.receiptState = receiptState;
        this.operatorId = operatorId;
        this.dayId = dayId;
    }

    public LocalDateTime getCreateTimeFloor() {
        return createTimeFloor;
    }

    public void setCreateTimeFloor(LocalDateTime createTimeFloor) {
        this.createTimeFloor = createTimeFloor;
    }

    public LocalDateTime getCreateTimeCeil() {
        return createTimeCeil;
    }

    public void setCreateTimeCeil(LocalDateTime createTimeCeil) {
        this.createTimeCeil = createTimeCeil;
    }

    public LocalDateTime getLastModifiedTimeFloor() {
        return lastModifiedTimeFloor;
    }

    public void setLastModifiedTimeFloor(LocalDateTime lastModifiedTimeFloor) {
        this.lastModifiedTimeFloor = lastModifiedTimeFloor;
    }

    public LocalDateTime getLastModifiedTimeCeil() {
        return lastModifiedTimeCeil;
    }

    public void setLastModifiedTimeCeil(LocalDateTime lastModifiedTimeCeil) {
        this.lastModifiedTimeCeil = lastModifiedTimeCeil;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }
}
