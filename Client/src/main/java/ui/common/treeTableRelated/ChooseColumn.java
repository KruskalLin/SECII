package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableColumn;
import vo.abstractVO.SelectableVO;

import java.util.Set;

public class ChooseColumn<T extends SelectableVO<T>> extends JFXTreeTableColumn<T, Boolean> {
    public ChooseColumn(Set<T> chosenItems) {
        super(" ");
        setPrefWidth(40);
        setCellValueFactory(param -> param.getValue().getValue().selectedProperty());
        setCellFactory(p -> new ChooseBoxTTCell<>(chosenItems));
    }
}
