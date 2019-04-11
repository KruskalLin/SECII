package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import javafx.scene.control.Label;
import po.promotionPO.PromotionPO;
import ui.mainui.PromotionCard;
import ui.managerui.promotionui.promotionDetailPane.PromotionDetailPane;
import util.PromotionState;
import vo.abstractVO.ReceipishVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public abstract class PromotionVO extends ReceipishVO<PromotionVO> {
    protected LocalDateTime beginTime, endTime;
    protected String comment;
    protected PromotionState promotionState;

    public double totalReduce; // 这个还是不用了……

    public int count; // 为了计算达到几重这个促销策略，比如买三送一，买六送二。这个还是不用了…不知为什么有点麻烦…

    public PromotionVO() {
    }

    protected PromotionVO(PromotionPO promotionPO) {
        super(promotionPO);
        this.beginTime = promotionPO.getBeginTime();
        this.endTime = promotionPO.getEndTime();
        this.comment = promotionPO.getComment();
        this.promotionState = promotionPO.getPromotionState();
    }

//    public abstract <T extends PromotionPO> T toPO(); // 这个就不用了


    protected <T extends PromotionPO> T toPromotionPO(Class<T> promotionClass) {
        T result = toReceipishPO(promotionClass);
        result.setBeginTime(beginTime);
        result.setEndTime(endTime);
        result.setComment(comment);
        result.setPromotionState(promotionState);
        return result;
    }

    /**
     * abstract Methods
     * */

    public abstract <T extends PromotionVO> PromotionblService<T> getService() throws RemoteException, NotBoundException, MalformedURLException;

    public abstract PromotionDetailPane getDetailPane();

    public abstract PromotionCard getPromotionCard();

    public abstract void modifySalesSell(SalesSellReceiptVO salesSellReceiptVO);


    /**
     * getters and setters.
     * */

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PromotionState getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(PromotionState promotionState) {
        this.promotionState = promotionState;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
