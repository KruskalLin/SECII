package ui.inventoryui.inventoryReceiptui.common;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import ui.common.popupList.MyPopUpListView;
import ui.common.treeTableRelated.IntegerEditableColumn;
import ui.common.treeTableRelated.OrdinaryStringColumn;
import ui.inventoryui.inventoryReceiptui.PopUpDeleteLabel;
import ui.inventoryui.inventoryReceiptui.common.InventoryListItemTreeTable;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

public class DamageAndOverflowGoodsItemTreeTable extends JFXTreeTableView<ReceiptGoodsItemVO> {
    private ObservableList<ReceiptGoodsItemVO> list;

    public DamageAndOverflowGoodsItemTreeTable() {
        super();

        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsID = new OrdinaryStringColumn<>("编号", 115.5, "goodsId");
        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsName = new OrdinaryStringColumn<>("名称", 115.5, "goodsName");
        JFXTreeTableColumn<ReceiptGoodsItemVO, String> inventoryNum = new OrdinaryStringColumn<>("原库存数量", 115.5, p -> String.valueOf(p.getInventoryNum()));
        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> factNum = new IntegerEditableColumn<>("实际数量", 115.5, ReceiptGoodsItemVO::factNumProperty);



        this.setRowFactory(tableView -> {
            JFXTreeTableRow<ReceiptGoodsItemVO> row = new JFXTreeTableRow<>();
//            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(e -> {
                if (row.getTreeItem() != null && e.getButton().equals(MouseButton.SECONDARY)) {
                    JFXPopup popup = new JFXPopup(); // 为了要传到下面
                    MyPopUpListView listView = new MyPopUpListView(popup);
                    listView.getItems().add(new PopUpDeleteLabel(() -> {
                        list.remove(row.getItem());
                    }));
                    popup.setPopupContent(listView);
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
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

        this.setEditable(false);
        this.setShowRoot(false);
        this.getColumns().setAll(goodsID, goodsName, inventoryNum, factNum);
    }

    public void refresh(ObservableList<ReceiptGoodsItemVO> list) {
        this.list = list;
        TreeItem<ReceiptGoodsItemVO> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        this.setRoot(root);
    }
}
