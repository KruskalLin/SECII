package data.inventorydata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.InventoryGiftReceiptPOMapper;
import po.receiptPO.InventoryGiftReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryGiftReceiptData extends ReceiptData<InventoryGiftReceiptPO> {
    public InventoryGiftReceiptData() throws RemoteException {
        super(InventoryGiftReceiptPOMapper.class, InventoryGiftReceiptPO.class);
    }
}
