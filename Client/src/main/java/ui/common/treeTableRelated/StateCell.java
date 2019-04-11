package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;

import java.util.function.Function;

public class StateCell<T, S> extends JFXTreeTableCell<T, S> { // 这其实就是我之前一直不想这么写的那种…
    private JFXButton civ = new JFXButton();
    private Function<S, String> stateToString, stateToStyle;

    public StateCell(Function<S, String> stateToString, Function<S, String> stateToStyle) {
        this.stateToString = stateToString;
        this.stateToStyle = stateToStyle;
    }

    @Override
    public void updateItem(S item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            civ.setDisable(true);
            civ.setTranslateY(-4);
            civ.setStyle(stateToStyle.apply(item));
            civ.setText(stateToString.apply(item));
            setGraphic(civ);
        }
    }
}