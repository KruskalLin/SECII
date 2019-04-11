package vo.inventoryVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;

public class InventoryCheckItemVO extends SelectableVO<InventoryCheckItemVO> {
    /**
     * 商品名
     */
    private String goodName;
    /**
     * 编号
     */
    private String goodId;
    /**
     * 库存数量
     */
    private int inventoryNum;
    /**
     * 每个商品价格
     */
    private double avePrice;
    /**
     * 出场日期
     */
    private String StockOutDate;
    /* 批次*/
    private String batch;
    /* 批号*/
    private String batchNum;

    public InventoryCheckItemVO() {
    }

    public InventoryCheckItemVO(String goodName, String id, int inventoryNum, double avePrice, String date, String batch, String batchNum) {
        this.goodName = goodName;
        this.goodId = id;
        this.inventoryNum = inventoryNum;
        this.avePrice = avePrice;
        this.StockOutDate = date;
        this.batch = batch;
        this.batchNum = batchNum;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(double avePrice) {
        this.avePrice = avePrice;
    }

    public String getStockOutDate() {
        return StockOutDate;
    }

    public void setStockOutDate(String date) {
        this.StockOutDate = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchinventoryNum(String batchNum) {
        this.batchNum = batchNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryCheckItemVO that = (InventoryCheckItemVO) o;

        return goodId != null ? goodId.equals(that.goodId) : that.goodId == null;
    }

    @Override
    public int hashCode() {
        return goodId != null ? goodId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InventoryCheckItemVO{" +
                "goodName='" + goodName + '\'' +
                ", goodId='" + goodId + '\'' +
                ", inventoryNum=" + inventoryNum +
                ", avePrice=" + avePrice +
                ", StockOutDate='" + StockOutDate + '\'' +
                ", batch='" + batch + '\'' +
                ", batchNum='" + batchNum + '\'' +
                '}';
    }
}