package po.receiptPO;

import java.io.Serializable;

public class InventoryReceiptGoodsItemPO implements Serializable{
    /**编号*/
    private String id;
    /** 商品名称 */
    private String goodName;
    /** 商品型号 */
    private String goodType;
    /** 商品库存数量 */
    private int inventoryNum;
    /** 当前现实数量*/
    private int factNumber;
    /** 商品赠送数量*/
    private int sendNumber;
    /** 商品警戒数量*/
    private int alarmNumber;

    public InventoryReceiptGoodsItemPO() {
    }

    public InventoryReceiptGoodsItemPO(String id, String goodName, String goodType, int inventoryNum, int factNumber,
                                       int sendNumber, int alarmNumber) {
        this.id = id;
        this.goodName = goodName;
        this.goodType = goodType;
        this.inventoryNum = inventoryNum;
        this.factNumber = factNumber;
        this.sendNumber = sendNumber;
        this.alarmNumber = alarmNumber;
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

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public int getFactNumber() { return factNumber; }

    public void setFactNumber(int factNumber) { this.factNumber = factNumber; }

    public int getsendNumber() {
        return sendNumber;
    }

    public void setsendNumber(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    public int getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(int alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    @Override
    public String toString() {
        return "InventoryReceiptGoodsItemPO{" +
                "id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodType='" + goodType + '\'' +
                ", inventoryNum=" + inventoryNum +
                ", factNumber=" + factNumber +
                ", sendNumber=" + sendNumber +
                ", alarmNumber=" + alarmNumber +
                '}';
    }
}
