package mapper;


import po.receiptPO.ReceiptPO;
import util.BusinessProgressInfo;

import java.util.List;
import java.util.Map;

public interface BusinessProgressMapper {

    public List<ReceiptPO> select(Map<String,Object> map);
}
