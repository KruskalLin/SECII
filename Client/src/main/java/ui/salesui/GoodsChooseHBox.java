package ui.salesui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import org.controlsfx.control.HyperlinkLabel;
import ui.util.CircleImageView;

public class GoodsChooseHBox  extends HBox {
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private Label num;
    @FXML
    private Label type;

    public GoodsChooseHBox(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/salesui/goodsHBox.fxml"));
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

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setNum(String num) {
        this.num.setText(num);
    }

    public void setType(String type) {
        this.type.setText(type);
    }
}