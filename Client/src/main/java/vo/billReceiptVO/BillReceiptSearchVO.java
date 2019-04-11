package vo.billReceiptVO;

import util.ReceiptState;

import java.util.HashSet;

public class BillReceiptSearchVO {

    HashSet<ReceiptState> receiptStates;

    public BillReceiptSearchVO() {
        receiptStates = new HashSet<>();
    }

    public BillReceiptSearchVO(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }

    public HashSet<ReceiptState> getReceiptStates() {
        return receiptStates;
    }

    public void setReceiptStates(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }
}
