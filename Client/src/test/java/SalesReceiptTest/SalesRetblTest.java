package SalesReceiptTest;

import blService.salesblService.SalesRetblService;
import blService.stockblService.StockRetblService;
import businesslogic.salesbl.SalesRetbl;
import businesslogic.stockbl.StockRetbl;
import org.junit.Before;
import org.junit.Test;
import po.receiptPO.SalesRetReceiptPO;
import util.ReceiptState;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import static org.junit.Assert.assertEquals;

public class SalesRetblTest {

    SalesRetblService stockRetblService;

    @Before
    public void setUp() throws Exception{
        stockRetblService = new SalesRetbl();
    }
    @Test
    public void testInAndSee() throws  Exception{
        SalesRetReceiptVO stockRetReceiptVO = stockRetblService.getNew();
        stockRetReceiptVO.setReceiptState(ReceiptState.REJECTED);
        stockRetblService.update(stockRetReceiptVO);
        stockRetReceiptVO = stockRetblService.selectByMold(stockRetReceiptVO);
        assertEquals(ReceiptState.REJECTED,stockRetReceiptVO.getReceiptState());
    }
}
