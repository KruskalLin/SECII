package ui.util;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class LabelRingIndicator extends StackPane {
    RingProgressIndicator ringProgressIndicator = new RingProgressIndicator();
    Label title = new Label();
    Label num = new Label();
    int max=100;
    public LabelRingIndicator(){
        super();
        this.getStyleClass().add("indicator");
        this.getStylesheets().add("/css/circleprogress.css");
        this.getChildren().addAll(ringProgressIndicator,title,num);
        title.setTranslateY(-6);
        title.setScaleX(0.7);
        title.setScaleY(0.7);
        num.setTranslateY(10);
        ringProgressIndicator.setScaleX(0.6);
        ringProgressIndicator.setScaleY(0.6);

    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setMax(int max){
        this.max=max;
    }

    public void setNum(int num){
        this.num.setText(num+"");
        double percent = (double)num/max*100;
        this.ringProgressIndicator.setProgress((int)percent);
    }




}
