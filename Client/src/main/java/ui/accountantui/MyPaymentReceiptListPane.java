package ui.accountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.genericblService.ReceiptblService;
import ui.common.bigPane.MyReceiptListPane;
import ui.util.RefreshablePane;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;


public class MyPaymentReceiptListPane extends MyReceiptListPane<PaymentReceiptListVO, PaymentReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new MyPaymentTablePane(chosenItems, searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<PaymentReceiptVO>> getServiceClass() {
        return PaymentBillReceiptblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new MyPaymentDetailPane();
    }
}
