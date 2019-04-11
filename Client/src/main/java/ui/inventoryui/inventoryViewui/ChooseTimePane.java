package ui.inventoryui.inventoryViewui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;

import java.io.IOException;
import java.time.LocalDate;

public class ChooseTimePane extends AnchorPane {
    @FXML
    JFXRippler close;
    @FXML
    JFXDatePicker startTimeField;
    @FXML
    JFXDatePicker endTimeField;
    @FXML
    JFXButton sure;

    BoardController boardController;

    StackPane mainPane;


    JFXDialog dialog;

    public ChooseTimePane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/ChooseTimePane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @FXML
    public void sureTime(){
        try {
            dialog.close();

            LocalDate startTime = startTimeField.getValue();
            LocalDate endTime = endTimeField.getValue();

            System.out.println(startTime.toString());
            System.out.println(endTime.toString());

            InventoryViewListPane inventoryViewListPane = new InventoryViewListPane();
            inventoryViewListPane.setStartTime(startTime);
            inventoryViewListPane.setEndTime(endTime);
            inventoryViewListPane.refresh(true);
            //boardController.switchTo(new InventoryViewListPane());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
