package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import ui.util.ImageCell;

public class ImageColumn extends JFXTreeTableColumn {
    public ImageColumn(String head){
        this.setPrefWidth(60);
        this.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        this.setCellFactory(p->new ImageCell());
        this.setText(head);
    }
}
