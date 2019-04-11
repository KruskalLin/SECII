package ui.memberui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import ui.util.CircleImageView;

public class ChooseListHBox extends HBox {
    @FXML
    private CircleImageView civ;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label degree;
@FXML
private Label comment;

    public ChooseListHBox(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/memberCell.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setImage(Image image){
        civ.setImage(image);
    }
    public void setName(String str){
        name.setText(str);
    }
    public void setId(int memberId){ id.setText(String.valueOf(memberId));}
    public void setDegree(int i){degree.setText(i+"");}
    public void setComment(String comment){this.comment.setText(comment);}
}
