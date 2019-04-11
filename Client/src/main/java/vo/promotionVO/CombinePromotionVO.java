package vo.promotionVO;

import blService.promotionblService.CombinePromotionblService;
import blService.promotionblService.PromotionblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import javafx.scene.control.Label;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.CombinePromotionPO;
import ui.mainui.PromotionCard;
import ui.managerui.promotionui.promotionDetailPane.CombinePromotionDetailPane;
import ui.managerui.promotionui.promotionDetailPane.PromotionDetailPane;
import ui.salesui.promotion.MyCombinePromotionCard;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinePromotionVO extends PromotionVO {
    private ArrayList<PromotionGoodsItemVO> goodsCombination;
    private double discountAmount;

    public CombinePromotionVO() {
    }

    public CombinePromotionVO(CombinePromotionPO promotionPO) {
        super(promotionPO);

        discountAmount = promotionPO.getDiscountAmount();
        goodsCombination = promotionPO.getGoodsCombination() == null ? new ArrayList<>()
                : Arrays.stream(promotionPO.getGoodsCombination()).map(PromotionGoodsItemVO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    protected String getCodeName() {
        return "TJBCX";
    }

    @Override // unchecked overriding…
    public CombinePromotionPO toPO() {
        CombinePromotionPO result = toPromotionPO(CombinePromotionPO.class);
        result.setDiscountAmount(discountAmount);
        result.setGoodsCombination(goodsCombination == null ? null : goodsCombination.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
        return result;
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyblServiceFactory.getService(CombinePromotionblService.class);
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return new CombinePromotionDetailPane(this);
    }

//    @Override
//    public Label getInfoLabel() {
//        String content = "特价包促销\n"
//                + "降价总额：" + discountAmount * count + "元\n";
//        return new Label(content);
//    }


    @Override
    public PromotionCard getPromotionCard() {
        return new MyCombinePromotionCard(this);
    }

    @Override
    public void modifySalesSell(SalesSellReceiptVO salesSellReceiptVO) {
        salesSellReceiptVO.setDiscountAmount(discountAmount);
    }

    public ArrayList<PromotionGoodsItemVO> getGoodsCombination() {
        return goodsCombination;
    }

    public void setGoodsCombination(ArrayList<PromotionGoodsItemVO> goodsCombination) {
        this.goodsCombination = goodsCombination;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

//    @Override
//    public String toString() {
//        return "CombinePromotion";
//    }

//    public static void main(String[] args) {
//        LocalDateTime createTime = LocalDateTime.now();
//        String id = "TJBCX-" + String.valueOf(createTime.getYear()) + createTime.getMonthValue() + createTime.getDayOfMonth() + "-%05d";
//        System.out.println((String.format(id, 54214)));
//
//        System.out.println("00001".substring(3));
//        System.out.println(Integer.parseInt("00001".substring(3)));
//
//    }
}
