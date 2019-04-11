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
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;
import java.util.List;

public class PaymentItemTreeTable extends JFXTreeTableView<TransferItemVO>{

    private ObservableList<TransferItemVO> observableList = FXCollections.observableArrayList();

    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);

    private StackPane mainpane;

    private PaymentItemTreeTable paymentItemTreeTable;

    public PaymentItemTreeTable(){
        super();

        this.paymentItemTreeTable = this;
        mainpane = PaneFactory.getMainPane();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        JFXTreeTableColumn<TransferItemVO, Integer> accountID = new JFXTreeTableColumn<>("银行账户");
        accountID.setPrefWidth(140);
        columnDecorator.setupCellValueFactory(accountID, l -> l.accountIDProperty().asObject());

        JFXTreeTableColumn<TransferItemVO, Double> price = new JFXTreeTableColumn<>("转账金额");
        price.setPrefWidth(140);
        columnDecorator.setupCellValueFactory(price, l -> l.sumProperty().asObject());

        JFXTreeTableColumn<TransferItemVO, String> comment = new JFXTreeTableColumn<>("备注");
        comment.setPrefWidth(140);
        columnDecorator.setupCellValueFactory(comment,TransferItemVO::commentProperty);

        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");

            row.setOnMouseClicked((MouseEvent event) -> {
                TransferItemVO transferItemVO = (TransferItemVO)row.getTreeItem().getValue();

                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            TransferItemPane transferItemPane = new TransferItemPane(transferItemVO,observableList,paymentItemTreeTable);
                            JFXDialog dialog = new JFXDialog(mainpane, transferItemPane, JFXDialog.DialogTransition.CENTER);
                            transferItemPane.setDialog(dialog);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    listPopup.getListdelete().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            observableList.remove(transferItemVO);
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                }
                if(event.getClickCount() == 2){
                    TransferItemPane transferItemPane = new TransferItemPane(transferItemVO, observableList,this);
                    JFXDialog dialog = new JFXDialog(mainpane, transferItemPane, JFXDialog.DialogTransition.CENTER);
                    transferItemPane.setDialog(dialog);
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

        observableList.addListener(new ListChangeListener<TransferItemVO>() {
            @Override
            public void onChanged(Change<? extends TransferItemVO> c) {
                sum.set(0);
                double s = 0;
                for(TransferItemVO transferItemVO:observableList){
                    s+=transferItemVO.getSum();
                }
                sum.set(s);
            }
        });

        TreeItem<TransferItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(accountID,price,comment);


    }


    public void setList(List<TransferItemVO> list){
        observableList.setAll(list);
    }

    public void remove(TransferItemVO transferItemVO){
        observableList.remove(transferItemVO);
    }

    public ArrayList<TransferItemVO> getList(){
        return new ArrayList<>(observableList);
    }


    public void add(TransferItemVO transferItemVO){
        observableList.add(transferItemVO);
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
