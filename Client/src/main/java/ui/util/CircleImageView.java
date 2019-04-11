package ui.util;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class CircleImageView extends Circle{

    private Image image;
    public CircleImageView(){
        this("/default/timg.jpg");
    }

    public CircleImageView(String url){
        super();
        this.setSmooth(true);
        this.setCache(true);
        image=new Image(getClass().getResource(url).toExternalForm());
        this.setFill(new ImagePattern(image));
        this.setStroke(Paint.valueOf("dimgrey"));
        this.setStrokeType(StrokeType.INSIDE);
        this.setVisible(true);
    }

    public void setImage(String url) {
        this.image = new Image(url);
        this.setFill(new ImagePattern(image));
    }

    public void setImage(Image image) {
        this.image = image;
        this.setFill(new ImagePattern(image));
    }


}
