package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import po.BusinessConditionPO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface BusinessConditionPOMapper {
    void insert(BusinessConditionPO businessConditionPO);
    ArrayList<BusinessConditionPO> select(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
}
