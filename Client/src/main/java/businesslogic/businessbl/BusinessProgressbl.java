package businesslogic.businessbl;

import blService.businessblservice.BusinessProgressblService;
import blService.businessblservice.BusinessSearchInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import util.BillType;
import util.ReceiptSearchCondition;
import util.ReceiptState;
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
import java.util.stream.Collectors;

public class BusinessProgressbl implements BusinessProgressblService {
    private BusinessSearchInfo<SalesSellReceiptVO> salesSellReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<SalesRetReceiptVO> salesRetReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<StockPurReceiptVO> stockPurReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<StockRetReceiptVO> stockRetReceiptVOBusinessSearchInfo;

    private BusinessSearchInfo<InventoryDamageReceiptVO> inventoryDamageReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<InventoryGiftReceiptVO> inventoryGiftReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<InventoryOverflowReceiptVO> inventoryOverflowReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<InventoryWarningReceiptVO> inventoryWarningReceiptVOBusinessSearchInfo;

    private BusinessSearchInfo<CashReceiptVO> cashReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<ChargeReceiptVO> chargeReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<PaymentReceiptVO> paymentReceiptVOBusinessSearchInfo;


    public BusinessProgressbl() throws RemoteException, NotBoundException, MalformedURLException {
        salesSellReceiptVOBusinessSearchInfo = MyServiceFactory.getSalesSellSearchInfo();
        salesRetReceiptVOBusinessSearchInfo = MyServiceFactory.getSalesRetSearchInfo();
        stockPurReceiptVOBusinessSearchInfo = MyServiceFactory.getStockPurSearchInfo();
        stockRetReceiptVOBusinessSearchInfo = MyServiceFactory.getStockRetSearchInfo();

        inventoryDamageReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryDamageSearchInfo();
        inventoryGiftReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryGiftSearchInfo();
        inventoryOverflowReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryOverflowSearchInfo();
        inventoryWarningReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryWarningSearchInfo();

        cashReceiptVOBusinessSearchInfo = MyServiceFactory.getCashReceiptSearchInfo();
        chargeReceiptVOBusinessSearchInfo = MyServiceFactory.getChargeReceiptSearchInfo();
        paymentReceiptVOBusinessSearchInfo = MyServiceFactory.getPaymentReceiptSearchInfo();
    }

    @Override
    public ArrayList<ReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        // TODO 这个要根据是否contain，来决定是否需要调这个SearchInfo。以及，这里还缺stock和sales
        // TODO 目前只能初始化，不能搜索。
        ArrayList<ReceiptVO> resultList = new ArrayList<>();

        if (receiptSearchCondition.getBillTypes().contains(BillType.SalesSell)) {
            resultList.addAll(getApprovedReceipt(salesSellReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.SalesRet)) {
            resultList.addAll(getApprovedReceipt(salesRetReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.StockPur)) {
            resultList.addAll(getApprovedReceipt(stockPurReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.StockRet)) {
            resultList.addAll(getApprovedReceipt(stockRetReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }

        if (receiptSearchCondition.getBillTypes().contains(BillType.InventoryDamage)) {
            resultList.addAll(getApprovedReceipt(inventoryDamageReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.InventoryGift)) {
            resultList.addAll(getApprovedReceipt(inventoryGiftReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.InventoryOverflow)) {
            resultList.addAll(getApprovedReceipt(inventoryOverflowReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.InventoryWarning)) {
            resultList.addAll(getApprovedReceipt(inventoryWarningReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }

        if (receiptSearchCondition.getBillTypes().contains(BillType.Cash)) {
            resultList.addAll(getApprovedReceipt(cashReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.BillCharge)) {
            resultList.addAll(getApprovedReceipt(chargeReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }
        if (receiptSearchCondition.getBillTypes().contains(BillType.BillPay)) {
            resultList.addAll(getApprovedReceipt(paymentReceiptVOBusinessSearchInfo, receiptSearchCondition));
        }

        return resultList;
    }

    /**
     * private search, in order to filter only saved receipt.
     * */
    private <T extends ReceiptVO> ArrayList<T> getApprovedReceipt(BusinessSearchInfo<T> businessSearchInfo, ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        // TODO
        return businessSearchInfo.search(receiptSearchCondition).stream().filter(r -> r.getReceiptState() == ReceiptState.APPROVED).collect(Collectors.toCollection(ArrayList::new));
//        return businessSearchInfo.search(receiptSearchCondition);
    }
}
