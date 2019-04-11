package ui.managerui.businessSalesDetail;

import ui.common.ChangePaneLabel;
import ui.managerui.businessSalesDetail.BusinessSalesListPane;
import ui.util.RefreshablePane;

public class BusinessSalesDetailChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new BusinessSalesListPane();
    }
}
