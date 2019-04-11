package test;

import data.inventorydata.InventoryDamageReceiptData;
import po.receiptPO.InventoryDamageReceiptPO;

import java.rmi.RemoteException;

public class TestInventoryData {
    public static void main(String[] args) throws RemoteException {
        InventoryDamageReceiptData inventoryDamageReceiptData = new InventoryDamageReceiptData();
        InventoryDamageReceiptPO idr = inventoryDamageReceiptData.getNew();
        System.out.println(idr);
    }
}
