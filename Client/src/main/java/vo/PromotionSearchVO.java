package vo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.PromotionSearchCondition;
import util.PromotionState;
import util.PromotionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PromotionSearchVO {
    // 搜索任何开始时间比这个晚。(a1, b1)代表促销策略时间， (a2, b2)代表搜索条件时间。
    // 需要bi > a2，且a1 < b2; 所以a2是endFloor，b2是beginCeil。且a2是UI里的前一个，b2是后一个。
    private ObjectProperty<LocalDate> endFloorProperty = new SimpleObjectProperty<>(/*LocalDate.now()*/)
            , beginCeilProperty = new SimpleObjectProperty<>(/*LocalDate.now().plusDays(30)*/);
    private BooleanProperty containMemberProperty = new SimpleBooleanProperty()
            , containCombineProperty = new SimpleBooleanProperty()
            , containTotalProperty = new SimpleBooleanProperty();
    private ObjectProperty<PromotionState> promotionStateProperty = new SimpleObjectProperty<>();

    public PromotionSearchVO() {
        beginCeilProperty.set(LocalDateTime.now().plusMonths(3).toLocalDate());
        endFloorProperty.set(LocalDateTime.now().minusMonths(3).toLocalDate());
        containMemberProperty.set(true);
        containTotalProperty.set(true);
        containCombineProperty.set(true);
    }

    public PromotionSearchVO(PromotionSearchCondition promotionSearchCondition) {
        endFloorProperty.set(promotionSearchCondition.getEndFloor().toLocalDate());
        beginCeilProperty.set(promotionSearchCondition.getBeginCeil().toLocalDate());
        containMemberProperty.set(promotionSearchCondition.getPromotionTypes().contains(PromotionType.MEMBER));
        containCombineProperty.set(promotionSearchCondition.getPromotionTypes().contains(PromotionType.COMBINE));
        containTotalProperty.set(promotionSearchCondition.getPromotionTypes().contains(PromotionType.TOTAL));
        promotionStateProperty.set(promotionSearchCondition.getPromotionState());
    }

    public PromotionSearchCondition toSC() {
        PromotionSearchCondition result = new PromotionSearchCondition();
        result.setEndFloor(LocalDateTime.of(endFloorProperty.get(), LocalTime.MIN));
        result.setBeginCeil(LocalDateTime.of(beginCeilProperty.get(), LocalTime.MIN));
        if (containMemberProperty.get()) {
            result.getPromotionTypes().add(PromotionType.MEMBER);
        }
        if (containCombineProperty.get()) {
            result.getPromotionTypes().add(PromotionType.COMBINE);
        }
        if (containTotalProperty.get()) {
            result.getPromotionTypes().add(PromotionType.TOTAL);
        }
        result.setPromotionState(promotionStateProperty.get());
        return result;
    }

    public LocalDate getEndFloorProperty() {
        return endFloorProperty.get();
    }

    public ObjectProperty<LocalDate> endFloorPropertyProperty() {
        return endFloorProperty;
    }

    public void setEndFloorProperty(LocalDate endFloorProperty) {
        this.endFloorProperty.set(endFloorProperty);
    }

    public LocalDate getBeginCeilProperty() {
        return beginCeilProperty.get();
    }

    public ObjectProperty<LocalDate> beginCeilPropertyProperty() {
        return beginCeilProperty;
    }

    public void setBeginCeilProperty(LocalDate beginCeilProperty) {
        this.beginCeilProperty.set(beginCeilProperty);
    }

    public boolean isContainMemberProperty() {
        return containMemberProperty.get();
    }

    public BooleanProperty containMemberPropertyProperty() {
        return containMemberProperty;
    }

    public void setContainMemberProperty(boolean containMemberProperty) {
        this.containMemberProperty.set(containMemberProperty);
    }

    public boolean isContainCombineProperty() {
        return containCombineProperty.get();
    }

    public BooleanProperty containCombinePropertyProperty() {
        return containCombineProperty;
    }

    public void setContainCombineProperty(boolean containCombineProperty) {
        this.containCombineProperty.set(containCombineProperty);
    }

    public boolean isContainTotalProperty() {
        return containTotalProperty.get();
    }

    public BooleanProperty containTotalPropertyProperty() {
        return containTotalProperty;
    }

    public void setContainTotalProperty(boolean containTotalProperty) {
        this.containTotalProperty.set(containTotalProperty);
    }

    public PromotionState getPromotionStateProperty() {
        return promotionStateProperty.get();
    }

    public ObjectProperty<PromotionState> promotionStatePropertyProperty() {
        return promotionStateProperty;
    }

    public void setPromotionStateProperty(PromotionState promotionStateProperty) {
        this.promotionStateProperty.set(promotionStateProperty);
    }
}
