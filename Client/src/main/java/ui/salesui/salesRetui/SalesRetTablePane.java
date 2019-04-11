package ui.salesui.salesRetui;

import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.StringProperty;
import ui.salesui.SalesTreeTable;
import ui.util.RefreshablePane;
import vo.receiptVO.SalesRetListVO;

import java.util.Set;

public class SalesRetTablePane extends SalesTreeTable<SalesRetListVO>{
    public SalesRetTablePane(Set<SalesRetListVO> chosenItems, StringProperty keywordProperty) {
        super(chosenItems, keywordProperty);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<SalesRetListVO> row) {
        ((RefreshablePane)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);

    }
}
