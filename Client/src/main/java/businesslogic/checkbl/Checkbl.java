package businesslogic.checkbl;

import blService.checkblService.CheckInfo;
import blService.checkblService.CheckblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import util.ResultMessage;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Checkbl implements CheckblService {
    private CheckInfo<SalesSellReceiptVO> salesSellReceiptVOCheckInfo;
    private CheckInfo<SalesRetReceiptVO> salesRetReceiptVOCheckInfo;
    private CheckInfo<StockPurReceiptVO> stockPurReceiptVOCheckInfo;
    private CheckInfo<StockRetReceiptVO> stockRetReceiptVOCheckInfo;

    private CheckInfo<InventoryDamageReceiptVO> inventoryDamageReceiptVOCheckInfo;
    private CheckInfo<InventoryGiftReceiptVO> inventoryGiftReceiptVOCheckInfo;
    private CheckInfo<InventoryOverflowReceiptVO> inventoryOverflowReceiptVOCheckInfo;
    private CheckInfo<InventoryWarningReceiptVO> inventoryWarningReceiptVOCheckInfo;

    private CheckInfo<CashReceiptVO> cashReceiptVOCheckInfo;
    private CheckInfo<ChargeReceiptVO> chargeReceiptVOCheckInfo;
    private CheckInfo<PaymentReceiptVO> paymentReceiptVOCheckInfo;


    public Checkbl() throws RemoteException, NotBoundException, MalformedURLException {
        salesSellReceiptVOCheckInfo = MyServiceFactory.getSalesSellReceiptVOCheckInfo();
        salesRetReceiptVOCheckInfo = MyServiceFactory.getSalesRetReceiptVOCheckInfo();
        stockPurReceiptVOCheckInfo = MyServiceFactory.getStockPurReceiptVOCheckInfo();
        stockRetReceiptVOCheckInfo = MyServiceFactory.getStockRetReceiptVOCheckInfo();

        inventoryDamageReceiptVOCheckInfo = MyServiceFactory.getInventoryDamageReceiptVOCheckInfo();
        inventoryGiftReceiptVOCheckInfo = MyServiceFactory.getInventoryGiftReceiptVOCheckInfo();
        inventoryOverflowReceiptVOCheckInfo = MyServiceFactory.getInventoryOverflowReceiptVOCheckInfo();
        inventoryWarningReceiptVOCheckInfo = MyServiceFactory.getInventoryWarningReceiptVOCheckInfo();

        cashReceiptVOCheckInfo = MyServiceFactory.getCashReceiptVOCheckInfo();
        chargeReceiptVOCheckInfo = MyServiceFactory.getChargeReceiptVOCheckInfo();
        paymentReceiptVOCheckInfo = MyServiceFactory.getPaymentReceiptVOCheckInfo();
    }

    @Override
    public ArrayList<ReceiptVO> initCheck() throws RemoteException {
        ArrayList<ReceiptVO> resultList = new ArrayList<>();

        resultList.addAll(salesSellReceiptVOCheckInfo.selectPending());
        resultList.addAll(salesRetReceiptVOCheckInfo.selectPending());
        resultList.addAll(stockPurReceiptVOCheckInfo.selectPending());
        resultList.addAll(stockRetReceiptVOCheckInfo.selectPending());

        resultList.addAll(inventoryDamageReceiptVOCheckInfo.selectPending());
        resultList.addAll(inventoryGiftReceiptVOCheckInfo.selectPending());
        resultList.addAll(inventoryOverflowReceiptVOCheckInfo.selectPending());
        resultList.addAll(inventoryWarningReceiptVOCheckInfo.selectPending());

        resultList.addAll(cashReceiptVOCheckInfo.selectPending());
        resultList.addAll(chargeReceiptVOCheckInfo.selectPending());
        resultList.addAll(paymentReceiptVOCheckInfo.selectPending());

        return resultList;
    }

    @Override
    public ResultMessage approve(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().approve(receiptVO);
    }

    @Override
    public ResultMessage reject(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().reject(receiptVO);
    }

    @Override
    public ResultMessage update(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().update(receiptVO);
    }

}
