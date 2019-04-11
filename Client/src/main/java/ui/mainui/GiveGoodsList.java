package ui.mainui;

import blService.goodsblService.GoodsblService;
import businesslogic.goodsbl.Goodsbl;
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
import ui.salesui.GoodsChooseCell;
import ui.util.PaneFactory;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GiveGoodsList extends StackPane {

    ObservableList<ReceiptGoodsItemVO> observableList= FXCollections.observableArrayList();
    @FXML
    private JFXListView<ReceiptGoodsItemVO> list;


    public GiveGoodsList(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/salesui/goodsList.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

            list.setCellFactory(new Callback<ListView<ReceiptGoodsItemVO>, ListCell<ReceiptGoodsItemVO>>() {
                @Override
                public ListCell<ReceiptGoodsItemVO> call(ListView<ReceiptGoodsItemVO> param) {
                    GiveGoodsCell goodsChooseCell = new GiveGoodsCell();
                    return goodsChooseCell;
                }

            });
            this.getStylesheets().remove(0);
            this.getStylesheets().add("/css/giveList.css");
            list.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void setList(ArrayList<ReceiptGoodsItemVO> receiptGoodsItemVOS) {
        this.observableList.setAll(receiptGoodsItemVOS);
    }
}
