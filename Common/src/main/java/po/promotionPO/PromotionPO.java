package po.promotionPO;

import po.generic.ReceipishPO;
import util.PromotionState;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class PromotionPO extends ReceipishPO {
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    private String comment;
    private PromotionState promotionState;

    public PromotionPO() {
    }

    public PromotionPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment, PromotionState promotionState) {
        super(dayId, createTime, lastModifiedTime);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.comment = comment;
        this.promotionState = promotionState;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PromotionState getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(PromotionState promotionState) {
        this.promotionState = promotionState;
    }
}