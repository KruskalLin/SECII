package ui.managerui.businessConditionui;

import ui.common.ChangePaneLabel;
import ui.managerui.businessConditionui.BusinessConditionPane;
import ui.util.RefreshablePane;

public class BusinessConditionChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new BusinessConditionPane();
    }
}
