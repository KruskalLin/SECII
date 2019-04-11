package SalesReceiptTest;

import blService.salesblService.SalesSellblService;
import blService.stockblService.StockRetblService;
import businesslogic.salesbl.SalesSellbl;
import businesslogic.stockbl.StockRetbl;
import org.junit.Before;
import org.junit.Test;
import util.ReceiptState;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import static org.junit.Assert.assertEquals;

public class SalesSellblTest {

    SalesSellblService salesSellblService;

    @Before
    public void setUp() throws Exception{
        salesSellblService = new SalesSellbl();
    }
    @Test
    public void testInAndSee() throws  Exception{
        SalesSellReceiptVO stockRetReceiptVO = salesSellblService.getNew();
        stockRetReceiptVO.setReceiptState(ReceiptState.REJECTED);
        salesSellblService.update(stockRetReceiptVO);
        stockRetReceiptVO = salesSellblService.selectByMold(stockRetReceiptVO);
        assertEquals(ReceiptState.REJECTED,stockRetReceiptVO.getReceiptState());
    }
}
