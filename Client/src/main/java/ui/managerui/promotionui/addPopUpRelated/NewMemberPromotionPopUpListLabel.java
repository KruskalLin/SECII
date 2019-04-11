package ui.managerui.promotionui.addPopUpRelated;

import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.managerui.promotionui.promotionDetailPane.MemberPromotionDetailPane;

public class NewMemberPromotionPopUpListLabel extends PopUpListLabel implements FXMLLoadableMixer{
    public NewMemberPromotionPopUpListLabel() {
        load();
        this.setText("会员");
    }

    @Override
    public void clickAction() {
        new MemberPromotionDetailPane().refresh(false);
    }

    @Override
    public String publicGetURL() {
        return "/managerui/newPromotionPopUpListLabel.fxml";
    }
}
