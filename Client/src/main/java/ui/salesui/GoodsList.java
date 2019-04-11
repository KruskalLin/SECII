package ui.salesui;

import blService.goodsblService.GoodsblService;
import blService.memberblService.MemberInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.memberbl.MemberInfo_Impl;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import ui.common.ItemTreeTable;
import ui.common.ListItemPane;
import ui.memberui.ChooseListCell;
import ui.util.PaneFactory;
import vo.ListGoodsItemVO;
import vo.MemberVO;
import vo.inventoryVO.GoodsVO;

import java.util.Set;

public class GoodsList extends StackPane {


    private ObservableList<GoodsVO> observableList = FXCollections.observableArrayList();

    private JFXDialog dialog;


    @FXML
    private JFXListView<GoodsVO> list;


    public GoodsList(ItemTreeTable itemTreeTable) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/salesui/goodsList.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

            list.setCellFactory(new Callback<ListView<GoodsVO>, ListCell<GoodsVO>>() {
                @Override
                public ListCell<GoodsVO> call(ListView<GoodsVO> param) {
                    GoodsChooseCell goodsChooseCell = new GoodsChooseCell();

                    goodsChooseCell.setOnMouseClicked(t -> {
                        if (t.getClickCount() == 2) {
                            ListGoodsItemVO listGoodsItemVO = new ListGoodsItemVO(goodsChooseCell.getItem());
                            if (!itemTreeTable.contain(listGoodsItemVO)) {
                                itemTreeTable.add(listGoodsItemVO);
                                Platform.runLater(() -> {
                                    ListItemPane listItemPane = new ListItemPane(listGoodsItemVO, itemTreeTable);
                                    JFXDialog jdialog = new JFXDialog(PaneFactory.getMainPane(), listItemPane, JFXDialog.DialogTransition.CENTER);
                                    listItemPane.setDialog(jdialog);
                                    jdialog.show();
                                });
                            }
                        }
                    });
                    return goodsChooseCell;
                }

            });
            Set<GoodsVO> goods = MyServiceFactory.getGoodsblService().show();
            observableList.setAll(goods);
            list.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public JFXDialog getDialog() {
        return dialog;
    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
