package ui.inventoryui.goodsui;

import blService.goodsblService.GoodsblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;

import ui.common.BoardController;
import ui.common.bigPane.ListPane;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.*;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsListPane extends ListPane<GoodsVO> {
    private Set<GoodsVO> chosenItems;

    private Set<GoodsVO> set;
    
    private GoodsblService goodsblService;

    private static GoodSearchVO goodSearchVO = new GoodSearchVO();
    
    @FXML
    JFXRippler search;

    Pagination pagination;

    SimpleStringProperty match = new SimpleStringProperty("");

    public GoodsListPane() throws Exception {
    }

    @Override
    protected String getURL() {
        return "/inventoryui/goodui/goodslistpane.fxml";
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        goodsblService = MyblServiceFactory.getService(GoodsblService.class);
    }

    @Override
    protected void initiateFields() {
        super.initiateFields();
        chosenItems = new HashSet<>();
    }

    @Override
    protected MyTreeTableBorderPane<GoodsVO> getInitialTreeTable() {
        return new GoodsTreeTable(chosenItems);
    }

    @Override
    protected ArrayList<GoodsVO> getNewListData() throws RemoteException {
        Set<GoodsVO> goodsVOSet = goodsblService.show();
        set = new HashSet<>(goodsVOSet);
        return new ArrayList<>(goodsVOSet);
    }

    public void refresh(boolean toSwitch) {
        super.refresh(toSwitch);
    }

    @FXML
    public void deleteList() {
        if(chosenItems.size()==0){
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","请选择商品","继续");
            oneButtonDialog.setButtonOne(()->{});
            oneButtonDialog.show();
        }else {
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "删除商品", "确定删除？", "确定", "取消");
            doubleButtonDialog.setButtonOne(() -> {
                for (GoodsVO goodsVO:chosenItems) {
                    try {
                        goodsblService.deleteGoods(goodsVO);
                        chosenItems.remove(goodsVO);
                        refresh(true);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
            doubleButtonDialog.setButtonTwo(() -> {
                BoardController.getBoardController().goBack();
            });
            doubleButtonDialog.show();

        }
    }

    @FXML
    public void search() {
        if (keywordField.getText() != ""&&keywordField.getText() != null) {
            match.setValue(keywordField.getText().toLowerCase());
            Set<GoodsVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getGoodName().contains(match.get()) ||
                            s.getGoodType().contains(match.get()) ||
                            s.getId().contains(match.get()) ||
                            Integer.toString(s.getInventoryNum()).contains(match.get())
            ).collect(Collectors.toSet());
            receiptListTreeTable.refresh(new ArrayList<>(hashSet));
        }

    }

    @FXML
    public void add() throws RemoteException, NotBoundException, MalformedURLException {
        GoodDetailPane goodDetailPane = new GoodDetailPane(true);
    }

    private void warningDialog(String infomation){

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(220.0);
        jfxDialogLayout.setHeading(new Label("警告"));
        JFXTextField name = new JFXTextField();
        jfxDialogLayout.setBody(new JFXTextField(infomation));
        JFXButton back = new JFXButton("取消");
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        back.setOnAction(e -> {
            dialog.close();
        });
        jfxDialogLayout.setActions(back);
        dialog.show();
    }

}
