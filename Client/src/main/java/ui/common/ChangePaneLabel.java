package ui.common;

import javafx.scene.control.Label;
import ui.util.RefreshablePane;

public abstract class ChangePaneLabel extends Label {
    public abstract RefreshablePane getPane();
}
