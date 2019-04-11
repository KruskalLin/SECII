package ui.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;
import java.util.function.Function;

public class PaneSwitchAnimation {

    private StackPane view;
    private Duration duration;
    private Function<PaneSwitchAnimation, List<KeyFrame>> animationProducer;
    private Timeline animation;
    private ImageView placeholder;

    public PaneSwitchAnimation(Duration duration, StackPane view) {
        this.view = view;
        this.duration = duration;
        placeholder = new ImageView();
        placeholder.setPreserveRatio(true);
        placeholder.setSmooth(true);
    }

    public void setAnimation(ContainerAnimations animation) {
        this.animationProducer = animation.getAnimationProducer();
    }

    public void setNode(Node node) {
        updatePlaceholder(node);
        if (animation != null) {
            animation.stop();
        }
        animation = new Timeline();
        animation.getKeyFrames().addAll(animationProducer.apply(this));
        animation.getKeyFrames().add(new KeyFrame(duration, (e) -> clearPlaceholder()));
        animation.play();
    }


    public ImageView getPlaceholder() {
        return placeholder;
    }


    public Duration getDuration() {
        return duration;
    }

    public StackPane getView() {
        return view;
    }

    public void setView(StackPane view) {
        this.view = view;
    }

    private void clearPlaceholder() {
        view.getChildren().remove(placeholder);
    }

    private void updatePlaceholder(Node newView) {
//        System.out.println(newView);
        if (view.getWidth() > 0 && view.getHeight() > 0) {
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            Image placeholderImage = view.snapshot(parameters,
                    new WritableImage((int) view.getWidth(), (int) view.getHeight()));
            placeholder.setImage(placeholderImage);
            placeholder.setFitWidth(placeholderImage.getWidth());
            placeholder.setFitHeight(placeholderImage.getHeight());
        } else {
            placeholder.setImage(null);
        }
        placeholder.setVisible(true);
        placeholder.setOpacity(1.0);
        view.getChildren().setAll(placeholder, newView);
        placeholder.toFront();
    }


}
