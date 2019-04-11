package data.billreceiptdata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.CashBillReceiptPOMapper;
import po.receiptPO.CashBillReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class CashBillReceiptData extends ReceiptData<CashBillReceiptPO> {
    public CashBillReceiptData() throws RemoteException {
        super(CashBillReceiptPOMapper.class, CashBillReceiptPO.class);
    }
}
