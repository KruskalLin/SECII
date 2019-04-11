package ui.memberui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import util.MemberCategory;
import util.MemberSearchCondition;
import vo.MemberSearchVO;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.NumberValid;

public class MemberFilterPane extends AnchorPane{

    @FXML
    JFXButton save;
    @FXML
    JFXTextField degree;

    @FXML
    JFXComboBox<Label> state;

    MemberSearchCondition memberSearchCondition;

    PopOver popOver;

    public MemberFilterPane(PopOver popOver, MemberSearchCondition memberSearchCondition) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/memberfilterpane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            NumberValid(degree);
            this.popOver = popOver;
            this.memberSearchCondition = memberSearchCondition;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void save() {
        if(!degree.getText().equals(""))
        memberSearchCondition.setDegree(Integer.parseInt(degree.getText()));
        if(state.getValue()!=null)
        memberSearchCondition.setMemberCategory(MemberCategory.map.get(state.getValue().getId().toUpperCase()));
        popOver.hide();
        BoardController.getBoardController().refresh();

    }
}
