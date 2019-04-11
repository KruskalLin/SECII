package util;

import po.receiptPO.*;

import java.util.HashMap;
import java.util.Map;

public enum BillType {
    InventoryGift,
    InventoryDamage,
    InventoryOverflow,
    InventoryWarning,

    SalesSell,
    SalesRet,
    StockPur,
    StockRet,

    BillPay,
    BillCharge,
    Cash,

    CreditNote;

//    InventoryGift(InventoryGiftReceiptPO.class),
//    InventoryDamage(InventoryDamageReceiptPO.class),
//    InventoryOverflow(InventoryOverflowReceiptPO.class),
//    InventoryWarning(InventoryWarningReceiptPO.class),
//
//    SalesSell(SalesSellReceiptPO.class),
//    SalesRet(SalesRetReceiptPO.class),
//    StockPur(StockPurReceiptPO.class),
//    StockRet(StockRetReceiptPO.class),
//
//    BillPay(PaymentBillReceiptPO.class),
//    BillCharge(ChargeBillReceiptPO.class),
//    Cash(CashBillReceiptPO.class),
//
//    CreditNote(null);

//    public final Class<? extends ReceiptPO> clazz;

    // TODO 所以这个map可以用一个遍历来解决了，（上面注释的那些…）
    public static Map<Class<? extends ReceiptPO>, BillType> classToType = new HashMap<>();
    static {
        classToType.put(InventoryGiftReceiptPO.class, BillType.InventoryGift);
        classToType.put(InventoryDamageReceiptPO.class, BillType.InventoryDamage);
        classToType.put(InventoryOverflowReceiptPO.class, BillType.InventoryOverflow);
        classToType.put(InventoryWarningReceiptPO.class, BillType.InventoryWarning);

        classToType.put(SalesSellReceiptPO.class, BillType.SalesSell);
        classToType.put(SalesRetReceiptPO.class, BillType.SalesRet);
        classToType.put(StockPurReceiptPO.class, BillType.StockPur);
        classToType.put(StockRetReceiptPO.class, BillType.StockRet);

        classToType.put(PaymentBillReceiptPO.class, BillType.BillPay);
        classToType.put(ChargeBillReceiptPO.class, BillType.BillCharge);
        classToType.put(CashBillReceiptPO.class, BillType.Cash);
    }

//    BillType(Class<? extends ReceiptPO> clazz){
//        this.clazz = clazz;
//    }
}
