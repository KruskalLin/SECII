package po.AccountInitialPO;

import java.io.Serializable;

public class InitialGoodsPO implements Serializable{

    private String year;
    private String id;
    private String goodName;
    private String goodType;
    private String classifyId;
    private int inventoryNum;
    private double purPrice;

    private double salePrice;
    private double recentPurPrice;
    private double recentSalePrice;
    private int alarmNumber;

    public InitialGoodsPO(){}


    public InitialGoodsPO(String year, String id, String goodName, String goodType, String classifyId, int inventoryNum, double purPrice, double salePrice, double recentPurPrice, double recentSalePrice, int alarmNumber) {
        this.year = year;
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
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
}
