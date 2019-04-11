package vo;

import po.LogPO;

import java.util.ArrayList;

public class LogVO {
    ArrayList<LogPO> arrayList;

    public LogVO(ArrayList<LogPO> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<LogPO> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<LogPO> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "LogVO{}";
    }
}
