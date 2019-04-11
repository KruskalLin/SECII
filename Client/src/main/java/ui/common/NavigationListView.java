package ui.common;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;

public class NavigationListView extends JFXListView<ChangePaneLabel> {
    public NavigationListView() {
        this.setCellFactory(param -> {
            JFXListCell<ChangePaneLabel> cell = new JFXListCell<>();
            cell.setOnMouseClicked(e -> {
                cell.getItem().getPane().refresh(true);
            });
            return cell;
        });
    }
}
