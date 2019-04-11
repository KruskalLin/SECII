package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.SnapshotView;

import java.net.URL;
import java.util.ResourceBundle;

public class SnapShot extends AnchorPane{
    @FXML
    SnapshotView snapshot;
    @FXML
    ImageView imageView;
    @FXML
    JFXButton save;
    @FXML
    JFXButton cancel;

    Image image;

    public SnapShot(JFXDialog dialog,Image image){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/snapshot.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            snapshot.setSelectionRatioFixed(true);
            this.imageView.setImage(image);
        }catch (Exception e){
            e.printStackTrace();
        }
        save.setOnMouseClicked(t->{
            this.image = snapshot.createSnapshot();
            dialog.close();
        });

        cancel.setOnMouseClicked(t->{
            dialog.close();
        });
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
