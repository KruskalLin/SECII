package ui.inventoryui.inventoryReceiptui.common;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;
import ui.common.popupList.MyPopUpListView;
import ui.common.treeTableRelated.IntegerEditableColumn;
import ui.common.treeTableRelated.OrdinaryStringColumn;
import ui.inventoryui.inventoryReceiptui.PopUpDeleteLabel;
import ui.util.ColumnDecorator;
import ui.util.ListPopup;
import ui.util.PaneFactory;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;


import java.util.ArrayList;
import java.util.List;

// TODO 这个类本来应该当作父类来用的，但是不知道为什么fxml老是出错，所以只能用复制粘贴代码了
public class InventoryListItemTreeTable extends JFXTreeTableView<ReceiptGoodsItemVO> {
    private ObservableList<ReceiptGoodsItemVO> list;

    public InventoryListItemTreeTable() {
        super();

//        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsID = new JFXTreeTableColumn<>("编号");
//        goodsID.setPrefWidth(115.5);
//       // goodsID.setEditable(true);
//        columnDecorator.setupCellValueFactory(goodsID, ReceiptGoodsItemVO::goodsIdProperty);
//          goodsID.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, String> param) -> {
//            return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
//        });
//        goodsID.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, String> t) -> {
//            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsIdProperty().set(t.getNewValue());
//        });

        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsID = new OrdinaryStringColumn<>("编号", 115.5, "goodsId");


//        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsName = new JFXTreeTableColumn<>("名称");
//        goodsName.setPrefWidth(115.5);
//        columnDecorator.setupCellValueFactory(goodsName, ReceiptGoodsItemVO::goodsNameProperty);
//        goodsName.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, String> param) -> {
//            return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
//        });
//        goodsName.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, String> t) -> {
//            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsNameProperty().set(t.getNewValue());
//        });
        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsName = new OrdinaryStringColumn<>("名称", 115.5, "goodsName");


//        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> inventoryNum = new JFXTreeTableColumn<>("库存数量");
//        inventoryNum.setPrefWidth(115.5);
//        columnDecorator.setupCellValueFactory(inventoryNum, l -> l.inventoryNumProperty().asObject());
//        inventoryNum.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, Integer> param) -> {
//            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
//        });
//        inventoryNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, Integer> t) -> {
//            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().inventoryNumProperty().set(t.getNewValue());
//        });

        JFXTreeTableColumn<ReceiptGoodsItemVO, String> inventoryNum = new OrdinaryStringColumn<>("原库存数量", 115.5, p -> String.valueOf(p.getInventoryNum()));


//        ColumnDecorator columnDecorator = new ColumnDecorator();
//        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> sendNum = new JFXTreeTableColumn<>("报告数量");
//        sendNum.setPrefWidth(115.5);
//        columnDecorator.setupCellValueFactory(sendNum, l -> l.sendNumProperty().asObject());
//        sendNum.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, Integer> param) -> {
//            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
//        });
//        sendNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, Integer> t) -> {
//            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().sendNumProperty().set(t.getNewValue());
//        });

        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> sendNum = new IntegerEditableColumn<>("赠送数量", 115.5, ReceiptGoodsItemVO::sendNumProperty);



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
        this.getColumns().setAll(goodsID, goodsName, inventoryNum, sendNum);
    }


//    public void setList(List<ReceiptGoodsItemVO> goods) {
//        observableList.setAll(goods);
//    }
//
//    public void setList(ObservableList<ReceiptGoodsItemVO> list){observableList.setAll(list);}

    public void refresh(ObservableList<ReceiptGoodsItemVO> list) {
        this.list = list;
        TreeItem<ReceiptGoodsItemVO> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        this.setRoot(root);
    }

//    public void removeGood(ReceiptGoodsItemVO good) {
//        observableList.remove(good);
//    }
//
//    public void addGood(ReceiptGoodsItemVO good) {
//        observableList.add(good);
//    }
//
//    public void clear(){
//        observableList.clear();
//    }

//    public ArrayList<ReceiptGoodsItemVO> getList(){
//        ArrayList<ReceiptGoodsItemVO> arrayList = new ArrayList<>();
//        observableList.forEach(i->arrayList.add(i));
//        return arrayList;
//    }

}
