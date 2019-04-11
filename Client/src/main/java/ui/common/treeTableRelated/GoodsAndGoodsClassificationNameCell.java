package ui.common.treeTableRelated;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableCell;
import vo.inventoryVO.goodsTreeTableView.AbstractGoodsTreeTableViewVO;

public class GoodsAndGoodsClassificationNameCell extends TreeTableCell<AbstractGoodsTreeTableViewVO, String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Label lbl = new Label(item);
        setGraphic(lbl);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
}
