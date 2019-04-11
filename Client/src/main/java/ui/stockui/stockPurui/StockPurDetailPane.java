package ui.stockui.stockPurui;

import blService.genericblService.ReceiptblService;
import blService.stockblService.StockPurblService;
import ui.stockui.StockReceiptPane;
import vo.ListGoodsItemVO;
import vo.receiptVO.StockPurReceiptVO;

import java.util.ArrayList;

public class StockPurDetailPane extends StockReceiptPane<StockPurReceiptVO> {

    public StockPurDetailPane() {
    }

    public StockPurDetailPane(StockPurReceiptVO stockPurReceiptVO) {
        super(stockPurReceiptVO);
    }

    @Override
    protected Class<? extends ReceiptblService<StockPurReceiptVO>> getServiceClass() {
        return StockPurblService.class;
    }

    @Override
    protected void setRedCredit(StockPurReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setSum(-redCreditVO.getSum());
        ArrayList<ListGoodsItemVO> list = redCreditVO.getItems();
        for(ListGoodsItemVO vo:list){
            vo.setSum(-vo.getSum());
        }
        redCreditVO.setItems(list);
    }
}
