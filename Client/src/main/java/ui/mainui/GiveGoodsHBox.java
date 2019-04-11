package ui.mainui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class GiveGoodsHBox extends HBox {
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label num;

    public GiveGoodsHBox(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/salesui/giveGoodsHBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setGoodsId(String id) {
        this.id.setText(id);
    }

    public void setNum(String num) {
        this.num.setText(num);
    }

}