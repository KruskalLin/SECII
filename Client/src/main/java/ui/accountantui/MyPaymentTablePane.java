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
import vo.billReceiptVO.PaymentReceiptListVO;

import java.util.Set;

public class MyPaymentTablePane extends MyTreeTableBorderPane<PaymentReceiptListVO> {
    public MyPaymentTablePane(Set<PaymentReceiptListVO> chosenItems, StringProperty keywordProperty) {

        JFXTreeTableColumn<PaymentReceiptListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<PaymentReceiptListVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, PaymentReceiptListVO::getId);
        JFXTreeTableColumn<PaymentReceiptListVO, String> operatorColumn = new SearchableStringColumn<>("操作员", 100, keywordProperty, p -> String.valueOf(p.getOperator()));
        JFXTreeTableColumn<PaymentReceiptListVO, String> sumColumn = new SearchableStringColumn<>("金额", 100, keywordProperty, p -> String.valueOf(p.getSum()));

        JFXTreeTableColumn<PaymentReceiptListVO, String> stateColumn = new JFXTreeTableColumn<>("状态");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<PaymentReceiptListVO>() {
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
    protected void clickTwiceAftermath(JFXTreeTableRow<PaymentReceiptListVO> row) {
        // TODO 这里本来不应该有强转的，接口没定义好，所以先这么将就着
        ((RefreshablePane)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);
    }
}
