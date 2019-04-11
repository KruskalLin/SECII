package ui.managerui.promotionui.addPopUpRelated;

import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.managerui.promotionui.promotionDetailPane.TotalPromotionDetailPane;

public class NewTotalPromotionPopUpListLabel extends PopUpListLabel implements FXMLLoadableMixer {
    public NewTotalPromotionPopUpListLabel() {
        load();
        setText("总价");
    }

    @Override
    public void clickAction() {
        new TotalPromotionDetailPane().refresh(false);
    }

    @Override
    public String publicGetURL() {
        return "/managerui/newPromotionPopUpListLabel.fxml";
    }
}
