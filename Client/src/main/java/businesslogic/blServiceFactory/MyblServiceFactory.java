package businesslogic.blServiceFactory;

import blService.billblservice.CashBillReceiptblService;
import blService.billblservice.ChargeBillReceiptblService;
import blService.billblservice.PaymentBillReceiptblService;
import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import blService.inventoryblService.InventoryWarningReceiptblService;
import blService.salesblService.SalesRetblService;
import blService.salesblService.SalesSellblService;
import blService.stockblService.StockPurblService;
import blService.stockblService.StockRetblService;
import po.receiptPO.StockPurReceiptPO;
import util.BillType;

import java.util.HashMap;
import java.util.Map;

public class MyblServiceFactory {
    private static Map<Class<?>, Object> serviceMap = new HashMap<>();

    public static Map<BillType, Class<?>> typeToServiceClass = new HashMap<>();
    // TODO 我感觉不应该放在这里，但是呃……
    static {
        typeToServiceClass.put(BillType.InventoryGift, InventoryGiftReceiptblService.class);
        typeToServiceClass.put(BillType.InventoryDamage, InventoryDamageReceiptblService.class);
        typeToServiceClass.put(BillType.InventoryOverflow, InventoryOverflowReceiptblService.class);
        typeToServiceClass.put(BillType.InventoryWarning, InventoryWarningReceiptblService.class);

        typeToServiceClass.put(BillType.Cash, CashBillReceiptblService.class);
        typeToServiceClass.put(BillType.BillCharge, ChargeBillReceiptblService.class);
        typeToServiceClass.put(BillType.BillPay, PaymentBillReceiptblService.class);

        typeToServiceClass.put(BillType.SalesSell, SalesSellblService.class);
        typeToServiceClass.put(BillType.SalesRet, SalesRetblService.class);
        typeToServiceClass.put(BillType.StockPur, StockPurblService.class);
        typeToServiceClass.put(BillType.StockRet, StockRetblService.class);
    }


    public synchronized static <TF> TF getService(Class<?> serviceName) {
        if (serviceMap.containsKey(serviceName)) {
            return (TF) serviceMap.get(serviceName);
        }

        String[] nameSplit = serviceName.getName().split("\\.");
        String searchName = "businesslogic" + "."
                + nameSplit[1].substring(0, nameSplit[1].length() - "Service".length()) + "."
                + nameSplit[2].substring(0, nameSplit[2].length() - "Service".length());
        try {
            Class<?> implClass = Class.forName(searchName);
            Object result = implClass.newInstance();
            serviceMap.put(serviceName, result);
            return (TF) result;
        } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    synchronized static void clearAllSavedService() {
        serviceMap = new HashMap<>();
    }

}

