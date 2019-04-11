package ui.chooseGoods;

import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.salesui.GoodsChooseCell;
import vo.inventoryVO.GoodsVO;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.function.Consumer;

public class MyGoodsList extends StackPane implements FXMLLoadableMixer {
    @FXML
    private JFXListView<GoodsVO> list;

    public MyGoodsList(Consumer<GoodsVO> clickAction) {
        load();
        try {
            list.setCellFactory(param -> {
                GoodsChooseCell goodsChooseCell = new GoodsChooseCell();
                goodsChooseCell.setOnMouseClicked(e -> {
                    if (e.getClickCount() == 2) {
                        clickAction.accept(goodsChooseCell.getItem());
                    }
                });

                return goodsChooseCell;
            });

            Set<GoodsVO> goods = MyServiceFactory.getGoodsblService().show();
            ObservableList<GoodsVO> goodsList = FXCollections.observableArrayList(goods);
            list.setItems(goodsList);
        } catch (RemoteException e) {
            e.printStackTrace();
            new MyOneButtonDialog("连接错误").show();
        }
    }

    @Override
    public String publicGetURL() {
        return "/salesui/goodsList.fxml";
    }
}
