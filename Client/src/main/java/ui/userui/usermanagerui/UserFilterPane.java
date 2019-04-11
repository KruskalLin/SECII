package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.salesui.SalesListPane;
//import ui.util.BoardController;
import util.ReceiptState;
import util.UserCategory;
import util.UserSearchCondition;
import vo.UserSearchVO;

/**
 * @Author: Lin Yuchao
 * @Description: 过滤器，filter的子pane
 * @ModifyBy: Lin Yuchao
**/
public class UserFilterPane extends AnchorPane{

    @FXML
    JFXComboBox<Label> state;

    @FXML
    JFXDatePicker from;

    @FXML
    JFXDatePicker to;

    UserSearchCondition userSearchCondition;

    PopOver popOver;


    /**
     * @Author: Lin Yuchao
     * @Attention
     * @Param: popover传入用于关闭popover,
     * @Return:
    **/
    public UserFilterPane(PopOver popOver, UserSearchCondition userSearchCondition) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userfilter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.userSearchCondition = userSearchCondition;



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void save() {
        if(from.getValue()!=null)
        userSearchCondition.setCreateTimeFloor(from.getValue().atStartOfDay());
        if(to.getValue()!=null)
        userSearchCondition.setCreateTimeCeil(to.getValue().atStartOfDay());
        userSearchCondition.setUserCategory(UserCategory.map.get(state.getValue().getId()));
        System.out.println(userSearchCondition.getUserCategory());
        popOver.hide();
        BoardController.getBoardController().refresh();
    }

    @FXML
    public void cancel(){
        popOver.hide();
    }

}
