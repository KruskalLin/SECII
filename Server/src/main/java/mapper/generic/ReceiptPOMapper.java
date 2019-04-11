package mapper.generic;

import org.apache.ibatis.annotations.Param;
import po.promotionPO.PromotionPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.ReceiptSearchCondition;
import util.RespectiveReceiptSearchCondition;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReceiptPOMapper<T extends ReceiptPO> extends ReceipishPOMapper<T> {
    ArrayList<T> selectBetween(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
    ArrayList<T> selectByState(@Param("receiptState") ReceiptState receiptState);
    ArrayList<T> searchForBusiness(ReceiptSearchCondition receiptSearchCondition);
    ArrayList<T> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition);
}
