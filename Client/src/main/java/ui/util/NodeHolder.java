package ui.util;

import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;


public class NodeHolder {
    private Stage stage;
    private Node node;
    private Duration duration;
    private DoubleProperty opacity;
    private NodeAnimation nodeAnimation;
    public NodeHolder(Node node, Duration duration,NodeAnimation nodeAnimation) {
        this.node = node;
        this.duration = duration;
        this.opacity = new SimpleDoubleProperty(1.0);
        node.opacityProperty().bind(opacity);
        this.nodeAnimation = nodeAnimation;
    }
    public NodeHolder(Stage node, Duration duration,NodeAnimation nodeAnimation) {
        this.stage = node;
        this.duration = duration;
        this.opacity = new SimpleDoubleProperty(1.0);
        node.opacityProperty().bind(opacity);
        this.nodeAnimation = nodeAnimation;
    }

    public void apply(){
        Timeline animation = new Timeline();
        animation.getKeyFrames().addAll(nodeAnimation.getAnimationProducer().apply(this));
        animation.play();
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public double getOpacity() {
        return opacity.get();
    }

    public DoubleProperty opacityProperty() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity.set(opacity);
    }
}
