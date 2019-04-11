package ui.salesui.promotion;

import com.jfoenix.controls.JFXProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.controlsfx.control.Rating;
import ui.mainui.GiveGoodsList;
import ui.mainui.PromotionCard;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.promotionVO.MemberPromotionVO;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MyMemberPromotionCard extends PromotionCard {
    public MyMemberPromotionCard(MemberPromotionVO promotionVO) {
        super(promotionVO);
        this.setStyle("-fx-background-color: linear-gradient(to right bottom,rgb(89,103,195),rgb(184,224,238));");
        this.getStylesheets().add("/css/memberPromotion.css");
        title.setText("会员降价");
        num.setText("2");

        Label rate = new Label("要求等级");
        rate.setLayoutX(58);
        rate.setLayoutY(50);
        rate.getStyleClass().add("myLabel");

        Rating rating = new Rating();
        rating.setLayoutX(12);
        rating.setLayoutY(70);
        rating.setDisable(true);
        rating.setRating(promotionVO.getRequiredLevel());

        this.getChildren().addAll(rate, rating);

    }

    public void addDiscountAmount(double rate) {
        Label label = new Label("打折");
        label.getStyleClass().add("myLabel");
        label.setLayoutX(25);
        label.setLayoutY(120);

        Label percent = new Label("");
        percent.setLayoutX(160);
        percent.setLayoutY(140);
        percent.getStyleClass().add("myLabel");

        ProgressBar progressBar = new JFXProgressBar();
        progressBar.getStyleClass().add("custom-jfx-progress-bar");
        progressBar.setPrefWidth(130);
        progressBar.setLayoutX(25);
        progressBar.setLayoutY(140);
        this.getChildren().addAll(label, progressBar, percent);


        // set percent
        progressBar.progressProperty().set(rate);
        percent.setText(NumberFormat.getPercentInstance().format(rate));
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
