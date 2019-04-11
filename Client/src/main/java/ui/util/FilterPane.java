package ui.util;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.salesui.SalesListPane;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;


public class FilterPane extends AnchorPane {


    @FXML
    JFXComboBox<Label> state;

    @FXML
    JFXDatePicker from;

    @FXML
    JFXDatePicker to;

    SalesListPane slp;


    RespectiveReceiptSearchCondition respectiveReceiptSearchCondition;

    PopOver popOver;

    public FilterPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/filter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public FilterPane(PopOver popOver, RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) {
        this();
        this.popOver = popOver;
        this.respectiveReceiptSearchCondition = respectiveReceiptSearchCondition;

    }


    @FXML
    public void save() {
        respectiveReceiptSearchCondition.setReceiptState(ReceiptState.map.get(state.getValue().getId().toUpperCase()));
        if (from.getValue() != null)
            respectiveReceiptSearchCondition.setCreateTimeFloor(from.getValue().atStartOfDay());
        if (to.getValue() != null)
            respectiveReceiptSearchCondition.setCreateTimeCeil(to.getValue().atStartOfDay());
        popOver.hide();
        BoardController.getBoardController().refresh();

    }

    @FXML
    public void cancel() {
        popOver.hide();
    }

}
