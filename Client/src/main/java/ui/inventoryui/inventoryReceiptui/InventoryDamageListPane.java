package ui.inventoryui.inventoryReceiptui;

import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import ui.common.bigPane.MyReceiptListPane;
import ui.util.RefreshablePane;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;

public class InventoryDamageListPane  extends MyReceiptListPane<InventoryDamageListVO, InventoryDamageReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryDamageTablePane(chosenItems,searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryDamageReceiptVO>> getServiceClass() {
        return InventoryDamageReceiptblService.class;
    }

    @Override
    protected RefreshablePane getNewDetailPane() {
        return new InventoryDamageDetailPane();
    }
}