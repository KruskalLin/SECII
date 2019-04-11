package ui.managerui.businessProgressui;

import ui.common.ChangePaneLabel;
import ui.managerui.businessProgressui.BusinessProgressPane;
import ui.util.RefreshablePane;

public class BusinessProgressChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new BusinessProgressPane();
    }
}
