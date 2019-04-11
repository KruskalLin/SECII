package ui.salesui;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.util.*;
import util.ReceiptState;
import vo.receiptVO.SalesReceiptListVO;

import java.util.Set;


public abstract class SalesTreeTable<T extends SalesReceiptListVO<T>> extends MyTreeTableBorderPane<T> {



    public SalesTreeTable(Set<T> chosenItems, StringProperty keywordProperty) {

        JFXTreeTableColumn<T, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<T, String> id = new SearchableStringColumn<>("ID", 200, keywordProperty, p -> p.getId());
        JFXTreeTableColumn<T, String> member = new SearchableStringColumn<>("客户", 100, keywordProperty, p -> p.getMemberName());
        JFXTreeTableColumn<T, String> stateColumn = new JFXTreeTableColumn<>("类型");
        JFXTreeTableColumn<T, String> stock = new SearchableStringColumn<>("仓库", 100, keywordProperty, p -> p.getStockName());

        stateColumn.setPrefWidth(150);
        stateColumn.setCellValueFactory(param -> {
            System.out.println(param.getValue().getValue().getReceiptState().name());
            return new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name());
        });
        stateColumn.setCellFactory(param -> new ButtonCell<T>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(ReceiptState.color.get(item));
                    setCivText(ReceiptState.chinese.get(ReceiptState.map.get(item)));

                }
            }
        });

        myTreeTable.getColumns().addAll(choose,id,member,stock,stateColumn);
    }

   /* //   private ObservableList<SalesReceiptListVO> observableListfilter = observableList;
    //  private ObservableList<SalesReceiptListVO> observableListtemp;
    private SalesblService salesblService;
    public SalesTreeTable() {
        super();
        rowsPerPage = 7;
        salesblService = ServiceFactory_Stub.getService(SalesblService.class.getName());
        JFXTreeTableColumn<SalesReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<SalesReceiptListVO>(chosenItem));


        JFXTreeTableColumn<SalesReceiptListVO, String> time = new JFXTreeTableColumn("Id");
        time.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(time, s -> new ReadOnlyObjectWrapper(s.getId()));
        time.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<SalesReceiptListVO, String> member = new JFXTreeTableColumn("Member");
        member.setPrefWidth(85);
        columnDecorator.setupCellValueFactory(member, s -> new ReadOnlyObjectWrapper(s.getMemberName()));
        member.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<SalesReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<SalesReceiptListVO, String>, TreeTableCell<SalesReceiptListVO, String>>() {
            @Override
            public TreeTableCell<SalesReceiptListVO, String> call(TreeTableColumn<SalesReceiptListVO, String> param) {
                return new ButtonCell() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setButtonStyle(ReceiptState.color.get(item));
                        }
                    }
                };
            }
        });
        columnDecorator.setupCellValueFactory(state, s -> new ReadOnlyObjectWrapper(s.getReceiptState().name()));

        JFXTreeTableColumn<SalesReceiptListVO, Integer> sum = new JFXTreeTableColumn("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));

        JFXTreeTableColumn<SalesReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<SalesReceiptListVO, Boolean>, TreeTableCell<SalesReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<SalesReceiptListVO, Boolean> call(TreeTableColumn<SalesReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{SalesReceiptPane salesReceiptPane = new SalesReceiptPane(
                        ((SalesReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()));
                salesReceiptPane.refresh(true);});
                multiCell.setRunnable2(()->{
                    try {
                        salesblService.delete(((SalesReceiptListVO) multiCell.getTreeTableRow().getTreeItem().getValue()).getId(),((SalesReceiptListVO) multiCell.getTreeTableRow().getTreeItem().getValue()).getCreateTime());

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<SalesReceiptListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{ SalesReceiptPane salesReceiptPane = new SalesReceiptPane(row.getTreeItem().getValue()); salesReceiptPane.refresh(true);});
            return row;
        });


        this.getColumns().addAll(choose, time, member,sum, state, more);

    }



    public void delete(Pagination p) {
        System.out.println(chosenItem);
        System.out.println(observableList);

        chosenItem.getSet().forEach(s -> {
            try {
                observableList.remove(s);
                salesblService.delete(s.getId(),s.getCreateTime());
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }
    private <TF extends SalesReceiptVO> TF kengdie(SalesReceiptVO salesReceiptVO) {
        try {
            return (TF) salesReceiptVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
