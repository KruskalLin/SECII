package ui.util;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.MasterDetailPane;
import vo.abstractVO.SelectableVO;

public class ChooseCell<T extends SelectableVO<T>> extends JFXTreeTableCell<T,Boolean> {

    private JFXCheckBox cb = new JFXCheckBox("");
    public ChooseCell(ChosenItem<T> chosenItem) {
        cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (getTreeTableRow() != null && getTreeTableRow().getItem() != null) {
                    T t = getTreeTableRow().getItem();
                    t.setSelected(!t.isSelected());
                    if (t.isSelected()) {
                        chosenItem.addItem(t);
                    } else {
                        chosenItem.removeItem(t);
                    }
                }
            }
        });


    }


    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            cb.setSelected(item);
            setGraphic(cb);
        }


    }


}
