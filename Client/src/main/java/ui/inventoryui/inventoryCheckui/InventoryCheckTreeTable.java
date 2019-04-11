package ui.inventoryui.inventoryCheckui;

import blService.inventoryblService.InventoryCheckblService;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.*;
import vo.inventoryVO.InventoryCheckItemVO;

import java.util.Set;

public class InventoryCheckTreeTable extends MyTreeTableBorderPane<InventoryCheckItemVO> {
    private static InventoryCheckblService inventoryCheckblService;

    public InventoryCheckTreeTable() {
        JFXTreeTableColumn<InventoryCheckItemVO,String> idcol = new JFXTreeTableColumn<>("");
        idcol.setPrefWidth(60);
        idcol.setCellFactory((col) -> {
            TreeTableCell<InventoryCheckItemVO, String> cell = new TreeTableCell<InventoryCheckItemVO, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex()+1;
                        rowIndex += pagination.getCurrentPageIndex() * rowsPerPage;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });

        JFXTreeTableColumn<InventoryCheckItemVO, String> name = new JFXTreeTableColumn<>("商品名称");
        name.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (name.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodName());
            } else {
                return name.getComputedValue(param);
            }
        });
        name.setPrefWidth(75);

        JFXTreeTableColumn<InventoryCheckItemVO, String> id = new JFXTreeTableColumn<>("编号");
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (id.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodId());
            } else {
                return id.getComputedValue(param);
            }
        });
        id.setPrefWidth(100);

        JFXTreeTableColumn<InventoryCheckItemVO, Integer> inventoryNum = new JFXTreeTableColumn<>("库存数量");
        inventoryNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, Integer> param) -> {
            if (inventoryNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getInventoryNum());
            } else {
                return inventoryNum.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryCheckItemVO, Double> avePrice = new JFXTreeTableColumn<>("价格");
        avePrice.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, Double> param) -> {
            if (avePrice.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getAvePrice());
            } else {
                return avePrice.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<InventoryCheckItemVO, String> stockOutDate = new JFXTreeTableColumn<>("出厂日期");
        stockOutDate.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (stockOutDate.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockOutDate());
            } else {
                return stockOutDate.getComputedValue(param);
            }
        });
        stockOutDate.setPrefWidth(150);

        JFXTreeTableColumn<InventoryCheckItemVO, String> batch = new JFXTreeTableColumn<>("批次");
        batch.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (batch.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getBatch());
            } else {
                return batch.getComputedValue(param);
            }
        });
        stockOutDate.setPrefWidth(150);

        JFXTreeTableColumn<InventoryCheckItemVO, String> batchNum = new JFXTreeTableColumn<>("批号");
        batchNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (batchNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getBatchNum());
            } else {
                return batchNum.getComputedValue(param);
            }
        });
        stockOutDate.setPrefWidth(150);


        myTreeTable.getColumns().addAll(idcol,name, id,inventoryNum,avePrice,stockOutDate,batch,batchNum);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<InventoryCheckItemVO> row) {

    }

    public void setCheckItem(Set<InventoryCheckItemVO> set){observableList.setAll(set);}
}
