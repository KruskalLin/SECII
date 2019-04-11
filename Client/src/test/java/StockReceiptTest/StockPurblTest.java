package StockReceiptTest;

import blService.stockblService.StockPurblService;
import blService.stockblService.StockRetblService;
import businesslogic.stockbl.StockPurbl;
import businesslogic.stockbl.StockRetbl;
import org.junit.Before;
import org.junit.Test;
import util.ReceiptState;
import vo.receiptVO.StockPurReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import static org.junit.Assert.assertEquals;

public class StockPurblTest {

    StockPurblService stockRetblService;

    @Before
    public void setUp() throws Exception{
        stockRetblService = new StockPurbl();
    }
    @Test
    public void testInAndSee() throws  Exception{
        StockPurReceiptVO stockRetReceiptVO = stockRetblService.getNew();
        stockRetReceiptVO.setReceiptState(ReceiptState.REJECTED);
        stockRetblService.update(stockRetReceiptVO);
        stockRetReceiptVO = stockRetblService.selectByMold(stockRetReceiptVO);
        assertEquals(ReceiptState.REJECTED,stockRetReceiptVO.getReceiptState());
    }
}
