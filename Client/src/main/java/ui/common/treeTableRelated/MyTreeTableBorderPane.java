package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ui.util.NodeAnimation;
import ui.util.NodeHolder;

import java.util.List;

public abstract class MyTreeTableBorderPane<T extends RecursiveTreeObject<T>> extends BorderPane {
    protected ObservableList<T> observableList;
    protected int rowsPerPage = 7;

    protected JFXTreeTableView<T> myTreeTable = new JFXTreeTableView<>();
    protected Pagination pagination = new Pagination();

    public MyTreeTableBorderPane() {
        myTreeTable.setRowFactory(tableView -> {
            JFXTreeTableRow<T> row = new JFXTreeTableRow<>();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(e -> {
                if (row.getTreeItem() != null && e.getClickCount() == 2) {
                    clickTwiceAftermath(row);
                }
            });
            row.selectedProperty().addListener(e -> {
                if (row.isSelected()) {
                    row.toFront();
                } else {
                    row.setEffect(null);
                }
            });

            return row;
        });

        pagination.currentPageIndexProperty().addListener(((observable, oldValue, newValue) -> {
            createPage(newValue.intValue());
        }));

        setPrefSize(600, 480);
        setBottom(pagination);
        setCenter(myTreeTable);

        myTreeTable.setPrefSize(600, 450);
        myTreeTable.setShowRoot(false);
    }

    /**
     * public
     */

    // TODO 这里应该设置成observableList的，这样可编辑的和不可编辑的就统一起来了
    public void refresh(List<T> list) {
        this.observableList = FXCollections.observableArrayList(list);

        int maxPageIndex = (observableList.size() - 1) / rowsPerPage + 1;
        pagination.setPageCount(maxPageIndex); // 就算是0好像也不报错的

        int currentPageIndex = pagination.getCurrentPageIndex();
        int newPageIndex = 0;
        if (currentPageIndex - 1 >= 0 && currentPageIndex < maxPageIndex) { // 这里pageIndex在userListPane逻辑有点没看懂…就自己瞎改了
            newPageIndex = currentPageIndex;
        }
        if (newPageIndex == currentPageIndex) {
            // 这里写的很丑。
            // 因为：如果newPageIndex和原来一样的，只改pagination就不会触发createPage；如果不一样（只有0的情况才不一样吧），还要改pagination
            // 要么就是继createPage又改pagination，感觉有可能会两次create。
            createPage(newPageIndex);
        } else {
            pagination.setCurrentPageIndex(newPageIndex);
        }
    }

    /**
     *
     * */

    protected abstract void clickTwiceAftermath(JFXTreeTableRow<T> row);

    private void createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        ObservableList<T> tempList = (FXCollections.observableArrayList(observableList.subList(fromIndex, toIndex)));
        System.out.println(tempList);
        TreeItem<T> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        myTreeTable.setRoot(root);

        new NodeHolder(this, Duration.millis(1000), NodeAnimation.FADE).apply();
    }


}
