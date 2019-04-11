package ui.accountantui;

import blService.initialblservice.InitialblService;
import businesslogic.initialbl.Initialbl;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;
import ui.util.*;

import java.util.Calendar;


public class InitialListPane extends RefreshablePane {

    @FXML
    BorderPane borderPane;

    BoardController myBoardController;

    StackPane mainpane;

    @FXML
    JFXButton filter;

    public InitialListPane()throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/InitialListPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.myBoardController = BoardController.getBoardController();
        this.mainpane = PaneFactory.getMainPane();
        filter.setText("Initial Account");
    }

    @FXML
    public void initialAccount(){
        try{
            InitialblService initialblService = new Initialbl();
            Calendar a=Calendar.getInstance();
            String year = String.valueOf(a.get(Calendar.YEAR));
            initialblService.initial(year);
        }catch (Exception e){
            e.printStackTrace();
        }
        OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","期初建账成功","返回");
        oneButtonDialog.setButtonOne(()->{});
        oneButtonDialog.show();
    }

    public void refresh(boolean toSwitch){
        myBoardController.Loading();
        myBoardController.switchTo(this);
    }



}
