package ui.salesui.promotion;

import javafx.scene.control.Label;
import ui.mainui.GiveGoodsList;
import ui.mainui.PromotionCard;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.PromotionVO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MyCombinePromotionCard extends PromotionCard {
    private Label discount = new Label("");
    private GiveGoodsList giveGoodsList;

    public MyCombinePromotionCard(CombinePromotionVO promotionVO) {
        super(promotionVO);
        this.setStyle("-fx-background-color: linear-gradient(to right bottom,rgb(202,97,156),rgb(245,203,158))");
        this.getStylesheets().add("/css/memberPromotion.css");
        title.setText("组合降价");
        num.setText("1");

        Label label = new Label("折扣额度");
        label.setLayoutX(25);
        label.setLayoutY(70);
        label.getStyleClass().add("myLabel");

        discount.setLayoutX(130);
        discount.setLayoutY(70);
        discount.getStyleClass().add("myLabel");
        discount.setScaleX(2.0);
        discount.setScaleY(2.0);

        giveGoodsList = new GiveGoodsList();
        giveGoodsList.setPrefWidth(190);
        giveGoodsList.setPrefHeight(150);
        giveGoodsList.setLayoutY(90);
        this.getChildren().addAll(label, discount, giveGoodsList);

        setDiscount(promotionVO.getDiscountAmount());
        setList(promotionVO.getGoodsCombination().stream().map(PromotionGoodsItemVO::toReceiptGoodsItemVO).collect(Collectors.toCollection(ArrayList::new)));
    }

    public void setDiscount(double discount) {
        this.discount.setText(discount + "");
    }

    public void setList(ArrayList<ReceiptGoodsItemVO> arrayList) {
        giveGoodsList.setList(arrayList);
    }
}
