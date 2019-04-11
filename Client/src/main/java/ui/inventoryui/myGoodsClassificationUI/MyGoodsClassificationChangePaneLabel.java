package ui.inventoryui.myGoodsClassificationUI;

import ui.common.ChangePaneLabel;
import ui.util.RefreshablePane;

public class MyGoodsClassificationChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new MyGoodsClassificationPane();
    }
}
