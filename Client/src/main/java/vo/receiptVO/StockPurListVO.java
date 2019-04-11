package vo.receiptVO;

import util.ReceiptState;

import java.time.LocalDateTime;

public class StockPurListVO extends StockReceiptListVO<StockPurListVO> {
    private StockPurReceiptVO stockPurReceiptVO;

    public StockPurListVO(String id, LocalDateTime createTime, ReceiptState receiptState, String memberName, String stockName, double sum) {
        super(id, createTime, receiptState, memberName, stockName, sum);
    }
    public StockPurListVO(StockPurReceiptVO stockPurReceiptVO){
        super(stockPurReceiptVO.getId(), stockPurReceiptVO.getCreateTime(), stockPurReceiptVO.getReceiptState(), stockPurReceiptVO.getMemberName(), stockPurReceiptVO.getStockName(), stockPurReceiptVO.getSum());
        this.stockPurReceiptVO = stockPurReceiptVO;
    }

    @Override
    public StockPurReceiptVO toVO() {
        return this.stockPurReceiptVO;
    }
}
