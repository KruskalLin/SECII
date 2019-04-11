package vo.receiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.promotionPO.PromotionGoodsItemPO;
import po.receiptPO.SalesSellReceiptPO;
import ui.salesui.salesSellui.SalesSellDetailPane;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesSellReceiptVO extends SalesReceiptVO {

    private PromotionGoodsItemPO[] gifts;
    private double giveTokenAmount;
    private int memberLevel; // TODO 这个是新加的



    public SalesSellReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum, PromotionGoodsItemPO[] gifts, double giveTokenAmount) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, items, comment, discountAmount, tokenAmount, originSum);
        this.gifts = gifts;
        this.giveTokenAmount = giveTokenAmount;
    }

    public SalesSellReceiptVO() {
    }

    public SalesSellReceiptVO(SalesSellReceiptPO salesSellReceiptPO) throws RemoteException, NotBoundException, MalformedURLException {
        super(salesSellReceiptPO);
        this.gifts = salesSellReceiptPO.getGifts();
        this.giveTokenAmount =salesSellReceiptPO.getGiveTokenAmount();
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public PromotionGoodsItemPO[] getGifts() {
        return gifts;
    }

    public void setGifts(PromotionGoodsItemPO[] gifts) {
        this.gifts = gifts;
    }

    public double getGiveTokenAmount() {
        return giveTokenAmount;
    }

    public void setGiveTokenAmount(double giveTokenAmount) {
        this.giveTokenAmount = giveTokenAmount;
    }

    @Override
    public CheckInfo<SalesSellReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getSalesSellReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new SalesSellDetailPane(this);
    }

    @Override
    public SalesSellListVO toListVO() {
        SalesSellListVO salesSellListVO = new SalesSellListVO(this);
        return salesSellListVO;
    }

    @Override
    protected String getCodeName() {
        return "XSD";
    }

    @Override
    public SalesSellReceiptPO toPO() {
        SalesSellReceiptPO salesSellReceiptPO = toSalesReceiptPO(SalesSellReceiptPO.class);
        salesSellReceiptPO.setGifts(gifts);
        salesSellReceiptPO.setGiveTokenAmount(giveTokenAmount);
        return salesSellReceiptPO;
    }

}