package ui.managerui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.util.UserInfomation;
import util.UserCategory;

import static ui.util.SetDraggable.setDraggable;

public class TempManagerLauncher extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TempManagerLauncher.primaryStage = primaryStage;

        // 必须要先提供信息。这一点在fxml里面也做了说明。
        UserInfomation.username = "王二小";
        UserInfomation.usertype = UserCategory.GeneralManager;
        UserInfomation.userimage = new Image("/default/timg.jpg");

        Parent root = FXMLLoader.load(getClass().getResource("/managerui/managerMain.fxml"));
        Scene scene = new Scene(root);
        setDraggable(scene, primaryStage);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
