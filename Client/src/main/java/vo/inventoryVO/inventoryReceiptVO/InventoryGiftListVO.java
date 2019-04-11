package vo.inventoryVO.inventoryReceiptVO;

import util.ReceiptState;
import vo.receiptVO.ReceiptListVO;

public class InventoryGiftListVO extends ReceiptListVO<InventoryGiftListVO> {
    private String id;
    private ReceiptState receiptState;
    private int operator;
    private boolean multiple = true; // 什么是multiple
    private InventoryGiftReceiptVO inventoryGiftReceiptVO;

    public InventoryGiftListVO(InventoryGiftReceiptVO inventoryGiftReceiptVO) {
        id = inventoryGiftReceiptVO.getId();
        receiptState = inventoryGiftReceiptVO.getReceiptState();
        operator = inventoryGiftReceiptVO.getOperatorId();
        this.inventoryGiftReceiptVO = inventoryGiftReceiptVO;
    }

    public InventoryGiftListVO(String id, ReceiptState receiptState, int operator) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
    }

    @Override
    public InventoryGiftReceiptVO  toVO() {
        return inventoryGiftReceiptVO;
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

    public InventoryGiftReceiptVO getInventoryGiftReceiptVO() {
        return inventoryGiftReceiptVO;
    }

    public void setInventoryGiftReceiptVO(InventoryGiftReceiptVO inventoryGiftReceiptVO) {
        this.inventoryGiftReceiptVO = inventoryGiftReceiptVO;
    }
}
