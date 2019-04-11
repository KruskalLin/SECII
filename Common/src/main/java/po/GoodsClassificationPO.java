package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsClassificationPO implements Serializable{
    private String id;

    private String _name;

    private String fatherId;

    private String[] childrenId;
    private String[] goodsId;

    public GoodsClassificationPO() {
    }

    public GoodsClassificationPO(String ID, String name, String fatherID) {
        this.id = ID;
        this._name = name;
        this.fatherId = fatherID;
    }

    public GoodsClassificationPO(String ID, String name, String fatherID, String[] childrenId,String[] goodsID) {
        this.id=ID;
        this._name = name;
        this.fatherId = fatherID;
        this.childrenId = childrenId;
        this.goodsId = goodsID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public void setChildrenId(String[] childrenId) {
        this.childrenId = childrenId;
    }

    public void setGoodsId(String[] goodsID) {
        this.goodsId = goodsID;
    }

    public String getId() { return id; }

    public String getName() {
        return _name;
    }

    public String getFatherID() {
        return fatherId;
    }

    public String[] getGoodsId() {
        return goodsId;
    }

    public String[] getChildrenId() {
        return childrenId;
    }

    @Override
    public String toString() {
        return "GoodsClassificationPO{" +
                "id='" + id + '\'' +
                ", _name='" + _name + '\'' +
                ", fatherId='" + fatherId + '\'' + ", childrenId='"+ Arrays.toString(childrenId)+'\''+
                ", goodsId=" + Arrays.toString(goodsId) +
                '}';
    }
}
