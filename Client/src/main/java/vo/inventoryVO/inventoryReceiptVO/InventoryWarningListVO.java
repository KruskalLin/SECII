package vo.inventoryVO.inventoryReceiptVO;

import util.ReceiptState;
import vo.receiptVO.ReceiptListVO;

public class InventoryWarningListVO extends ReceiptListVO<InventoryWarningListVO> {
    private String id;
    private ReceiptState receiptState;
    private int operator;
    private boolean multiple = true; // 什么是multiple
    private InventoryWarningReceiptVO inventoryWarningReceiptVO;

    public InventoryWarningListVO(InventoryWarningReceiptVO inventoryWarningReceiptVO) {
        id = inventoryWarningReceiptVO.getId();
        receiptState = inventoryWarningReceiptVO.getReceiptState();
        operator = inventoryWarningReceiptVO.getOperatorId();
        this.inventoryWarningReceiptVO = inventoryWarningReceiptVO;
    }

    public InventoryWarningListVO(String id, ReceiptState receiptState, int operator) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
    }

    @Override
    public InventoryWarningReceiptVO  toVO() {
        return inventoryWarningReceiptVO;
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

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public InventoryWarningReceiptVO getInventoryWarningReceiptVO() {
        return inventoryWarningReceiptVO;
    }

    public void setInventoryWarningReceiptVO(InventoryWarningReceiptVO inventoryWarningReceiptVO) {
        this.inventoryWarningReceiptVO = inventoryWarningReceiptVO;
    }
}
