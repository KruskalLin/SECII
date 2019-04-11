package ui.inventoryui.inventoryReceiptui;

import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;

public class PopUpDeleteLabel extends PopUpListLabel implements FXMLLoadableMixer{
    private Runnable runnable;

    public PopUpDeleteLabel(Runnable runnable) {
        this.runnable = runnable;
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/inventoryreceiptui/myPopUpDeleteLabel.fxml";
    }

    @Override
    public void clickAction() {
        runnable.run();
    }
}
