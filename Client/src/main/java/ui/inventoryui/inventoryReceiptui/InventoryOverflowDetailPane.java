package ui.inventoryui.inventoryReceiptui;

import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import com.jfoenix.controls.JFXRippler;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.chooseGoods.ChooseGoodsDialog;
import ui.common.dialog.MyOneButtonDialog;
import ui.inventoryui.goodsui.GoodChoose;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.inventoryui.inventoryReceiptui.common.DamageAndOverflowGoodsItemTreeTable;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InventoryOverflowDetailPane extends MyReceiptDetailPane<InventoryOverflowReceiptVO> {
    @FXML
    private DamageAndOverflowGoodsItemTreeTable overflowItemTreeTable;

    private ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
    @FXML
    private TextField stateField;
    @FXML
    private JFXRippler addGoodsButton;

    public InventoryOverflowDetailPane() {
    }

    public InventoryOverflowDetailPane(InventoryOverflowReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected void initiate() {
        super.initiate();
        stateField.setDisable(true);
        operator.setDisable(true);

        commentArea.disableProperty().bind(modify.modifyProperty().not());
        addGoodsButton.visibleProperty().bind(modify.modifyProperty());
        overflowItemTreeTable.editableProperty().bind(modify.modifyProperty());
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryOverflowDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryOverflowReceiptVO>> getServiceClass() {
        return InventoryOverflowReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        if (super.validate()) {
            if (observableList.isEmpty()) {
                new MyOneButtonDialog("不可上交空单据").show();
                return false;
            }
            for (ReceiptGoodsItemVO goodsItemVO : observableList) {
                if (goodsItemVO.getFactNum() <= goodsItemVO.getInventoryNum()) {
                    new MyOneButtonDialog("报溢单报告实际数量必须大于库存数量").show();
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected void updateReceiptVO() {
        List<ReceiptGoodsItemVO> goodsList = new ArrayList<>(observableList);
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);

    }

    @Override
    protected void setRedCredit(InventoryOverflowReceiptVO redCreditVO) {
        List<ReceiptGoodsItemVO> list = receiptVO.getItems();
        ArrayList<ReceiptGoodsItemVO> temp = new ArrayList<>();

        for (ReceiptGoodsItemVO vo : list) {
            ReceiptGoodsItemVO toBeAdd = new ReceiptGoodsItemVO();

            toBeAdd.setGoodsId(vo.getGoodsId());
            toBeAdd.setGoodsName(vo.getGoodsName());
            toBeAdd.setGoodsType(vo.getGoodsType());
            toBeAdd.setPrice(vo.getPrice());
            toBeAdd.setInventoryNum(vo.getInventoryNum());
            toBeAdd.setFactNum(vo.getFactNum() * (-1));

            temp.add(toBeAdd);
        }
        redCreditVO.setItems(temp);
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText(receiptVO.getComment());
        stateField.setText(receiptVO.getReceiptState().toString());

        overflowItemTreeTable.refresh(observableList = FXCollections.observableArrayList(receiptVO.getItems()));
    }

    @FXML
    public void addGoods() throws RemoteException, NotBoundException, MalformedURLException {
        new ChooseGoodsDialog((g) -> {
            if (observableList.stream().anyMatch(gi -> gi.getGoodsId().equals(g.getId()))) {
                new MyOneButtonDialog("不可重复添加").show();
            } else {
                observableList.add(new ReceiptGoodsItemVO(g));
            }
        }).show();
    }
}
