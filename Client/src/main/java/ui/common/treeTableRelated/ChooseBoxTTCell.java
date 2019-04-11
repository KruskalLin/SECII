package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.scene.control.ContentDisplay;
import vo.abstractVO.SelectableVO;

import java.util.Set;

public class ChooseBoxTTCell<T extends SelectableVO<T>> extends JFXTreeTableCell<T, Boolean> {
    private JFXCheckBox cb;

    public ChooseBoxTTCell(Set<T> chosenItems) {
        cb = new JFXCheckBox("");
        cb.setOnMouseClicked(e -> {
//                if (getTreeTableRow() != null && getTreeTableRow().getItem() != null) {
            T item = getTreeTableRow().getItem();
            item.setSelected(!item.isSelected());

            if (item.isSelected()) {
                chosenItems.add(item);
            } else {
                chosenItems.remove(item);
            }
//                }
        });
    }

    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            cb.setSelected(item);
            setGraphic(cb);
        }

    }
}