package data.salesdata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.SalesRetReceiptPOMapper;
import po.receiptPO.SalesRetReceiptPO;

import java.rmi.RemoteException;
@RMIRemote
public class SalesRetReceiptData extends ReceiptData<SalesRetReceiptPO> {
    public SalesRetReceiptData() throws RemoteException {
        super(SalesRetReceiptPOMapper.class, SalesRetReceiptPO.class);
    }
}
