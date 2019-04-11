package ui.stockui.stockPurui;

import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.StringProperty;
import ui.stockui.StockTreeTable;
import ui.util.RefreshablePane;
import vo.receiptVO.StockPurListVO;

import java.util.Set;

public class StockPurTablePane  extends StockTreeTable<StockPurListVO> {
    public StockPurTablePane(Set<StockPurListVO> chosenItems, StringProperty keywordProperty) {
        super(chosenItems, keywordProperty);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<StockPurListVO> row) {
        ((RefreshablePane)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);

    }
}