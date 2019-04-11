package ui.common;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TreeItem;
import ui.util.ColumnDecorator;
import vo.ListGoodsItemVO;

import java.rmi.server.ExportException;
import java.util.List;

public class ItemTreeTable extends MyListItemTablePane<ListGoodsItemVO> {
    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);

    public ItemTreeTable() {
        super();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        JFXTreeTableColumn<ListGoodsItemVO, String> goodsid = new JFXTreeTableColumn<>("ID");
        goodsid.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getValue().getGoodsId()));
        goodsid.setPrefWidth(112.5);

        JFXTreeTableColumn<ListGoodsItemVO, String> name = new JFXTreeTableColumn<>("商品名");
        name.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getValue().getGoodsName()));
        name.setPrefWidth(112.5);

        JFXTreeTableColumn<ListGoodsItemVO, Double> price = new JFXTreeTableColumn<>("价格");
        columnDecorator.setupCellValueFactory(price, t -> t.priceProperty().asObject());
        price.setPrefWidth(112.5);

        JFXTreeTableColumn<ListGoodsItemVO, Integer> num = new JFXTreeTableColumn<>("数量");
        columnDecorator.setupCellValueFactory(num, t -> t.goodsNumProperty().asObject());
        num.setPrefWidth(112.5);

        this.getColumns().setAll(goodsid, name, price, num);

        TreeItem<ListGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
    }

    @Override
    public void initial(ListGoodsItemVO vo) {
        myListItemPane = new ListItemPane(vo, this);
    }

    @Override
    public void refresh() {
        sum.set(0);
        for (ListGoodsItemVO listGoodsItemVO : observableList) {
            sum.set(listGoodsItemVO.getSum() + sum.get());
        }
    }

    public boolean contain(ListGoodsItemVO listGoodsItemVO) {
        for (ListGoodsItemVO listGoodsItem : observableList) {
            if (listGoodsItemVO.getGoodsId().equals(listGoodsItem.getGoodsId())) {
                return true;
            }
        }
        return false;
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

    public boolean refreshAndCheck() {
        boolean flag = true;
        for (ListGoodsItemVO listGoodsItemVO : observableList) {
            try {
                flag = flag & listGoodsItemVO.setInventoryNumAndCheck();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
