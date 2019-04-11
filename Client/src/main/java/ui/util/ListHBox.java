package ui.util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListHBox extends HBox {
    @FXML
    Label id;
    @FXML
    Label name;

    public ListHBox(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/salesui/listHBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setLabelId(String id){
        this.id.setText(id);
    }
    public void setName(String name){
        this.name.setText(name);
    }

}
