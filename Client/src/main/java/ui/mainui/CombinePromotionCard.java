package ui.mainui;

import javafx.scene.control.Label;

public class CombinePromotionCard extends PromotionCard {

    Label label = new Label("折扣额度");
    Label token = new Label("代金券数");
    Label tokenNum = new Label("");
    Label discount = new Label("");

    public CombinePromotionCard() {
        super();
        this.setStyle("-fx-background-color: linear-gradient(to right bottom,rgb(202,97,156),rgb(245,203,158))");
        title.setText("组合降价");
        this.getStylesheets().add("/css/memberPromotion.css");
        label.setLayoutX(25);
        label.setLayoutY(90);
        label.getStyleClass().add("myLabel");
        discount.setLayoutX(130);
        discount.setLayoutY(90);
        discount.getStyleClass().add("myLabel");
        discount.setScaleX(2.0);
        discount.setScaleY(2.0);
        token.setLayoutX(25);
        token.setLayoutY(140);
        token.getStyleClass().add("myLabel");
        tokenNum.setLayoutX(130);
        tokenNum.setLayoutY(140);
        tokenNum.getStyleClass().add("myLabel");
        tokenNum.setScaleX(2.0);
        tokenNum.setScaleY(2.0);
        this.getChildren().addAll(label, token, tokenNum, discount);
        setTokenNum(5);
        setDiscount(1240);
    }

    public void setTokenNum(int num) {
        this.tokenNum.setText(num + "");
    }

    public void setDiscount(double discount) {
        this.discount.setText(discount + "");
    }
}
