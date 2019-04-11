package ui.managerui.promotionui.addPopUpRelated;

import com.jfoenix.controls.JFXButton;
import ui.util.RefreshablePane;

public abstract class addButton extends JFXButton {
    public addButton() {
        setStyle("-fx-pref-width: 99; -fx-pref-height: 33;");
        setOnMouseClicked(e -> {
            getPane().refresh(true);
        });
    }

    public abstract RefreshablePane getPane();
}
