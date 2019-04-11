package vo.inventoryVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.scene.image.Image;
import ui.inventoryui.goodsui.GoodDetailPane;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GoodsVO extends SelectableVO<GoodsVO> implements Serializable{
    /**编号*/
    private String id;
    /** 商品名称 */
    private String goodName;
    /** 商品型号 */
    private String goodType;
    /** 商品分类ID */
    private String classifyId;
    /** 商品库存数量 */
    private int inventoryNum;
    /** 商品进价 */
    private double purPrice;
    /** 商品零售价 */
    private double salePrice;
    /** 商品最近进价 */
    private double recentPurPrice;
    /** 商品最近零售价 */
    private double recentSalePrice;
    /** 商品警戒数量 */
    private int alarmNumber;
    /** 来表示是否被选中*/
    private boolean multiple = true;
    /** 是否被进货*/
    private int isStockPur;

    public boolean mark = false; // 这个是为了自己写搜索写的方便。by 连

    public GoodsVO() {
    }

    public GoodsVO(String id) {
        this.id = id;
    }

    public GoodsVO(String id, String goodName, String goodType, String classifyId, int inventoryNum, double purPrice,
                   double salePrice, double recentPurPrice, double recentSalePrice, int alarmNumber, int isStockPur) {
        this.id = id;
        this.goodName = goodName;
        this.goodType = goodType;
        this.classifyId = classifyId;
        this.inventoryNum = inventoryNum;
        this.purPrice = purPrice;
        this.salePrice = salePrice;
        this.recentPurPrice = recentPurPrice;
        this.recentSalePrice = recentSalePrice;
        this.alarmNumber = alarmNumber;
        this.isStockPur = isStockPur;
    }

    public int getIsStockPur() {
        return isStockPur;
    }

    public void setIsStockPur(int isStockPur) {
        this.isStockPur = isStockPur;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRecentPurPrice() {
        return recentPurPrice;
    }

    public void setRecentPurPrice(double recentPurPrice) {
        this.recentPurPrice = recentPurPrice;
    }

    public double getRecentSalePrice() {
        return recentSalePrice;
    }

    public void setRecentSalePrice(double recentSalePrice) {
        this.recentSalePrice = recentSalePrice;
    }

    public int getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(int alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public GoodDetailPane getDetailPane() throws RemoteException, NotBoundException, MalformedURLException {return new GoodDetailPane(this);}

    @Override
    public String toString() {
        return "GoodsVO{" +
                "id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodType='" + goodType + '\'' +
                ", classifyId='" + classifyId + '\'' +
                ", inventoryNum=" + inventoryNum +
                ", purPrice=" + purPrice +
                ", salePrice=" + salePrice +
                ", recentPurPrice=" + recentPurPrice +
                ", recentSalePrice=" + recentSalePrice +
                ", alarmNumber=" + alarmNumber +
                ", multiple=" + multiple +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsVO goodsVO = (GoodsVO) o;

        return id.equals(goodsVO.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
