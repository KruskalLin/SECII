package ui.managerui.promotionui.addPopUpRelated;

import ui.managerui.promotionui.promotionDetailPane.TotalPromotionDetailPane;
import ui.util.RefreshablePane;

public class TotalLabel extends addButton {
    public TotalLabel() {
        setText("总价促销");
    }

    @Override
    public RefreshablePane getPane() {
        return new TotalPromotionDetailPane();
    }
}
