package ui.managerui.promotionui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import ui.common.popupList.MyPopUpListView;
import ui.inventoryui.inventoryReceiptui.PopUpDeleteLabel;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.util.Collection;

public class GoodsTreeTable extends JFXTreeTableView<PromotionGoodsItemVO> {

    private ObservableList<PromotionGoodsItemVO> gifts = FXCollections.observableArrayList();;

    public GoodsTreeTable() {
        TreeItem<PromotionGoodsItemVO> root = new RecursiveTreeItem<>(gifts, RecursiveTreeObject::getChildren);

        JFXTreeTableColumn<PromotionGoodsItemVO, String> idColumn = new JFXTreeTableColumn<>("商品编号");
        idColumn.setPrefWidth(94);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionGoodsItemVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));

        JFXTreeTableColumn<PromotionGoodsItemVO, String> nameColumn = new JFXTreeTableColumn<>("商品名称");
        nameColumn.setPrefWidth(94);
        nameColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getName()));

        JFXTreeTableColumn<PromotionGoodsItemVO, Double> unitPriceColumn = new JFXTreeTableColumn<>("单价");
        unitPriceColumn.setPrefWidth(93);
        unitPriceColumn.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getValue().getUnitPrice()));

        JFXTreeTableColumn<PromotionGoodsItemVO, Integer> numColumn = new JFXTreeTableColumn<>("数量");
        numColumn.setPrefWidth(93);
        numColumn.setCellValueFactory(p -> p.getValue().getValue().numProperty().asObject());
        numColumn.setCellFactory((TreeTableColumn<PromotionGoodsItemVO, Integer> param) -> new GenericEditableTreeTableCell<>(
                new IntegerTextFieldEditorBuilder()));

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<PromotionGoodsItemVO> row = new JFXTreeTableRow<>();
//            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(e -> {
                if (row.getTreeItem() != null && e.getButton().equals(MouseButton.SECONDARY)) {
                    JFXPopup popup = new JFXPopup(); // 为了要传到下面
                    MyPopUpListView listView = new MyPopUpListView(popup);
                    listView.getItems().add(new PopUpDeleteLabel(() -> {
                        gifts.remove(row.getItem());
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

        setShowRoot(false);
        setEditable(true);
        getColumns().setAll(idColumn, nameColumn, unitPriceColumn, numColumn);
        setRoot(root);
    }

    public void add(PromotionGoodsItemVO promotionGoodsItemVO) {
        gifts.add(promotionGoodsItemVO);
    }

    public void setAll(ObservableList<PromotionGoodsItemVO> newGifts) {
        gifts = newGifts;
        TreeItem<PromotionGoodsItemVO> root = new RecursiveTreeItem<>(gifts, RecursiveTreeObject::getChildren);
        setRoot(root);
    }
}
