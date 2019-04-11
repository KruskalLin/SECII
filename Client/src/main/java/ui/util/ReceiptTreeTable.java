package ui.util;

import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.common.BoardController;
import vo.abstractVO.SelectableVO;

import java.util.Set;

public abstract class ReceiptTreeTable<T extends SelectableVO<T>> extends JFXTreeTableView<T> {


    protected ObservableList<T> observableList = FXCollections.observableArrayList();
    //   private ObservableList<StockReceiptListVO> observableListfilter = observableList;
    //  private ObservableList<StockReceiptListVO> observableListtemp;
    protected int rowsPerPage;
    protected BoardController boardController;
    protected StackPane mainpane;
    protected ChosenItem<T> chosenItem = new ChosenItem<>();
    protected ColumnDecorator columnDecorator = new ColumnDecorator();
    protected SimpleStringProperty keyword = new SimpleStringProperty("");

    public ReceiptTreeTable() {
        super();
        this.boardController = BoardController.getBoardController();
        this.mainpane = PaneFactory.getMainPane();
        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setReceipts(Set<T> receipts) {
        observableList.setAll(receipts);
    }

    public void removeReceipt(T t) {
        observableList.remove(t);
    }

    public void createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        final TreeItem<T> root = new RecursiveTreeItem<>(FXCollections.observableList(observableList.subList(fromIndex, toIndex)), RecursiveTreeObject::getChildren);
        this.setRoot(root);
        NodeHolder nodeHolder = new NodeHolder(this, Duration.millis(1000), NodeAnimation.FADE);
        nodeHolder.apply();

    }

    public abstract void delete(Pagination p);

    public void RowSetter(JFXTreeTableRow<T> row,Runnable click){
        row.setPrefHeight(55);
        row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
        row.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Platform.runLater(click);
            }

        });
        row.selectedProperty().addListener(e -> {
            if (row.isSelected()) {
                row.toFront();
            } else {
                row.setEffect(null);
            }
        });
    }

    public String getKeyword() {
        return keyword.get();
    }

    public SimpleStringProperty keywordProperty() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword.set(keyword);
    }


}
