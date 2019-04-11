package vo.billReceiptVO;

import util.ReceiptState;
import vo.receiptVO.ReceiptListVO;

public class PaymentReceiptListVO extends ReceiptListVO<PaymentReceiptListVO> {

    private String id;
    private ReceiptState receiptState;
    private int operator;
    private double sum;
    private boolean multiple = true; // 什么是multiple
    private PaymentReceiptVO paymentReceiptVO;

    public PaymentReceiptListVO(PaymentReceiptVO paymentReceiptVO) {
        id = paymentReceiptVO.getId();
        receiptState = paymentReceiptVO.getReceiptState();
        operator = paymentReceiptVO.getOperatorId();
        sum = paymentReceiptVO.getSum();
        this.paymentReceiptVO = paymentReceiptVO;
    }

    public PaymentReceiptListVO(String id, ReceiptState receiptState, int operator, double sum) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
        this.sum = sum;
    }

    @Override
    public PaymentReceiptVO toVO() {
        return paymentReceiptVO;
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
