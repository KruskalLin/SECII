package vo.inventoryVO.inventoryReceiptVO;

import util.ReceiptState;
import vo.receiptVO.ReceiptListVO;

public class InventoryDamageListVO extends ReceiptListVO<InventoryDamageListVO> {
    private String id;
    private ReceiptState receiptState;
    private int operator;
    private boolean multiple = true; // 什么是multiple
    private InventoryDamageReceiptVO inventoryDamageReceiptVO;

    public InventoryDamageListVO(InventoryDamageReceiptVO inventoryDamageReceiptVO) {
        id = inventoryDamageReceiptVO.getId();
        receiptState = inventoryDamageReceiptVO.getReceiptState();
        operator = inventoryDamageReceiptVO.getOperatorId();
       // System.out.println(inventoryDamageReceiptVO.getOperatorId());
        this.inventoryDamageReceiptVO = inventoryDamageReceiptVO;
    }

    public InventoryDamageListVO(String id, ReceiptState receiptState, int operator) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
    }

    @Override
    public InventoryDamageReceiptVO  toVO() {
        return inventoryDamageReceiptVO;
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

    public InventoryDamageReceiptVO getInventoryDamageReceiptVO() {
        return inventoryDamageReceiptVO;
    }

    public void setInventoryDamageReceiptVO(InventoryDamageReceiptVO inventoryDamageReceiptVO) {
        this.inventoryDamageReceiptVO = inventoryDamageReceiptVO;
    }
}
