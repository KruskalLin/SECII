package po.receiptPO;

import po.TransferItemPO;
import po.receiptPO.ReceiptPO;
import util.BillCategory;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PaymentBillReceiptPO extends ReceiptPO {

    private int clientId;
    private TransferItemPO[] transferList;
    private double sum;

    public PaymentBillReceiptPO(){

    }

    public PaymentBillReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientId, TransferItemPO[] transferList, double sum) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.clientId = clientId;
        this.transferList = transferList;
        this.sum = sum;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public TransferItemPO[] getTransferList() {
        return transferList;
    }

    public void setTransferList(TransferItemPO[] transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
