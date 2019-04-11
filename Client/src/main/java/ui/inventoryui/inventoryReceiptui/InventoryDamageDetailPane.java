package ui.inventoryui.inventoryReceiptui;

import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import com.jfoenix.controls.JFXRippler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.chooseGoods.ChooseGoodsDialog;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.inventoryui.inventoryReceiptui.common.DamageAndOverflowGoodsItemTreeTable;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDamageDetailPane extends MyReceiptDetailPane<InventoryDamageReceiptVO> {
    @FXML
    private DamageAndOverflowGoodsItemTreeTable damageItemTreeTable;

    private ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
    @FXML
    private TextField stateField;
    @FXML
    private JFXRippler addGoodsButton;



    public InventoryDamageDetailPane() {
        super();

    }

    public InventoryDamageDetailPane(InventoryDamageReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected void initiate() {
        super.initiate();
        stateField.setDisable(true);
        operator.setDisable(true);

        commentArea.disableProperty().bind(modify.modifyProperty().not());
        addGoodsButton.visibleProperty().bind(modify.modifyProperty());
        damageItemTreeTable.editableProperty().bind(modify.modifyProperty());
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryDamageDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryDamageReceiptVO>> getServiceClass() {
        return InventoryDamageReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        if (super.validate()) {
            if (observableList.isEmpty()) {
                new MyOneButtonDialog("不可上交空单据").show();
                return false;
            }
            for (ReceiptGoodsItemVO goodsItemVO : observableList) {
                if (goodsItemVO.getFactNum() <= 0) {
                    new MyOneButtonDialog("报告实际数量不可为0或者负数").show();
                    return false;
                }
                if (goodsItemVO.getFactNum() >= goodsItemVO.getInventoryNum()) {
                    new MyOneButtonDialog("报损单报告实际数量必须小于库存数量").show();
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected void updateReceiptVO() { // 这里不需要再检查了
        List<ReceiptGoodsItemVO> goodsList = new ArrayList<>(observableList);
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);
    }

    @Override
    protected void setRedCredit(InventoryDamageReceiptVO redCreditVO) {
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

        damageItemTreeTable.refresh(observableList = FXCollections.observableArrayList(receiptVO.getItems()));
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
