package mapper;

import po.LogPO;

import java.util.ArrayList;

public interface LogDataPOMapper {
    public int getId();
    public ArrayList<LogPO> search();
    public void insert(LogPO logPO);
}
