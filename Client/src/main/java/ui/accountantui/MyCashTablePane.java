package ui.accountantui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import ui.util.RefreshablePane;
import util.ReceiptState;
import vo.billReceiptVO.CashReceiptListVO;

import java.util.Set;

public class MyCashTablePane extends MyTreeTableBorderPane<CashReceiptListVO> {
    public MyCashTablePane(Set<CashReceiptListVO> chosenItems, StringProperty keywordProperty) {

        JFXTreeTableColumn<CashReceiptListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<CashReceiptListVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, CashReceiptListVO::getId);
        JFXTreeTableColumn<CashReceiptListVO, String> operatorColumn = new SearchableStringColumn<>("操作员", 100, keywordProperty, p -> String.valueOf(p.getOperator()));
        JFXTreeTableColumn<CashReceiptListVO, String> sumColumn = new SearchableStringColumn<>("金额", 100, keywordProperty, p -> String.valueOf(p.getSum()));

        JFXTreeTableColumn<CashReceiptListVO, String> stateColumn = new JFXTreeTableColumn<>("状态");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<CashReceiptListVO>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(ReceiptState.color.get(item));
                    setCivText(ReceiptState.chinese.get(ReceiptState.map.get(item)));
                }
            }
        });

        myTreeTable.getColumns().addAll(choose, idColumn, operatorColumn, sumColumn, stateColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<CashReceiptListVO> row) {
        ((RefreshablePane)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);
    }
}
