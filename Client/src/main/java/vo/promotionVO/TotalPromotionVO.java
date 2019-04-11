package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import blService.promotionblService.TotalPromotionblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import javafx.scene.control.Label;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.TotalPromotionPO;
import ui.mainui.PromotionCard;
import ui.managerui.promotionui.promotionDetailPane.PromotionDetailPane;
import ui.managerui.promotionui.promotionDetailPane.TotalPromotionDetailPane;
import ui.salesui.promotion.MyTotalPromotionCard;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TotalPromotionVO extends PromotionVO {
    private double requiredTotal;
    private double tokenAmount;
    private ArrayList<PromotionGoodsItemVO> gifts;


    public TotalPromotionVO() {
    }

    public TotalPromotionVO(TotalPromotionPO promotionPO) {
        super(promotionPO);
        requiredTotal = promotionPO.getRequiredTotal();
        tokenAmount = promotionPO.getTokenAmount();
        gifts = promotionPO.getGifts() == null ? new ArrayList<>()
                : Arrays.stream(promotionPO.getGifts()).map(PromotionGoodsItemVO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    protected String getCodeName() {
        return "ZJCX";
    }

    @Override
    public TotalPromotionPO toPO() {
        TotalPromotionPO result = toPromotionPO(TotalPromotionPO.class);
        result.setRequiredTotal(requiredTotal);
        result.setTokenAmount(tokenAmount);
        result.setGifts(gifts == null ? null : gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
        return result;
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyblServiceFactory.getService(TotalPromotionblService.class);
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return new TotalPromotionDetailPane(this);
    }

//    @Override
//    public Label getInfoLabel() {
//        String content = "总价促销\n"
//                + "代金券金额：" + tokenAmount + "元\n"
//                + "赠品总额：" + gifts.stream().mapToDouble(g -> g.getNum() * g.getUnitPrice()).sum() + "元";
//        return new Label(content);
//    }


    @Override
    public PromotionCard getPromotionCard() {
        MyTotalPromotionCard result = new MyTotalPromotionCard(this);
        if (tokenAmount > 0) {
            result.addTokenNum(tokenAmount);
            return result;
        }
        result.addGifts(gifts.stream().map(PromotionGoodsItemVO::toReceiptGoodsItemVO).collect(Collectors.toCollection(ArrayList::new)));
        return result;
    }

    @Override
    public void modifySalesSell(SalesSellReceiptVO salesSellReceiptVO) {
        salesSellReceiptVO.setGiveTokenAmount(tokenAmount);
        salesSellReceiptVO.setGifts(gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
    }

    /**
     * getters and setters
     * */

    public double getRequiredTotal() {
        return requiredTotal;
    }

    public void setRequiredTotal(double requiredTotal) {
        this.requiredTotal = requiredTotal;
    }

    public double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public ArrayList<PromotionGoodsItemVO> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<PromotionGoodsItemVO> gifts) {
        this.gifts = gifts;
    }
}
