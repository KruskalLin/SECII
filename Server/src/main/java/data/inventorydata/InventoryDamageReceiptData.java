package data.inventorydata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.InventoryDamageReceiptPOMapper;
import po.receiptPO.InventoryDamageReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryDamageReceiptData extends ReceiptData<InventoryDamageReceiptPO> {
    public InventoryDamageReceiptData() throws RemoteException {
        super(InventoryDamageReceiptPOMapper.class, InventoryDamageReceiptPO.class);
    }
}
