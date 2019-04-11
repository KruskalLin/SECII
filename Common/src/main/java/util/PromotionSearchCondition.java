package util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PromotionSearchCondition implements Serializable {
    private LocalDateTime lastModifiedFloor, lastModifiedCeil;
    private LocalDateTime beginFloor, beginCeil;
    private LocalDateTime endFloor, endCeil;
    private PromotionState promotionState;
    // 但是UI层打算只提供两个。接索时只需要满足在给定的时间里。促销开始的时间比条件中结束的时间早，促销结束的时间比条件中开始的时间晚
    private ArrayList<PromotionType> promotionTypes = new ArrayList<>();

    public LocalDateTime getLastModifiedFloor() {
        return lastModifiedFloor;
    }

    public void setLastModifiedFloor(LocalDateTime lastModifiedFloor) {
        this.lastModifiedFloor = lastModifiedFloor;
    }

    public LocalDateTime getLastModifiedCeil() {
        return lastModifiedCeil;
    }

    public void setLastModifiedCeil(LocalDateTime lastModifiedCeil) {
        this.lastModifiedCeil = lastModifiedCeil;
    }

    public LocalDateTime getBeginFloor() {
        return beginFloor;
    }

    public void setBeginFloor(LocalDateTime beginFloor) {
        this.beginFloor = beginFloor;
    }

    public LocalDateTime getBeginCeil() {
        return beginCeil;
    }

    public void setBeginCeil(LocalDateTime beginCeil) {
        this.beginCeil = beginCeil;
    }

    public LocalDateTime getEndFloor() {
        return endFloor;
    }

    public void setEndFloor(LocalDateTime endFloor) {
        this.endFloor = endFloor;
    }

    public LocalDateTime getEndCeil() {
        return endCeil;
    }

    public void setEndCeil(LocalDateTime endCeil) {
        this.endCeil = endCeil;
    }

    public ArrayList<PromotionType> getPromotionTypes() {
        return promotionTypes;
    }

    public void setPromotionTypes(ArrayList<PromotionType> promotionTypes) {
        this.promotionTypes = promotionTypes;
    }

    public PromotionState getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(PromotionState promotionState) {
        this.promotionState = promotionState;
    }
}
