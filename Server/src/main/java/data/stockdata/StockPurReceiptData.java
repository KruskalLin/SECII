package data.stockdata;

import annotations.RMIRemote;
import data.generic.ReceiptData;
import mapper.StockPurReceiptPOMapper;
import po.receiptPO.StockPurReceiptPO;

import java.rmi.RemoteException;
@RMIRemote
public class StockPurReceiptData extends ReceiptData<StockPurReceiptPO> {
    public StockPurReceiptData() throws RemoteException{
        super(StockPurReceiptPOMapper.class, StockPurReceiptPO.class);
    }
}
