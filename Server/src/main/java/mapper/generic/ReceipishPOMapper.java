package mapper.generic;

import org.apache.ibatis.annotations.Param;
import po.generic.ReceipishPO;

import java.time.LocalDateTime;

public interface ReceipishPOMapper<T extends ReceipishPO> {
    int getDayId(@Param("today") LocalDateTime today);
    void insert(T receipishPO);
    void update(T receipishPO);
    void delete(T receipishPO);
    T selectByMold(T receipishPO);
}
