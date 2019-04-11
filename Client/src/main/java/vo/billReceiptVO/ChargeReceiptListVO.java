package vo.billReceiptVO;

import util.ReceiptState;
import vo.abstractVO.SelectableVO;
import vo.receiptVO.ReceiptListVO;

public class ChargeReceiptListVO extends ReceiptListVO<ChargeReceiptListVO> {

    private String id;
    private ReceiptState receiptState;
    private int operator;
    private double sum;
    private boolean multiple = true;
    private ChargeReceiptVO chargeReceiptVO;


    public ChargeReceiptListVO(ChargeReceiptVO chargeReceiptVO) {
        id = chargeReceiptVO.getId();
        receiptState = chargeReceiptVO.getReceiptState();
        operator = chargeReceiptVO.getOperatorId();
        sum = chargeReceiptVO.getSum();
        this.chargeReceiptVO = chargeReceiptVO;
    }

    public ChargeReceiptListVO(String id, ReceiptState receiptState, int operator, double sum) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
        this.sum = sum;
    }

    @Override
    public ChargeReceiptVO toVO(){
        return chargeReceiptVO;
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
