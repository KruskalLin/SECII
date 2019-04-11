package ui.mainui;

import businesslogic.logbl.Logbl;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import po.LogPO;
import ui.logui.LogPane;
import ui.salesui.GoodsList;
import ui.salesui.PromotionMasterDetail;
import ui.util.LabelRingIndicator;
import ui.util.RingProgressIndicator;
import util.EventCategory;
import util.UserCategory;

import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        Scene scene = new Scene(new EstablishPane());
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Test ring progress");
//        primaryStage.show();
    }
}
