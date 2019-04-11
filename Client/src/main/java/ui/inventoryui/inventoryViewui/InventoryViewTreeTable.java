package ui.inventoryui.inventoryViewui;

import blService.inventoryblService.InventoryViewblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeTableColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.ReceiptTreeTable;
import ui.util.SearchableStringCell;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;

import java.util.Set;

public class InventoryViewTreeTable extends MyTreeTableBorderPane<InventoryViewItemVO> {
    private static InventoryViewblService inventoryViewblService;

    public InventoryViewTreeTable() {
        super();
        rowsPerPage = 7;
        inventoryViewblService = MyblServiceFactory.getService(InventoryViewblService.class);


        JFXTreeTableColumn<InventoryViewItemVO, String> name = new JFXTreeTableColumn<>("商品名称");
        name.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, String> param) -> {
            if (name.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodName());
            } else {
                return name.getComputedValue(param);
            }
        });
        name.setPrefWidth(75);

        JFXTreeTableColumn<InventoryViewItemVO, String> id = new JFXTreeTableColumn<>("编号");
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, String> param) -> {
            if (id.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodId());
            } else {
                return id.getComputedValue(param);
            }
        });
        id.setPrefWidth(100);

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockInNum = new JFXTreeTableColumn<>("入库数量");
        stockInNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (stockInNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockInNum());
            } else {
                return stockInNum.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockInMoney = new JFXTreeTableColumn<>("入库金额");
        stockInMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (stockInMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockInMoney());
            } else {
                return stockInMoney.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockOutNum = new JFXTreeTableColumn<>("出库数量");
        stockOutNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (stockOutNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockOutNum());
            } else {
                return stockOutNum.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockOutMoney = new JFXTreeTableColumn<>("出库金额");
        stockOutMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (stockOutMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockOutMoney());
            } else {
                return stockOutMoney.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> saleNum = new JFXTreeTableColumn<>("销售数量");
        saleNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (saleNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getSaleNum());
            } else {
                return saleNum.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Double> saleMoney = new JFXTreeTableColumn<>("销售金额");
        saleMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (saleMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getSaleMoney());
            } else {
                return saleMoney.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockPurNum = new JFXTreeTableColumn<>("进货数量");
        stockPurNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (stockPurNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockPurNum());
            } else {
                return stockPurNum.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockPurMoney = new JFXTreeTableColumn<>("进货金额");
        stockPurMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (stockPurMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockPurMoney());
            } else {
                return stockPurMoney.getComputedValue(param);
            }
        });

        myTreeTable.getColumns().addAll(name,id,stockInNum,stockInMoney,stockOutNum,stockOutMoney,saleNum,saleMoney,stockPurNum,stockPurMoney);
        
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<InventoryViewItemVO> row) {

    }

    public void setViewItem(Set<InventoryViewItemVO> set){observableList.setAll(set);}
}
