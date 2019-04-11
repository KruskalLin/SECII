package ui.inventoryui.inventoryReceiptui;

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
import ui.util.ColumnDecorator;
import ui.util.ListPopup;
import ui.util.PaneFactory;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.ArrayList;
import java.util.List;

public class InventoryWarningTreeTable extends JFXTreeTableView<ReceiptGoodsItemVO> {
    private ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();


    private BoardController boardController;
    private StackPane mainpane;

    public InventoryWarningTreeTable() {
        super();


        mainpane = PaneFactory.getMainPane();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        this.setEditable(true);

        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsID = new JFXTreeTableColumn<>("GoodsID");
        goodsID.setPrefWidth(115.5);
        goodsID.setEditable(true);
        columnDecorator.setupCellValueFactory(goodsID, ReceiptGoodsItemVO::goodsIdProperty);
        // goodsID.setCellFactory(TextFieldTableCell.forTableColumn());
        goodsID.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, String> param) -> {
            return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        goodsID.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsIdProperty().set(t.getNewValue());
        });



        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsName = new JFXTreeTableColumn<>("GoodsName");
        goodsName.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(goodsName, ReceiptGoodsItemVO::goodsNameProperty);
        goodsName.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, String> param) -> {
            return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        goodsName.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsNameProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> inventoryNum = new JFXTreeTableColumn<>("InventoryNum");
        inventoryNum.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(inventoryNum, l -> l.inventoryNumProperty().asObject());
        inventoryNum.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        inventoryNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, Integer> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().inventoryNumProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> warningNum = new JFXTreeTableColumn<>("WarningNum");
        warningNum.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(warningNum, l -> l.warningNumProperty().asObject());
        warningNum.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        warningNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, Integer> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().sendNumProperty().set(t.getNewValue());
        });

        TreeItem<ReceiptGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(goodsID, goodsName, inventoryNum, warningNum);
    }


    public void setList(List<ReceiptGoodsItemVO> goods) {
        observableList.setAll(goods);
    }

    public void removeGood(ReceiptGoodsItemVO good) {
        observableList.remove(good);
    }

    public void addGood(ReceiptGoodsItemVO good) {
        observableList.add(good);
    }

    public void clear(){
        observableList.clear();
    }

    public ArrayList<ReceiptGoodsItemVO> getList(){
        ArrayList<ReceiptGoodsItemVO> arrayList = new ArrayList<>();
        observableList.forEach(i->arrayList.add(i));
        return arrayList;
    }
}
