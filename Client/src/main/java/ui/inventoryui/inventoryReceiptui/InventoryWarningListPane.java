package ui.inventoryui.inventoryReceiptui;

import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryWarningReceiptblService;
import ui.common.bigPane.MyReceiptListPane;
import ui.util.RefreshablePane;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;

public class InventoryWarningListPane extends MyReceiptListPane<InventoryWarningListVO, InventoryWarningReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryWarningTablePane(chosenItems,searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<InventoryWarningReceiptVO>> getServiceClass() {
        return InventoryWarningReceiptblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new InventoryWarningDetailPane();
    }
}
