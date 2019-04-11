package ui.managerui.promotionui.addPopUpRelated;

import ui.managerui.promotionui.promotionDetailPane.MemberPromotionDetailPane;
import ui.util.RefreshablePane;

public class MemberLabel extends addButton {
    public MemberLabel() {
        setText("会员促销");
    }

    @Override
    public RefreshablePane getPane() {
        return new MemberPromotionDetailPane();
    }
}
