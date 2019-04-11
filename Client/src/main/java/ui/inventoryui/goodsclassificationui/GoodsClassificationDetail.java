package ui.inventoryui.goodsclassificationui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GoodsClassificationDetail extends AnchorPane {
    @FXML
    JFXTextField name;

    JFXDialog dialog;

    public GoodsClassificationDetail() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodui/goodsclassificationdetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void sure(){

    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
