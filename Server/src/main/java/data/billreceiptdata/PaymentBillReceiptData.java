package data.billreceiptdata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.PaymentBillReceiptPOMapper;
import po.receiptPO.PaymentBillReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class PaymentBillReceiptData extends ReceiptData<PaymentBillReceiptPO> {
    public PaymentBillReceiptData() throws RemoteException {
        super(PaymentBillReceiptPOMapper.class, PaymentBillReceiptPO.class);
    }


}
