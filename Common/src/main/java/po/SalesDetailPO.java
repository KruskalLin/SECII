package po;

import java.time.LocalDateTime;

public class SalesDetailPO {
    private LocalDateTime date;
    private String goodsName;
    private int goodsID;
    private int number;
    private double price;
    private double total;
    private int clientID;
    private String clerkName;
    private int stockID;


    public SalesDetailPO(LocalDateTime date, String goodsName, int goodsID, int number, double price, double total, int clientID, String clerkName, int stockID) {
        this.date = date;
        this.goodsName = goodsName;
        this.goodsID = goodsID;
        this.number = number;
        this.price = price;
        this.total = total;
        this.clientID = clientID;
        this.clerkName = clerkName;
        this.stockID = stockID;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }
}
