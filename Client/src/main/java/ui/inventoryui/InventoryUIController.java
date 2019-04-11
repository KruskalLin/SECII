package ui.inventoryui;

import businesslogic.blServiceFactory.FactoryController;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.inventoryui.goodsclassificationui.GoodsClassificationPane;
import ui.inventoryui.goodsui.GoodsListPane;
import ui.inventoryui.inventoryCheckui.InventoryCheckPane;
import ui.inventoryui.inventoryReceiptui.InventoryDamageListPane;
import ui.inventoryui.inventoryReceiptui.InventoryGiftListPane;
import ui.inventoryui.inventoryReceiptui.InventoryOverflowListPane;
import ui.inventoryui.inventoryReceiptui.InventoryWarningListPane;
import ui.inventoryui.inventoryViewui.ChooseTimePane;
import ui.common.MyTopBar;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsClassificationPane;
import ui.util.PaneFactory;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class InventoryUIController implements Initializable {
    @FXML
    MyTopBar bar;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    StackPane board;

    @FXML
    BoardController boardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉
        bar.setBoardController(boardController);


        // TODO by 连。这个是暂时加在这里用来测试消息的。
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


        InventoryGiftListPane initialPane = new InventoryGiftListPane();
        initialPane.refresh(true);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            try {
                if (newVal != null) {
                    if (newVal.getId().equals("goods")) {
                        GoodsListPane goodsListPane = new GoodsListPane();
                        goodsListPane.refresh(true);
                    } else if (newVal.getId().equals("inventoryView")) {
                        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                        jfxDialogLayout.setPrefWidth(220.0);
                        ChooseTimePane chooseTimePane = new ChooseTimePane();
                        jfxDialogLayout.setBody(chooseTimePane);
//                        JFXButton save = new JFXButton("Save");
                        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
                        chooseTimePane.setDialog(dialog);
                        dialog.show();

                        /*InventoryViewListPane inventoryViewListPane = new InventoryViewListPane();
                        inventoryViewListPane.refresh(true);*/
                    } else if (newVal.getId().equals("inventoryCheck")) {
                        InventoryCheckPane inventoryCheckPane = new InventoryCheckPane();
                        inventoryCheckPane.refresh(true);
                    } else if (newVal.getId().equals("goodsClassification")) {
                        MyGoodsClassificationPane myGoodsClassificationPane = new MyGoodsClassificationPane();
                        myGoodsClassificationPane.refresh(true);
                      /*  GoodsClassificationPane goodsClassificationPane = new GoodsClassificationPane(boardController, PaneFactory.getMainPane());
                        goodsClassificationPane.refresh(true);*/
                    } else if (newVal.getId().equals("inventoryGift")) {
                        InventoryGiftListPane inventoryGiftListPane = new InventoryGiftListPane();
                        inventoryGiftListPane.refresh(true);
                    } else if (newVal.getId().equals("inventoryDamage")) {
                        InventoryDamageListPane inventoryDamageListPane = new InventoryDamageListPane();
                        inventoryDamageListPane.refresh(true);
                    } else if (newVal.getId().equals("inventoryOverflow")) {
                        InventoryOverflowListPane inventoryOverflowListPane = new InventoryOverflowListPane();
                        inventoryOverflowListPane.refresh(true);
                    } else if (newVal.getId().equals("inventoryWarning")) {
                        InventoryWarningListPane inventoryWarningListPane = new InventoryWarningListPane();
                        inventoryWarningListPane.refresh(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}
