package ui.util;

import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.scene.image.Image;

public class ImageCell extends JFXTreeTableCell {
    private CircleImageView civ = new CircleImageView();

    @Override
    public void updateItem(Object item,boolean empty){
        super.updateItem(item,empty);
        if(empty){
            setGraphic(null);
        }else{
            civ.setImage((Image)item);
            civ.setRadius(17);
            civ.setTranslateY(-8);
            setGraphic(civ);
        }


    }
}
