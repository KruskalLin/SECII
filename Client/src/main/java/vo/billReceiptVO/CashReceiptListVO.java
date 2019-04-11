package vo.billReceiptVO;

import util.ReceiptState;
import vo.abstractVO.SelectableVO;
import vo.receiptVO.ReceiptListVO;

public class CashReceiptListVO extends ReceiptListVO<CashReceiptListVO>{

    private String id;
    private ReceiptState receiptState;
    private int operator;
    private double sum;
    private boolean multiple = true;
    private CashReceiptVO cashReceiptVO;


    public CashReceiptListVO(CashReceiptVO cashReceiptVO) {
        id = cashReceiptVO.getId();
        receiptState = cashReceiptVO.getReceiptState();
        operator = cashReceiptVO.getOperatorId();
        sum =cashReceiptVO.getTotal();
        this.cashReceiptVO = cashReceiptVO;
    }

    public CashReceiptListVO(String id, ReceiptState receiptState, int operator, double sum) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
        this.sum = sum;
    }

    @Override
    public CashReceiptVO toVO(){
        return cashReceiptVO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }


}
