package ui.inventoryui.inventoryReceiptui;

import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import ui.common.bigPane.MyReceiptListPane;
import ui.util.RefreshablePane;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;

public class InventoryGiftListPane extends MyReceiptListPane<InventoryGiftListVO, InventoryGiftReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryGiftTablePane(chosenItems,searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryGiftReceiptVO>> getServiceClass() {
        return InventoryGiftReceiptblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new InventoryGiftDetailPane();
    }
}
