package ui.common.popupList;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import ui.common.mixer.FXMLLoadableMixer;

public class MyPopUpListView extends JFXListView<PopUpListLabel> implements FXMLLoadableMixer {

    public MyPopUpListView(JFXPopup popup) {
        load();
        this.setCellFactory(param -> {
            JFXListCell<PopUpListLabel> cell = new JFXListCell<>();
            cell.setOnMouseClicked(e -> {
                cell.getItem().clickAction();
                popup.hide();
            });
            return cell;
        });
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/goodsPopUpList.fxml";
    }
}