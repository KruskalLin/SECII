package data.salesdata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.SalesSellReceiptPOMapper;
import po.receiptPO.SalesSellReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class SalesSellReceiptData extends ReceiptData<SalesSellReceiptPO>{
    public SalesSellReceiptData() throws RemoteException {
        super(SalesSellReceiptPOMapper.class, SalesSellReceiptPO.class);
    }
}
