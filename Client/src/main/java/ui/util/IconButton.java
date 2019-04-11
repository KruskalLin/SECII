package ui.util;

import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class IconButton extends JFXRippler {

    @FXML
    private MaterialDesignIconView view;


    public IconButton(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/iconbutton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IconButton(MaterialDesignIcon icon){
        this();
        view.setIcon(icon);
    }
    public void setIconFill(Paint paint){
        view.setFill(paint);
    }

}
