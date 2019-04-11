package ui.stockui.stockPurui;

import blService.genericblService.ReceiptblService;
import blService.stockblService.StockPurblService;
import ui.stockui.StockListPane;
import ui.util.RefreshablePane;
import vo.receiptVO.StockPurListVO;
import vo.receiptVO.StockPurReceiptVO;

public class StockPurListPane extends StockListPane<StockPurListVO,StockPurReceiptVO> {


    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new StockPurTablePane(chosenItems, searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<StockPurReceiptVO>> getServiceClass() {
        return StockPurblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new StockPurDetailPane();
    }

}
