package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TreeTableColumn;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.function.Function;

public class IntegerEditableColumn<T> extends JFXTreeTableColumn<T, Integer> {
    public IntegerEditableColumn(String columnName, double width, Function<T, IntegerProperty> getter) {
        super(columnName);
        setPrefWidth(width);
        setCellValueFactory(param -> getter.apply(param.getValue().getValue()).asObject());
        setCellFactory(param -> {
            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        setOnEditCommit((TreeTableColumn.CellEditEvent<T, Integer> t) -> {
            getter.apply(t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).set(t.getNewValue());
        });
    }
}
