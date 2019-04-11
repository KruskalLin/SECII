package ui.common;

import businesslogic.blServiceFactory.FactoryController;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import network.ClientInterface;
import network.ClientObject;
import network.ServerInterface;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.managerui.promotionui.PromotionListPane;
import ui.util.GetTask;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RespectiveMainUIControlelr { // TODO 这个类还不成熟，不能用。
    @FXML
    private JFXListView<ChangePaneLabel> navigation;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;

    private ServerInterface serverInterface;

    @FXML
    public void initialize() {
        bar.setBoardController(boardController); // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉

        // TODO 这个东西加到哪里比较好呢？……这里或者login那里应该都行吧
        new Thread(() -> {
            try {
                FactoryController.initiateService();
            } catch (RemoteException e) {
                e.printStackTrace();
                FactoryController.clearAllSavedService();
                Platform.runLater(() -> {
                    new MyOneButtonDialog("连接错误").show();
                });
            }
        }).start();

        PromotionListPane initialPane = new PromotionListPane();
        initialPane.refresh(false);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            newVal.getPane().refresh(true); // 这里还是先按照refresh的写法吧
        });


    }

//    private void initiateMessage() {
//        MyOneButtonDialog dialog = new MyOneButtonDialog("消息连接失败，请重试", this::initiateMessage);
//        new Thread(new GetTask(() -> {
//
//        }, dialog, woid -> {
//            try {
//
//                return true;
//            } catch (NotBoundException | MalformedURLException | RemoteException e) {
//                e.printStackTrace();
//                return false;
//            }
//        })).start();

//    }
}
