package ui.common.bigPane;

import javafx.scene.layout.AnchorPane;
import ui.common.mixer.FXMLLoadableMixer;

public abstract class FXMLAnchorPane extends AnchorPane implements FXMLLoadableMixer {
    public FXMLAnchorPane() {
        load();
    }

    protected abstract String getURL();

    @Override
    public String publicGetURL() {
        return getURL();
    }
}
