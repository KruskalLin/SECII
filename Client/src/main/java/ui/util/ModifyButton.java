package ui.util;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ModifyButton extends JFXButton {
    @FXML
    MaterialDesignIconView pen;

    SimpleBooleanProperty modify = new SimpleBooleanProperty(false);


    public ModifyButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/modify.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                modify.setValue(!modify.getValue());
                if (modify.getValue() == true) {
                    setBackground(new Background(new BackgroundFill(Color.valueOf("#03A9F4"), getBackground().getFills().get(0).getRadii(), null)));
                    pen.setFill(Paint.valueOf("#FFFFFF"));
                } else {
                    setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, getBackground().getFills().get(0).getRadii(), null)));
                    pen.setFill(Paint.valueOf("#000000"));
                }
            }
        });
    }

    public boolean isModify() {
        return modify.get();
    }

    public SimpleBooleanProperty modifyProperty() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify.set(modify);
    }

}




