package ui.util;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum ContainerAnimations {

    FADE((c) ->
            new ArrayList<>(Arrays.asList(new KeyFrame(Duration.ZERO, new KeyValue(c.getPlaceholder().opacityProperty(), 1.0, Interpolator.EASE_BOTH)),
                    new KeyFrame(c.getDuration(), new KeyValue(c.getPlaceholder().opacityProperty(), 0.0, Interpolator.EASE_BOTH))))),
    SWIPE_LEFT((c) ->
            new ArrayList<>(Arrays.asList(new KeyFrame(Duration.ZERO, new KeyValue(c.getView().translateXProperty(), c.getView().getWidth(), Interpolator.EASE_BOTH), new KeyValue(c.getPlaceholder().translateXProperty(), -c.getView().getWidth(), Interpolator.EASE_BOTH)),
                    new KeyFrame(c.getDuration(), new KeyValue(c.getView().translateXProperty(), 0, Interpolator.EASE_BOTH), new KeyValue(c.getPlaceholder().translateXProperty(), -c.getView().getWidth(), Interpolator.EASE_BOTH))))),

    SWIPE_RIGHT((c) ->
            new ArrayList<>(Arrays.asList(new KeyFrame(Duration.ZERO, new KeyValue(c.getView().translateXProperty(), -c.getView().getWidth(), Interpolator.EASE_BOTH), new KeyValue(c.getPlaceholder().translateXProperty(), c.getView().getWidth(), Interpolator.EASE_BOTH)),
                    new KeyFrame(c.getDuration(), new KeyValue(c.getView().translateXProperty(), 0, Interpolator.EASE_BOTH), new KeyValue(c.getPlaceholder().translateXProperty(), c.getView().getWidth(), Interpolator.EASE_BOTH)))));

    private Function<PaneSwitchAnimation, List<KeyFrame>> animationProducer;

    private ContainerAnimations(Function<PaneSwitchAnimation, List<KeyFrame>> animationProducer) {
        this.animationProducer = animationProducer;
    }


    public Function<PaneSwitchAnimation, List<KeyFrame>> getAnimationProducer() {
        return animationProducer;
    }
}
