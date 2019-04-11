package data.billreceiptdata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.ChargeBillReceiptPOMapper;
import po.receiptPO.ChargeBillReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class ChargeBillReceiptData extends ReceiptData<ChargeBillReceiptPO> {
    public ChargeBillReceiptData() throws RemoteException {
        super(ChargeBillReceiptPOMapper.class, ChargeBillReceiptPO.class);
    }
}
