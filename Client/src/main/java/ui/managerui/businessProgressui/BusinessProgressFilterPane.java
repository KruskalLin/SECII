package ui.managerui.businessProgressui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.controlsfx.control.PopOver;
import ui.common.bigPane.FXMLAnchorPane;
import util.BillType;
import util.ReceiptSearchCondition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BusinessProgressFilterPane extends FXMLAnchorPane {
    @FXML
    private JFXDatePicker beginTime, endTime;
    @FXML
    private JFXButton select;
    @FXML
    private JFXCheckBox salesSellBox, salesRetBox, stockPurBox, stockRetBox, paymentBox, chargeBox, cashBox, overflowBox, damageBox, giftBox;
    @FXML
    private JFXTextField memberField, clerkField, stockField;

    private ReceiptSearchCondition receiptSearchCondition;


    public BusinessProgressFilterPane(PopOver popOver, BusinessProgressPane businessProgressPane, ReceiptSearchCondition receiptSearchCondition) {
        this.receiptSearchCondition = receiptSearchCondition;

        beginTime.setValue(LocalDate.now());
        endTime.setValue(LocalDate.now());

        select.setOnAction(e -> {
            updateSearchCondition();
            popOver.hide();
            businessProgressPane.refresh(false);
        });
    }

    @Override
    protected String getURL() {
        return "/managerui/businessProgressFilterPane.fxml";
    }

    private void updateSearchCondition() {
        System.out.println(receiptSearchCondition);
        receiptSearchCondition.setBegin(LocalDateTime.of(beginTime.getValue(), LocalTime.MIN));
        receiptSearchCondition.setEnd(LocalDateTime.of(endTime.getValue(), LocalTime.MIN));

        receiptSearchCondition.getBillTypes().clear();
        if (salesSellBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.SalesSell);
        }
        if (salesRetBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.SalesRet);
        }
        if (stockPurBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.StockPur);
        }
        if (stockRetBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.StockRet);
        }
        if (paymentBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.BillPay);
        }
        if (chargeBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.BillCharge);
        }
        if (cashBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.Cash);
        }
        if (overflowBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.InventoryOverflow);
        }
        if (damageBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.InventoryDamage);
        }
        if (giftBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.InventoryGift);
        }

        receiptSearchCondition.setClerkName(clerkField.getText().trim().equals("") ? null : clerkField.getText());
        receiptSearchCondition.setStockName(stockField.getText().trim().equals("") ? null : stockField.getText());
        try {
            receiptSearchCondition.setMemberId(Integer.parseInt(memberField.getText()));
        } catch (NumberFormatException e) {
            receiptSearchCondition.setMemberId(null);
        }

//        System.out.println("Finish updateSearchCondition");
    }


}
