package data.inventorydata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.InventoryWarningReceiptPOMapper;
import po.receiptPO.InventoryWarningReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryWarningReceiptData extends ReceiptData<InventoryWarningReceiptPO> {
    public InventoryWarningReceiptData() throws RemoteException {
        super(InventoryWarningReceiptPOMapper.class, InventoryWarningReceiptPO.class);
    }
}
