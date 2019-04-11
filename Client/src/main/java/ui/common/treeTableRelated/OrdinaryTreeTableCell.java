package ui.common.treeTableRelated;

import javafx.scene.control.TreeTableCell;

// 我不知道是不是真的需要这个类……
public class OrdinaryTreeTableCell<T> extends TreeTableCell<T, String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        if (empty) {
            setGraphic(null);
        } else {
            setText(item);
        }
    }
}
