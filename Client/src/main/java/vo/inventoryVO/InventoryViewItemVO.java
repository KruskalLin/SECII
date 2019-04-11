package vo.inventoryVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;

public class InventoryViewItemVO extends SelectableVO<InventoryViewItemVO>{
    /*  商品名 */
    private String goodName;
    /*  商品编号 */
    private String goodId;
    /* 入库数量*/
    private int stockInNum;
    /*  入库金额 */
    private double stockInMoney;
    /* 出库数量 */
    private int stockOutNum;
    /* 出库金额 */
    private double stockOutMoney;
    /* 销售数量 */
    private int saleNum;
    /* 销售金额*/
    private double saleMoney;
    /* 进货数量*/
    private int stockPurNum;
    /* 进货金额*/
    private double stockPurMoney;


    public InventoryViewItemVO(String goodName, String goodId, int stockInNum, double stockInMoney, int stockOutNum,
                               double stockOutMoney, int saleNum, double saleMoney, int stockPurNum, double stockPurMoney) {
        this.goodName = goodName;
        this.goodId = goodId;
        this.stockInNum = stockInNum;
        this.stockInMoney = stockInMoney;
        this.stockOutNum = stockOutNum;
        this.stockOutMoney = stockOutMoney;
        this.saleNum = saleNum;
        this.saleMoney = saleMoney;
        this.stockPurNum = stockPurNum;
        this.stockPurMoney = stockPurMoney;
    }

    public InventoryViewItemVO() {
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getStockInNum() {
        return stockInNum;
    }

    public void setStockInNum(int stockInNum) {
        this.stockInNum = stockInNum;
    }

    public double getStockInMoney() {
        return stockInMoney;
    }

    public void setStockInMoney(double stockInMoney) {
        this.stockInMoney = stockInMoney;
    }

    public int getStockOutNum() {
        return stockOutNum;
    }

    public void setStockOutNum(int stockOutNum) {
        this.stockOutNum = stockOutNum;
    }

    public double getStockOutMoney() {
        return stockOutMoney;
    }

    public void setStockOutMoney(double stockOutMoney) {
        this.stockOutMoney = stockOutMoney;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(double saleMoney) {
        this.saleMoney = saleMoney;
    }

    public int getStockPurNum() {
        return stockPurNum;
    }

    public void setStockPurNum(int stockPurNum) {
        this.stockPurNum = stockPurNum;
    }

    public double getStockPurMoney() {
        return stockPurMoney;
    }

    public void setStockPurMoney(double stockPurMoney) {
        this.stockPurMoney = stockPurMoney;
    }



}
