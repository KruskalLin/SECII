package ui.common;

import businesslogic.blServiceFactory.FactoryController;
import businesslogic.blServiceFactory.MessageObjectFactory;
import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import ui.messageui.MessageListView;
import ui.messageui.MyMessageListView;
import ui.userui.usermanagerui.UserDetailPane;
import ui.util.*;

import java.io.IOException;

import static ui.util.SetDraggable.setDraggable;

public class MyTopBar extends HBox {
    @FXML
    private JFXRippler closebutton;

    @FXML
    private JFXRippler hidebutton;

    @FXML
    private JFXBadge message;

    @FXML
    private JFXRippler back;
    @FXML
    private JFXRippler forward;

    @FXML
    private FontAwesomeIconView left;

    @FXML
    private FontAwesomeIconView right;

    @FXML
    private JFXRippler userPopup;

    @FXML
    private CircleImageView user;

    @FXML
    private Label username;


    private BoardController myBoardController;

    private IntegerProperty messageNumber;

    public MyTopBar() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/topbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            user.setImage(UserInfomation.userimage);
            username.setText(UserInfomation.username);

            ListPopup list = new ListPopup();
            ((Label)(list.getListview().getChildren().get(0))).setText("我的信息");
            ((Label)(list.getListdelete().getChildren().get(0))).setText("退出");
            JFXPopup popup = new JFXPopup(list);
            userPopup.setOnMouseClicked(e -> popup.show(userPopup, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
            list.getListview().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popup.hide();
                    UserDetailPane userDetailPane = new UserDetailPane(UserInfomation.userVO);
                    userDetailPane.refresh(true);
                }
            });
            list.getListdelete().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        popup.hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/userui/login.fxml"));
                        Stage stage = (Stage)closebutton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        setDraggable(scene, stage);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            back.disableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        left.setFill(Paint.valueOf("#9fc1d6"));

                    } else {
                        left.setFill(Paint.valueOf("#000000"));
                    }
                }
            });
            forward.disableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        right.setFill(Paint.valueOf("#9fc1d6"));
                    } else {
                        right.setFill(Paint.valueOf("#000000"));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        PopOver messagePopOver = new PopOver();
//        MessageListView messageListView = new MessageListView();
//        BorderPane anchorPane = new BorderPane();
//
//        anchorPane.setCenter(messageListView);
//
//        anchorPane.setPadding(new Insets(10, 10, 10, 10));
//        messagePopOver.setContentNode(anchorPane);
//        messagePopOver.setDetachable(false);
//        messagePopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
//
//        message.setOnMouseClicked(e -> messagePopOver.show(message));


        // initiate message
        messageNumber = new SimpleIntegerProperty(0);
        messageNumber.addListener((observable, oldValue, newValue) -> {
            System.out.println("change messageNumber value to " + newValue);
            if (newValue != null) {
                Platform.runLater(() -> {
                    message.setText(String.valueOf(newValue.intValue()));
                });
            }
        });
        MyServiceFactory.setMessageNumber(messageNumber);

        PopOver messagePopOver = new PopOver();
        MyMessageListView messageListView = new MyMessageListView(messagePopOver);
        BorderPane anchorPane = new BorderPane();

        anchorPane.setCenter(messageListView);

        anchorPane.setPadding(new Insets(10, 10, 10, 10));
        messagePopOver.setContentNode(anchorPane);
        messagePopOver.setDetachable(false);
        messagePopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);

        messageListView.myRefresh();

        message.setOnMouseClicked(e -> { // TODO 判断目前是否有消息，至于UI怎么设计呢？要么不会弹出来？要么弹出来上面大书“没消息”？
            messageListView.myRefresh();
            messagePopOver.show(message);
        });
    }

    public void setBoardController(BoardController myBoardController) {
        this.myBoardController = myBoardController;

        back.disableProperty().bind(myBoardController.canBackProperty().not());
        forward.disableProperty().bind(myBoardController.canForwardProperty().not());
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        FactoryController.clearAllSavedService(); // TODO 这个如果不是正常关的，之后发消息的时候就还是会有问题。比如用ide的红方框来关
        stage.close();
    }


    @FXML
    public void hide() throws Exception {
        Stage stage = (Stage) hidebutton.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void goback() {
        myBoardController.goBack();
    }

    @FXML
    public void goforward() {
        myBoardController.goForward();
    }

    @FXML
    public void refresh() {
        myBoardController.refresh();
    }
}

