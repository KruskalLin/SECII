package ui.util;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SetDraggable {
    public static double x_stage;
    public static double y_stage;
    public static double x1;
    public static double y1;



    public static void setDraggable(Scene scene, Stage primaryStage){
        primaryStage.centerOnScreen();
        scene.setFill(Color.TRANSPARENT);
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent m) {
                primaryStage.setX(x_stage + m.getScreenX() - x1);
                primaryStage.setY(y_stage + m.getScreenY() - y1);

            }
        });
        scene.setOnDragEntered(null);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override public void handle(MouseEvent m) {
                x1 =m.getScreenX();
                y1 =m.getScreenY();
                x_stage = primaryStage.getX();
                y_stage = primaryStage.getY();
            }
        });
    }



}
