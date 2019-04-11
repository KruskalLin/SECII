package vo.inventoryVO.inventoryReceiptVO;

import util.ReceiptState;
import vo.receiptVO.ReceiptListVO;

public class InventoryOverflowListVO extends ReceiptListVO<InventoryOverflowListVO> {
    private String id;
    private ReceiptState receiptState;
    private int operator;
    private boolean multiple = true; // 什么是multiple
    private InventoryOverflowReceiptVO InventoryOverflowReceiptVO;

    public InventoryOverflowListVO(InventoryOverflowReceiptVO InventoryOverflowReceiptVO) {
        id = InventoryOverflowReceiptVO.getId();
        receiptState = InventoryOverflowReceiptVO.getReceiptState();
        operator = InventoryOverflowReceiptVO.getOperatorId();
        this.InventoryOverflowReceiptVO = InventoryOverflowReceiptVO;
    }

    public InventoryOverflowListVO(String id, ReceiptState receiptState, int operator) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
    }

    @Override
    public InventoryOverflowReceiptVO  toVO() {
        return InventoryOverflowReceiptVO;
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

    public InventoryOverflowReceiptVO getInventoryOverflowReceiptVO() {
        return InventoryOverflowReceiptVO;
    }

    public void setInventoryOverflowReceiptVO(InventoryOverflowReceiptVO InventoryOverflowReceiptVO) {
        this.InventoryOverflowReceiptVO = InventoryOverflowReceiptVO;
    }
}
