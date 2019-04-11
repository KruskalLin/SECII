package ui.mainui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import ui.common.mixer.FXMLLoadableMixer;

import java.text.NumberFormat;

public class MemberPromotionCard extends PromotionCard {

    ProgressBar progressBar = new JFXProgressBar();
    Rating rating = new Rating();
    Label label = new Label("打折");
    Label rate = new Label("要求等级");
    Label token = new Label("代金券数");
    Label tokenNum = new Label("");
    Label percent = new Label("");

    public MemberPromotionCard() {
        super();
        this.setStyle("-fx-background-color: linear-gradient(to right bottom,rgb(89,103,195),rgb(184,224,238));");
        title.setText("会员降价");
        num.setText("2");
        progressBar.getStyleClass().add("custom-jfx-progress-bar");
        progressBar.setPrefWidth(130);
        this.getStylesheets().add("/css/memberPromotion.css");
        this.getChildren().addAll(progressBar, label, rating, rate, percent, token, tokenNum);
        progressBar.setLayoutX(25);
        progressBar.setLayoutY(140);
        percent.setLayoutX(160);
        percent.setLayoutY(140);
        percent.getStyleClass().add("myLabel");
        token.setLayoutX(25);
        token.setLayoutY(180);
        token.getStyleClass().add("myLabel");
        tokenNum.setLayoutX(130);
        tokenNum.setLayoutY(180);
        tokenNum.getStyleClass().add("myLabel");
        tokenNum.setScaleX(2.0);
        tokenNum.setScaleY(2.0);
        rating.setLayoutX(12);
        rating.setLayoutY(70);
        rate.setLayoutX(58);
        rate.setLayoutY(50);
        rate.getStyleClass().add("myLabel");
        rating.setDisable(true);
        label.getStyleClass().add("myLabel");
        label.setLayoutX(25);
        label.setLayoutY(120);
        setDiscountRate(0.5);
        setTokenNum(5);
    }

    public void setDiscountRate(double rate) {
        if (rate > 1 || rate < 0) {
            return;
        } else {
            progressBar.progressProperty().set(rate);
            this.percent.setText(NumberFormat.getPercentInstance().format(rate));
        }
    }

    public void setMemberRate(int rate) {
        this.rating.setRating(rate);
    }

    public void setTokenNum(int num) {
        this.tokenNum.setText(num + "");
    }


}
