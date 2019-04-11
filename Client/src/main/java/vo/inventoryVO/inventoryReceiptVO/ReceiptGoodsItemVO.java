package vo.inventoryVO.inventoryReceiptVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.io.Serializable;

public class ReceiptGoodsItemVO extends RecursiveTreeObject<ReceiptGoodsItemVO> implements Serializable {
    private StringProperty goodsName = new SimpleStringProperty();
    private StringProperty goodsId = new SimpleStringProperty();
    private StringProperty goodsType = new SimpleStringProperty();
    private IntegerProperty inventoryNum = new SimpleIntegerProperty();
    private IntegerProperty factNum = new SimpleIntegerProperty();
    private IntegerProperty sendNum = new SimpleIntegerProperty();
    private IntegerProperty warningNum = new SimpleIntegerProperty();
    private double price;

    /**
     * construtors
     * */
    public ReceiptGoodsItemVO() {
        this.goodsName = new SimpleStringProperty();
        this.goodsId = new SimpleStringProperty();
        this.goodsType = new SimpleStringProperty();
        this.inventoryNum = new SimpleIntegerProperty();
        this.factNum = new SimpleIntegerProperty();
        this.sendNum = new SimpleIntegerProperty();
        this.warningNum = new SimpleIntegerProperty();
    }

    public ReceiptGoodsItemVO(String goodsName, String goodsId, String goodsType,
                              Integer inventoryNum, Integer factNum, Integer sendNum, Integer warningNum) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.goodsType = new SimpleStringProperty(goodsType);
        this.inventoryNum = new SimpleIntegerProperty(inventoryNum);
        this.factNum = new SimpleIntegerProperty(factNum);
        this.sendNum = new SimpleIntegerProperty(sendNum);
        this.warningNum = new SimpleIntegerProperty(warningNum);
    }

    public ReceiptGoodsItemVO(String goodsName, String goodsId, Integer inventoryNum, Integer sendNum) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.inventoryNum = new SimpleIntegerProperty(inventoryNum);
        this.sendNum = new SimpleIntegerProperty(sendNum);
    }

    public ReceiptGoodsItemVO(GoodsVO goodsVO) {
        this.goodsId = new SimpleStringProperty(goodsVO.getId());
        this.goodsName = new SimpleStringProperty(goodsVO.getGoodName());
        this.goodsType = new SimpleStringProperty(goodsVO.getGoodType());
        this.inventoryNum = new SimpleIntegerProperty(goodsVO.getInventoryNum());
        this.warningNum = new SimpleIntegerProperty(goodsVO.getAlarmNumber());
    }

    /**
     * toOtherVO
     * */
    public ListGoodsItemVO toListGoodsItemVO() {
        ListGoodsItemVO result = new ListGoodsItemVO();
        result.setGoodsName(goodsName.get());
        result.setGoodsId(goodsId.get());
        result.setPrice(price);
        result.setGoodsNum(0);
        return result;
    }

    public PromotionGoodsItemVO toPromotionGoodsItemVO() {
        PromotionGoodsItemVO result = new PromotionGoodsItemVO();
        result.setId(goodsId.get());
        result.setName(goodsName.get());
        result.setUnitPrice(price);
        result.setNum(0);
        return result;
    }


    public String getGoodsType() {
        return goodsType.get();
    }

    public StringProperty goodsTypeProperty() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType.set(goodsType);
    }

    public String getGoodsName() {
        return goodsName.get();
    }

    public StringProperty goodsNameProperty() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName.set(goodsName);
    }

    public String getGoodsId() {
        return goodsId.get();
    }

    public StringProperty goodsIdProperty() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId.set(goodsId);
    }

    public int getInventoryNum() {
        return inventoryNum.get();
    }

    public IntegerProperty inventoryNumProperty() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum.set(inventoryNum);
    }

    public int getFactNum() {
        return factNum.get();
    }

    public IntegerProperty factNumProperty() {
        return factNum;
    }

    public void setFactNum(int factNum) {
        this.factNum.set(factNum);
    }

    public int getSendNum() {
        return sendNum.get();
    }

    public IntegerProperty sendNumProperty() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum.set(sendNum);
    }

    public int getWarningNum() {
        return warningNum.get();
    }

    public IntegerProperty warningNumProperty() {
        return warningNum;
    }

    public void setWarningNum(int warningNum) {
        this.warningNum.set(warningNum);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReceiptGoodsItemVO{" +
                "goodsName=" + goodsName +
                ", goodsId=" + goodsId +
                '}';
    }
}
