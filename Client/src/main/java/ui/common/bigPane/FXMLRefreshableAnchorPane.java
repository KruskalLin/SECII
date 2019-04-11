package ui.common.bigPane;

import ui.common.mixer.FXMLLoadableMixer;
import ui.util.RefreshablePane;

public abstract class FXMLRefreshableAnchorPane extends RefreshablePane implements FXMLLoadableMixer {
    public FXMLRefreshableAnchorPane() {
        load();
    }

    protected abstract String getURL();

    @Override
    public String publicGetURL() {
        return getURL();
    }
}
