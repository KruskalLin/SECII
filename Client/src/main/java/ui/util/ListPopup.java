package ui.util;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class ListPopup extends JFXListView {


    @FXML
    HBox listview;
    @FXML
    HBox listdelete;

    public ListPopup(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/popupList.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HBox getListview() {
        return listview;
    }

    public HBox getListdelete() {
        return listdelete;
    }
}
