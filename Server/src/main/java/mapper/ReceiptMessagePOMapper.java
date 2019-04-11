package mapper;

import org.apache.ibatis.annotations.Param;
import po.ReceiptMessagePO;
import util.UserCategory;

import java.util.ArrayList;

public interface ReceiptMessagePOMapper {
    void insert(ReceiptMessagePO messagePO);

    ArrayList<ReceiptMessagePO> selectByUser(@Param("toUserId") int userId);

    ArrayList<ReceiptMessagePO> selectByUserCategory(@Param("toUserCategory") UserCategory userCategory);

    ReceiptMessagePO selectByMold(ReceiptMessagePO messagePO);

    void delete(ReceiptMessagePO messagePO);
}
