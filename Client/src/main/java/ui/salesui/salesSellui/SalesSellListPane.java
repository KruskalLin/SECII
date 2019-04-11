package ui.salesui.salesSellui;

import blService.genericblService.ReceiptblService;
import blService.salesblService.SalesSellblService;
import ui.salesui.SalesListPane;
import ui.util.RefreshablePane;
import vo.receiptVO.SalesSellListVO;
import vo.receiptVO.SalesSellReceiptVO;

public class SalesSellListPane extends SalesListPane<SalesSellListVO,SalesSellReceiptVO> {


    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new SalesSellTablePane(chosenItems, searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<SalesSellReceiptVO>> getServiceClass() {
        return SalesSellblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new SalesSellDetailPane();
    }
}