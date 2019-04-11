package ui.salesui.promotion;

import javafx.scene.control.Label;
import ui.mainui.GiveGoodsList;
import ui.mainui.PromotionCard;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.promotionVO.TotalPromotionVO;

import java.util.ArrayList;

public class MyTotalPromotionCard extends PromotionCard {
    public MyTotalPromotionCard(TotalPromotionVO promotionVO) {
        super(promotionVO);
        this.setStyle("-fx-background-color: linear-gradient(to right bottom,rgb(253,139,166),rgb(199,146,245))");
        title.setText("总价降价");
        num.setText("3");
        this.getStylesheets().add("/css/memberPromotion.css");

        Label label = new Label("要求总价");
        label.setLayoutX(25);
        label.setLayoutY(70);
        label.getStyleClass().add("myLabel");

        Label requiredTotal = new Label("" + promotionVO.getRequiredTotal());
        requiredTotal.setLayoutX(130);
        requiredTotal.setLayoutY(70);
        requiredTotal.getStyleClass().add("myLabel");
        requiredTotal.setScaleX(2.0);
        requiredTotal.setScaleY(2.0);

        this.getChildren().addAll(label, requiredTotal);
    }

    public void addTokenNum(double num) {
        Label token = new Label("代金券数");
        token.setLayoutX(25);
        token.setLayoutY(180);
        token.getStyleClass().add("myLabel");

        Label tokenNum = new Label("");
        tokenNum.setLayoutX(130);
        tokenNum.setLayoutY(180);
        tokenNum.getStyleClass().add("myLabel");
        tokenNum.setScaleX(2.0);
        tokenNum.setScaleY(2.0);

        this.getChildren().addAll(token, tokenNum);

        tokenNum.setText(num + "");
    }

    public void addGifts(ArrayList<ReceiptGoodsItemVO> arrayList) {
        GiveGoodsList giveGoodsList = new GiveGoodsList();
        giveGoodsList.setPrefWidth(190);
        giveGoodsList.setPrefHeight(150);
        giveGoodsList.setLayoutY(90);
        this.getChildren().add(giveGoodsList);

        giveGoodsList.setList(arrayList);
    }
}
