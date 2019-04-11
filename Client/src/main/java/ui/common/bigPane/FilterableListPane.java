package ui.common.bigPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.bigPane.ListPane;

public abstract class FilterableListPane<TR extends RecursiveTreeObject<TR>> extends ListPane<TR> {
    @FXML
    private JFXButton filter;

    public FilterableListPane() {
        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(getInitialFilterPane(filterPopOver));
        filter.setOnAction(e -> filterPopOver.show(filter));
    }

    /**
     * abstract methods
     * */
    protected abstract AnchorPane getInitialFilterPane(PopOver filterPopOver);
}
