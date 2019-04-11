package mapper;

import po.SalesDetailPO;
import util.SalesDetailInfo;

import java.util.List;
import java.util.Map;

public interface SalesDetailPOMapper {
    List<SalesDetailPO> select(SalesDetailInfo salesDetailInfo);
}

