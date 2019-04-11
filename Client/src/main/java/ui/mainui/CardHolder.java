package ui.mainui;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.layout.AnchorPane;

import javafx.util.Duration;


public class CardHolder {
    private Duration duration;
    private PromotionCard promotionCard;
    private AnchorPane view;
    private CardPlace cardPlace;
    private DoubleProperty translateX;
    private DoubleProperty translateY;
    private DoubleProperty scaleX;
    private DoubleProperty scaleY;

    public CardHolder(Duration duration, PromotionCard promotionCard, AnchorPane view, CardPlace cardPlace) {
        this.duration = duration;
        this.view = view;
        this.cardPlace = cardPlace;
        this.promotionCard = promotionCard;
        translateX = new SimpleDoubleProperty(0);
        translateY = new SimpleDoubleProperty(0);
        scaleX = new SimpleDoubleProperty(1.0);
        scaleY = new SimpleDoubleProperty(1.0);
        promotionCard.translateXProperty().bind(translateX);
        promotionCard.translateYProperty().bind(translateY);
        promotionCard.scaleXProperty().bind(scaleX);
        promotionCard.scaleYProperty().bind(scaleY);
        promotionCard.setLayoutY(70);
        promotionCard.setLayoutX(200);
        init();
    }


    public void init() {
        view.getChildren().add(promotionCard);

        switch (cardPlace) {
            case Left:
                translateX.set(-170);
                translateY.set(5);
                scaleX.set(0.873);
                scaleY.set(0.873);
                break;
            case Right:
                translateX.set(170);
                translateY.set(5);
                scaleX.set(0.873);
                scaleY.set(0.873);
                break;
        }
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void MTL() {
        KeyFrame keyFrame = new KeyFrame(this.duration,
                new KeyValue(translateX, 0, Interpolator.EASE_BOTH),
                new KeyValue(translateX, -170, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame1 = new KeyFrame(this.duration,
                new KeyValue(translateY, 0, Interpolator.EASE_BOTH),
                new KeyValue(translateY, 5, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame2 = new KeyFrame(this.duration,
                new KeyValue(scaleX, 1.0, Interpolator.EASE_BOTH),
                new KeyValue(scaleX, 0.873, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame3 = new KeyFrame(this.duration,
                new KeyValue(scaleY, 1.0, Interpolator.EASE_BOTH),
                new KeyValue(scaleY, 0.873, Interpolator.EASE_BOTH)
        );
        Timeline timeline = new Timeline(keyFrame);
        Timeline timeline2 = new Timeline(keyFrame1);
        Timeline timeline3 = new Timeline(keyFrame2);
        Timeline timeline4 = new Timeline(keyFrame3);
        timeline.play();
        timeline2.play();
        timeline3.play();
        timeline4.play();
    }

    public void LTM() {
        promotionCard.toFront();
        KeyFrame keyFrame = new KeyFrame(this.duration,
                new KeyValue(translateX, 170, Interpolator.EASE_BOTH),
                new KeyValue(translateX, 0, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame1 = new KeyFrame(this.duration,
                new KeyValue(translateY, -5, Interpolator.EASE_BOTH),
                new KeyValue(translateY, 0, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame2 = new KeyFrame(this.duration,
                new KeyValue(scaleX, 0.873, Interpolator.EASE_BOTH),
                new KeyValue(scaleX, 1.0, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame3 = new KeyFrame(this.duration,
                new KeyValue(scaleY, 0.873, Interpolator.EASE_BOTH),
                new KeyValue(scaleY, 1.0, Interpolator.EASE_BOTH)
        );
        Timeline timeline = new Timeline(keyFrame);
        Timeline timeline2 = new Timeline(keyFrame1);
        Timeline timeline3 = new Timeline(keyFrame2);
        Timeline timeline4 = new Timeline(keyFrame3);
        timeline.play();
        timeline2.play();
        timeline3.play();
        timeline4.play();
    }

    public void RTM() {
        promotionCard.toFront();
        KeyFrame keyFrame = new KeyFrame(this.duration,
                new KeyValue(translateX, 170, Interpolator.EASE_BOTH),
                new KeyValue(translateX, 0, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame1 = new KeyFrame(this.duration,
                new KeyValue(translateY, 0, Interpolator.EASE_BOTH),
                new KeyValue(translateY, -5, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame2 = new KeyFrame(this.duration,
                new KeyValue(scaleX, 0.873, Interpolator.EASE_BOTH),
                new KeyValue(scaleX, 1.0, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame3 = new KeyFrame(this.duration,
                new KeyValue(scaleY, 0.873, Interpolator.EASE_BOTH),
                new KeyValue(scaleY, 1.0, Interpolator.EASE_BOTH)
        );
        Timeline timeline = new Timeline(keyFrame);
        Timeline timeline2 = new Timeline(keyFrame1);
        Timeline timeline3 = new Timeline(keyFrame2);
        Timeline timeline4 = new Timeline(keyFrame3);
        timeline.play();
        timeline2.play();
        timeline3.play();
        timeline4.play();
    }

    public void MTR() {
        KeyFrame keyFrame = new KeyFrame(this.duration,
                new KeyValue(translateX, 0, Interpolator.EASE_BOTH),
                new KeyValue(translateX, 170, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame1 = new KeyFrame(this.duration,
                new KeyValue(translateY, 0, Interpolator.EASE_BOTH),
                new KeyValue(translateY, 5, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame2 = new KeyFrame(this.duration,
                new KeyValue(scaleX, 1.0, Interpolator.EASE_BOTH),
                new KeyValue(scaleX, 0.873, Interpolator.EASE_BOTH)
        );
        KeyFrame keyFrame3 = new KeyFrame(this.duration,
                new KeyValue(scaleY, 1.0, Interpolator.EASE_BOTH),
                new KeyValue(scaleY, 0.873, Interpolator.EASE_BOTH)
        );
        Timeline timeline = new Timeline(keyFrame);
        Timeline timeline2 = new Timeline(keyFrame1);
        Timeline timeline3 = new Timeline(keyFrame2);
        Timeline timeline4 = new Timeline(keyFrame3);
        timeline.play();
        timeline2.play();
        timeline3.play();
        timeline4.play();
    }

    public PromotionCard getPromotionCard() {
        return promotionCard;
    }

    public void setPromotionCard(PromotionCard promotionCard) {
        this.promotionCard = promotionCard;
    }
}
