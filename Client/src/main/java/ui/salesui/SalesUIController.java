package ui.salesui;

import businesslogic.blServiceFactory.FactoryController;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;
import ui.common.MyTopBar;
import ui.common.dialog.MyOneButtonDialog;
import ui.memberui.MemberListPane;
import ui.salesui.salesRetui.SalesRetListPane;
import ui.salesui.salesSellui.SalesSellListPane;
import ui.stockui.StockListPane;
import ui.stockui.stockPurui.StockPurListPane;
import ui.stockui.stockRetui.StockRetListPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SalesUIController implements Initializable{

    @FXML
    private JFXListView<HBox> navigation;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setBoardController(boardController);

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

        StockListPane init =new StockPurListPane();
        init.refresh(true);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            try {
                if (newVal != null) {
                    if (newVal.getId().equals("stockpur")) {
                        StockListPane stockListPane = new StockPurListPane();
                        stockListPane.refresh(true);
                    }else if(newVal.getId().equals("stockret")){
                        StockListPane stockListPane = new StockRetListPane();
                        stockListPane.refresh(true);
                    }else if(newVal.getId().equals("salessell")){
                        SalesListPane salesListPane = new SalesSellListPane();
                        salesListPane.refresh(true);
                    }else if(newVal.getId().equals("salesret")){
                        SalesListPane salesListPane = new SalesRetListPane();
                        salesListPane.refresh(true);
                    }
                    else if(newVal.getId().equals("member")){
                        MemberListPane memberListPane = new MemberListPane();
                        memberListPane.refresh(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    /*
    @FXML
    TopBar bar;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    StackPane board;

    @FXML
    BoardController boardController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BoardController.setBoardController(boardController);

        bar.setBoardController(boardController);

        //set default pane
            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));
            try{
                StockListPane stockListPane = new StockListPane(true);
                stockListPane.historyAdd = true;
                stockListPane.refresh(false);
            }catch (Exception e){
                e.printStackTrace();
            }



         navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getId().equals("stockpur")) {
                                    StockListPane stockListPane = new StockListPane(true);
                                    stockListPane.refresh(true);
                                }else if(newVal.getId().equals("stockret")){
                                    StockListPane stockListPane = new StockListPane(false);
                                    stockListPane.refresh(true);
                                }else if(newVal.getId().equals("salessell")){
                                    SalesListPane salesListPane = new SalesListPane(true);
                                    salesListPane.refresh(true);
                                }else if(newVal.getId().equals("salesret")){
                                    SalesListPane salesListPane = new SalesListPane(false);
                                    salesListPane.refresh(true);
                                }else if(newVal.getId().equals("member")){
                                    MemberListPane memberListPane = new MemberListPane();
                                    memberListPane.refresh(true);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            });

    }*/
}
