package po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BusinessConditionPO implements Serializable{

    private LocalDateTime date;

    private double salesIncome; //  销售单，销售单中的折让后总价
    private double overFlowIncome; // 报溢单中，数量*零售价，这两个都在报溢单的属性里。
    private double purPriceAdjustIncome; // 进货单，最新的进货单价，如果需要更新最近进价，同时也要用最近进价的delta * 当前库存数量来计算这条记录。（需要库存人员和销售人员协调接口）
    private double priceDiffIncome; // 进货退货单，商品数量 * (该单中的单价 - 商品属性里的最近进价)。（需要库存人员和销售人员协调接口）
    private double tokenIncome; // 销售单中，代金券 - 折让后总价是正值的时候。

    private double discount; // 销售单，折让金额。

    private double purCost; // 进货单，总价
    private double damageCost; // 报损单，数量*零售价
    private double giftCost; // 赠送单，数量*单价

    // 总结：库存人员和销售人员在审批通过后调用的那个方法里面，要加上对这个经营情况的记录。
    // 对于这个记录：
    // 库存人员需要考虑的单据：报溢单的overFlowIncome；报损单的damageCost；赠送单的giftCost。
    // 销售人员需要考虑的单据：销售单需要考虑salesIncome, tokenIncome, discount；进货单的purPriceAdjustIncome, purCost；进货退货单的priceDiffIncome。
    // 其中priceDiffIncome和purPriceAdjustIncome需要库存人员和销售人员协调接口，以决定由谁来写和怎么写的问题。


    public BusinessConditionPO() {
    }

    public BusinessConditionPO(LocalDateTime date, double salesIncome, double overFlowIncome, double purPriceAdjustIncome, double priceDifferenceIncome, double tokenIncome, double discount, double purCost, double damageCost, double giftCost) {
        this.date = date;
        this.salesIncome = salesIncome;
        this.overFlowIncome = overFlowIncome;
        this.purPriceAdjustIncome = purPriceAdjustIncome;
        this.priceDiffIncome = priceDifferenceIncome;
        this.tokenIncome = tokenIncome;
        this.discount = discount;
        this.purCost = purCost;
        this.damageCost = damageCost;
        this.giftCost = giftCost;
    }

    /**
     * 一些计算的方法
     * */
    public BusinessConditionPO add(BusinessConditionPO that) {
        this.salesIncome += that.salesIncome;
        this.overFlowIncome += that.overFlowIncome;
        this.purPriceAdjustIncome += that.purPriceAdjustIncome;
        this.priceDiffIncome += that.priceDiffIncome;
        this.tokenIncome += that.tokenIncome;
        this.discount += that.discount;
        this.purCost += that.purCost;
        this.damageCost += that.damageCost;
        this.giftCost += that.giftCost;
        return this;
    }

    public double getTotalProfit() {
        return getTotalIncome() - getTotalCost();
    }

    public double getTotalIncome() {
        return salesIncome + overFlowIncome + purPriceAdjustIncome + priceDiffIncome + tokenIncome;
    }

    public double getTotalCost() {
        return purCost + damageCost + giftCost;
    }

    /**
     * 自动生成的getter和setter
     * */

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getSalesIncome() {
        return salesIncome;
    }

    public void setSalesIncome(double salesIncome) {
        this.salesIncome = salesIncome;
    }

    public double getOverFlowIncome() {
        return overFlowIncome;
    }

    public void setOverFlowIncome(double overFlowIncome) {
        this.overFlowIncome = overFlowIncome;
    }

    public double getPurPriceAdjustIncome() {
        return purPriceAdjustIncome;
    }

    public void setPurPriceAdjustIncome(double purPriceAdjustIncome) {
        this.purPriceAdjustIncome = purPriceAdjustIncome;
    }

    public double getPriceDiffIncome() {
        return priceDiffIncome;
    }

    public void setPriceDiffIncome(double priceDiffIncome) {
        this.priceDiffIncome = priceDiffIncome;
    }

    public double getTokenIncome() {
        return tokenIncome;
    }

    public void setTokenIncome(double tokenIncome) {
        this.tokenIncome = tokenIncome;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPurCost() {
        return purCost;
    }

    public void setPurCost(double purCost) {
        this.purCost = purCost;
    }

    public double getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(double damageCost) {
        this.damageCost = damageCost;
    }

    public double getGiftCost() {
        return giftCost;
    }

    public void setGiftCost(double giftCost) {
        this.giftCost = giftCost;
    }
}
