package ui.managerui.promotionui.addPopUpRelated;

import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.managerui.promotionui.promotionDetailPane.CombinePromotionDetailPane;

// TODO 这三个也应该合一下
public class NewCombinePromotionPopUpListLabel extends PopUpListLabel implements FXMLLoadableMixer{
    public NewCombinePromotionPopUpListLabel() {
        load();
        setText("组合");
    }

    @Override
    public void clickAction() {
        new CombinePromotionDetailPane().refresh(false);
    }

    @Override
    public String publicGetURL() {
        return "/managerui/newPromotionPopUpListLabel.fxml";
    }
}
