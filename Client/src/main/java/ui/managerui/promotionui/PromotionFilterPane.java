package ui.managerui.promotionui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vo.PromotionSearchVO;

import java.io.IOException;

public class PromotionFilterPane extends AnchorPane {
    @FXML
    private JFXCheckBox memberBox, combineBox, totalBox;
    @FXML
    private JFXDatePicker beginTime, endTime;
    @FXML
    private JFXButton select;

    public PromotionFilterPane(PromotionListPane promotionListPane, PromotionSearchVO promotionSearchVO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/promotionFilterPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        beginTime.valueProperty().bindBidirectional(promotionSearchVO.endFloorPropertyProperty());
        endTime.valueProperty().bindBidirectional(promotionSearchVO.beginCeilPropertyProperty());
        memberBox.selectedProperty().bindBidirectional(promotionSearchVO.containMemberPropertyProperty());
        combineBox.selectedProperty().bindBidirectional(promotionSearchVO.containCombinePropertyProperty());
        totalBox.selectedProperty().bindBidirectional(promotionSearchVO.containTotalPropertyProperty());

        select.setOnAction(event -> promotionListPane.refresh(false));
    }
}
