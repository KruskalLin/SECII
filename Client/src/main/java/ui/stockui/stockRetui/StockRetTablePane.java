package ui.stockui.stockRetui;

import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.StringProperty;
import ui.stockui.StockTreeTable;
import ui.util.RefreshablePane;
import vo.receiptVO.StockRetListVO;

import java.util.Set;

public class StockRetTablePane extends StockTreeTable<StockRetListVO> {

    public StockRetTablePane(Set<StockRetListVO> chosenItems, StringProperty keywordProperty) {
        super(chosenItems, keywordProperty);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<StockRetListVO> row) {
        ((RefreshablePane)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);

    }
}