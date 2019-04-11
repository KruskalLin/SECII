package mapper;

import org.apache.ibatis.annotations.Param;

public interface EstablishMapper {

    public void transfer(@Param("time")String time);

}
