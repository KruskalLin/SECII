package StockReceiptTest;

import blService.stockblService.StockRetblService;
import businesslogic.stockbl.StockRetbl;
import org.junit.Before;
import org.junit.Test;
import util.ReceiptState;
import vo.receiptVO.StockRetReceiptVO;

import static org.junit.Assert.assertEquals;

public class StockRetblTest {

    StockRetblService stockRetblService;

    @Before
    public void setUp() throws Exception{
        stockRetblService = new StockRetbl();
    }
    @Test
    public void testInAndSee() throws  Exception{
        StockRetReceiptVO stockRetReceiptVO = stockRetblService.getNew();
        stockRetReceiptVO.setReceiptState(ReceiptState.REJECTED);
        stockRetblService.update(stockRetReceiptVO);
        stockRetReceiptVO = stockRetblService.selectByMold(stockRetReceiptVO);
        assertEquals(ReceiptState.REJECTED,stockRetReceiptVO.getReceiptState());
    }
}
