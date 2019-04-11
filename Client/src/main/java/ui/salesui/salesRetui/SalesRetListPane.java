package ui.salesui.salesRetui;

import blService.genericblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import ui.salesui.SalesListPane;
import ui.util.RefreshablePane;
import vo.receiptVO.SalesRetListVO;
import vo.receiptVO.SalesRetReceiptVO;

public class SalesRetListPane extends SalesListPane<SalesRetListVO,SalesRetReceiptVO>{


    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new SalesRetTablePane(chosenItems, searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<SalesRetReceiptVO>> getServiceClass() {
        return SalesRetblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new SalesRetDetailPane();
    }


}
