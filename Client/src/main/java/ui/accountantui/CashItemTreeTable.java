package ui.accountantui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.util.ColumnDecorator;
import ui.util.ListPopup;
import ui.util.PaneFactory;
import vo.billReceiptVO.CashItemVO;

import java.util.ArrayList;
import java.util.List;

public class CashItemTreeTable extends JFXTreeTableView<CashItemVO>{

    private ObservableList<CashItemVO> observableList = FXCollections.observableArrayList();

    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);


    StackPane mainpane;

    private CashItemTreeTable cashItemTreeTable;

    public CashItemTreeTable(){
        super();

        this.cashItemTreeTable  =this;
        mainpane = PaneFactory.getMainPane();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        JFXTreeTableColumn<CashItemVO, String> name = new JFXTreeTableColumn<>("条目名");
        name.setPrefWidth(140);
        columnDecorator.setupCellValueFactory(name,CashItemVO::nameProperty);

        JFXTreeTableColumn<CashItemVO, Double> price = new JFXTreeTableColumn<>("金额");
        price.setPrefWidth(140);
        columnDecorator.setupCellValueFactory(price, l -> l.priceProperty().asObject());

        JFXTreeTableColumn<CashItemVO, String> comment = new JFXTreeTableColumn<>("备注");
        comment.setPrefWidth(140);
        columnDecorator.setupCellValueFactory(comment,CashItemVO::commentProperty);

        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");

            row.setOnMouseClicked((MouseEvent event) -> {
                CashItemVO cashItemVO = (CashItemVO)row.getTreeItem().getValue();

                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            CashItemPane cashItemPane = new CashItemPane(cashItemVO,observableList,cashItemTreeTable);
                            JFXDialog dialog = new JFXDialog(mainpane, cashItemPane, JFXDialog.DialogTransition.CENTER);
                            cashItemPane.setDialog(dialog);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    listPopup.getListdelete().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            observableList.remove(cashItemVO);
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                }
                if(event.getClickCount() == 2){
                    CashItemPane cashItemPane = new CashItemPane(cashItemVO, observableList,this);
                    JFXDialog dialog = new JFXDialog(mainpane, cashItemPane, JFXDialog.DialogTransition.CENTER);
                    cashItemPane.setDialog(dialog);
                    dialog.show();
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

        observableList.addListener(new ListChangeListener<CashItemVO>() {
            @Override
            public void onChanged(Change<? extends CashItemVO> c) {
                sum.set(0);
                double s = 0;
                for(CashItemVO cashItemVO:observableList){
                    s+=cashItemVO.getPrice();
                }
                sum.set(s);
            }
        });

        TreeItem<CashItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(name,price,comment);


    }


    public void setList(List<CashItemVO> list){
            observableList.setAll(list);
    }

    public void remove(CashItemVO cashItemVO){
        observableList.remove(cashItemVO);
    }

    public void add(CashItemVO cashItemVO){
        observableList.add(cashItemVO);
    }

    public ArrayList<CashItemVO> getList(){
        return new ArrayList<>(observableList);
    }



    public double getSum() {
        return sum.get();
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }
}
