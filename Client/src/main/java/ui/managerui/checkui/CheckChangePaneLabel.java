package ui.managerui.checkui;

import ui.common.ChangePaneLabel;
import ui.managerui.checkui.CheckListPane;
import ui.util.RefreshablePane;

public class CheckChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new CheckListPane();
    }
}
