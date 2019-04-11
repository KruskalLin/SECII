package ui.util;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum NodeAnimation {
    FADE((c) -> new ArrayList<>(Arrays.asList(new KeyFrame(Duration.ZERO, new KeyValue(c.opacityProperty(), 0.0, Interpolator.EASE_BOTH)),
                    new KeyFrame(c.getDuration(), new KeyValue(c.opacityProperty(), 1.0, Interpolator.EASE_BOTH)))));


    private Function<NodeHolder, List<KeyFrame>> animationProducer;
    private NodeAnimation(Function<NodeHolder, List<KeyFrame>> animationProducer) {
        this.animationProducer = animationProducer;

    }

    public Function<NodeHolder, List<KeyFrame>> getAnimationProducer() {
        return animationProducer;
    }
}
