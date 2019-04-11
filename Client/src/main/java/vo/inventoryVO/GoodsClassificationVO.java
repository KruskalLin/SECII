package vo.inventoryVO;

import java.util.List;

public class GoodsClassificationVO implements Comparable<GoodsClassificationVO>{
    private String ID = "1";

    private String name;

    private String fatherID;

    private List<String> childrenId;

    private List<String> goodsID;

    public GoodsClassificationVO() {
    }

    public GoodsClassificationVO(String ID, String name, String fatherID) {
        this.ID = ID;
        this.name = name;
        this.fatherID = fatherID;
    }

    public GoodsClassificationVO(String ID, String name, String fatherID, List<String> goodsID, List<String> childrenId) {
        this.ID = ID;
        this.name = name;
        this.fatherID = fatherID;
        this.goodsID = goodsID;
        this.childrenId = childrenId;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public List<String> getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(List<String> goodsID) {
        this.goodsID = goodsID;
    }

    public List<String> getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(List<String> childrenId) {
        this.childrenId = childrenId;
    }

    @Override
    public int compareTo(GoodsClassificationVO o) {
        return ID.compareTo(o.ID);
    }

    @Override
    public String toString() {
        return name;
    }
}
