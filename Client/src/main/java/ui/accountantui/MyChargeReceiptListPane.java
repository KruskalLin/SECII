package ui.accountantui;

import blService.genericblService.ReceiptblService;
import ui.common.bigPane.MyReceiptListPane;
import ui.util.RefreshablePane;
import vo.billReceiptVO.ChargeReceiptListVO;
import vo.billReceiptVO.ChargeReceiptVO;

public class MyChargeReceiptListPane extends MyReceiptListPane<ChargeReceiptListVO, ChargeReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new MyChargeTablePane(chosenItems, searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<ChargeReceiptVO>> getServiceClass() {
        return blService.billblservice.ChargeBillReceiptblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new MyChargeDetailPane();
    }
}
