package ui.util;

import javafx.scene.layout.AnchorPane;

public abstract class RefreshablePane extends AnchorPane {
    public abstract void refresh(boolean toSwitch);
}
