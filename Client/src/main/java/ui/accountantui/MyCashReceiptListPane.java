package ui.accountantui;

import blService.billblservice.CashBillReceiptblService;
import blService.genericblService.ReceiptblService;
import ui.common.bigPane.MyReceiptListPane;
import ui.util.RefreshablePane;
import vo.billReceiptVO.CashReceiptListVO;
import vo.billReceiptVO.CashReceiptVO;

public class MyCashReceiptListPane extends MyReceiptListPane<CashReceiptListVO, CashReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new MyCashTablePane(chosenItems, searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<CashReceiptVO>> getServiceClass() {
        return CashBillReceiptblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new MyCashDetailPane();
    }
}
