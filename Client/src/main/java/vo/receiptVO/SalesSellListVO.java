package vo.receiptVO;

import util.ReceiptState;

import java.time.LocalDateTime;

public class SalesSellListVO extends SalesReceiptListVO<SalesSellListVO> {
    private SalesSellReceiptVO salesSellReceiptVO;

    public SalesSellListVO(SalesSellReceiptVO salesSellReceiptVO) {
        super(salesSellReceiptVO.getId(), salesSellReceiptVO.getCreateTime(), salesSellReceiptVO.getReceiptState(), salesSellReceiptVO.getMemberName(), salesSellReceiptVO.getStockName(), salesSellReceiptVO.getOriginSum());
        this.salesSellReceiptVO = salesSellReceiptVO;
    }

    public SalesSellListVO(String id, LocalDateTime createTime, ReceiptState receiptState, String memberName, String stockName, double sum) {
        super(id, createTime, receiptState, memberName, stockName, sum);
    }

    @Override
    public SalesSellReceiptVO toVO() {
        return salesSellReceiptVO;
    }
}
