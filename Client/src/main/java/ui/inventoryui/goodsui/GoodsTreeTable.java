package ui.inventoryui.goodsui;

import blService.goodsblService.GoodsblService;
import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.util.Callback;
import ui.common.BoardController;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.util.*;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class GoodsTreeTable extends MyTreeTableBorderPane<GoodsVO> {
    public GoodsTreeTable(Set<GoodsVO> chosenItems) {

        JFXTreeTableColumn<GoodsVO, Boolean> choose = new ChooseColumn<>(chosenItems);

        JFXTreeTableColumn<GoodsVO, String> name = new JFXTreeTableColumn<>("商品名称");
        name.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, String> param) -> {
            if (name.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodName());
            } else {
                return name.getComputedValue(param);
            }
        });
        name.setPrefWidth(150);

        JFXTreeTableColumn<GoodsVO, String> id = new JFXTreeTableColumn<>("编号");
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, String> param) -> {
            if (id.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getId());
            } else {
                return id.getComputedValue(param);
            }
        });
        id.setPrefWidth(150);

        JFXTreeTableColumn<GoodsVO,String> goodType = new JFXTreeTableColumn<>("商品类型");
        goodType.setPrefWidth(150);
        goodType.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, String> param) -> {
            if (goodType.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodType());
            } else {
                return goodType.getComputedValue(param);
            }
        });
        goodType.setPrefWidth(150);

        JFXTreeTableColumn<GoodsVO, Integer> inventoryNum = new JFXTreeTableColumn<>("库存数量");
        inventoryNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, Integer> param) -> {
            if (inventoryNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getInventoryNum());
            } else {
                return inventoryNum.getComputedValue(param);
            }
        });

        myTreeTable.getColumns().addAll(choose,name, id, goodType, inventoryNum);
    }




    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<GoodsVO> row) {
        try {
            (row.getTreeItem().getValue().getDetailPane()).refresh(false);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
