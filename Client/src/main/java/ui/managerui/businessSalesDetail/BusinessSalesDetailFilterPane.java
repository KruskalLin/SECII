package ui.managerui.businessSalesDetail;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import ui.common.bigPane.FXMLAnchorPane;
import util.ReceiptSearchCondition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BusinessSalesDetailFilterPane extends FXMLAnchorPane {
    @FXML
    private JFXDatePicker beginTime, endTime;
    @FXML
    private JFXButton select;
    @FXML
    private JFXTextField memberField, clerkField, stockField;

    private ReceiptSearchCondition receiptSearchCondition;

    public BusinessSalesDetailFilterPane(BusinessSalesListPane businessSalesListPane, ReceiptSearchCondition receiptSearchCondition) {
        this.receiptSearchCondition = receiptSearchCondition;

        beginTime.setValue(LocalDate.now());
        endTime.setValue(LocalDate.now());

        select.setOnAction(e -> {
            updateSearchCondition();
            businessSalesListPane.refresh(false);
        });
    }

    @Override
    protected String getURL() {
        return "/managerui/businessSalesFilterPane.fxml";
    }


    private void updateSearchCondition() {
        receiptSearchCondition.setBegin(LocalDateTime.of(beginTime.getValue(), LocalTime.MIN));
        receiptSearchCondition.setEnd(LocalDateTime.of(endTime.getValue(), LocalTime.MIN));

        if (clerkField.getText().trim().equals("")) {
            receiptSearchCondition.setClerkName(null);
        } else {
            receiptSearchCondition.setClerkName(clerkField.getText());
        }
        if (stockField.getText().trim().equals("")) {
            receiptSearchCondition.setStockName(null);
        } else {
            receiptSearchCondition.setStockName(stockField.getText());
        }
        try {
            receiptSearchCondition.setMemberId(Integer.parseInt(memberField.getText()));
        } catch (NumberFormatException e) {
            receiptSearchCondition.setMemberId(null);
        }
    }
}
