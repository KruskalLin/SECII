package ui.util;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.scene.layout.HBox;

public class MultiCell<T> extends JFXTreeTableCell<T, Boolean> {
    private IconButton iconButton = new IconButton(MaterialDesignIcon.DOTS_HORIZONTAL);
    ListPopup list = new ListPopup();
    JFXPopup popup;
    Runnable runnable1;
    Runnable runnable2;
    @Override
    public void updateItem(Boolean item,boolean empty){
        super.updateItem(item,empty);
        if(item!=null){
            iconButton.setTranslateY(-8);
            popup = new JFXPopup(list);
            iconButton.setOnMouseClicked(e -> popup.show(iconButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
            setGraphic(iconButton);
        }else{
            setGraphic(null);
        }
    }

    public void setRunnable1(Runnable runnable1) {
        this.runnable1 = runnable1;
        list.getListview().setOnMouseClicked(t->{
            Platform.runLater(runnable1);
            popup.hide();
        });
    }

    public void setRunnable2(Runnable runnable2) {
        this.runnable2 = runnable2;
        list.getListdelete().setOnMouseClicked(t->{
            Platform.runLater(runnable2);
            popup.hide();
        });
    }
}
