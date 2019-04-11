package mapper.generic;

import org.apache.ibatis.annotations.Param;
import po.promotionPO.PromotionPO;
import util.PromotionSearchCondition;
import util.PromotionState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface PromotionPOMapper<T extends PromotionPO> extends ReceipishPOMapper<T> {
    ArrayList<T> selectInEffect(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("promotionState")PromotionState promotionState);
    ArrayList<T> search(PromotionSearchCondition promotionSearchCondition);
}
