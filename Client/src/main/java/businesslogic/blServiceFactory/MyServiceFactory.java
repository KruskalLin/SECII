package businesslogic.blServiceFactory;

import blService.accountblService.AccountblService;
import blService.businessblservice.BusinessConditionInfo;
import blService.businessblservice.BusinessConditionblService;
import blService.businessblservice.BusinessSearchInfo;
import blService.checkblService.CheckInfo;
import blService.goodsClassificationblService.MyGoodsClassificationblService;
import blService.goodsblService.GoodsblService;
import blService.goodsblService.goodsSearchInfo;
import blService.memberblService.MemberInfo;
import blService.messageblService.MessageblService;
import blService.messageblService.ReceiptMessageblService;
import blService.promotionblService.PromotionInfo;
import blService.userblService.UserManagerblService;
import businesslogic.accountbl.Accountbl;
import businesslogic.billbl.CashBillReceiptbl;
import businesslogic.billbl.ChargeBillReceiptbl;
import businesslogic.billbl.PaymentBillReceiptbl;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsSearch.goodsSearch;
import businesslogic.inventorybl.InventoryDamageReceiptbl;
import businesslogic.inventorybl.InventoryGiftReceiptbl;
import businesslogic.inventorybl.InventoryOverflowReceiptbl;
import businesslogic.inventorybl.InventoryWarningReceiptbl;
import businesslogic.memberbl.MemberInfo_Impl;
import businesslogic.messagebl.ReceiptMessagebl;
import businesslogic.promotionbl.PromotionListbl;
import businesslogic.salesbl.SalesRetbl;
import businesslogic.salesbl.SalesSellbl;
import businesslogic.stockbl.StockPurbl;
import businesslogic.stockbl.StockRetbl;
import businesslogic.userbl.Userbl;
import javafx.beans.property.IntegerProperty;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockPurReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyServiceFactory {
    private static IntegerProperty messageNumber;

    private static SalesSellbl salesSellbl;
    private static SalesRetbl salesRetbl;
    private static StockPurbl stockPurbl;
    private static StockRetbl stockRetbl;

    private static InventoryDamageReceiptbl inventoryDamageReceiptbl;
    private static InventoryGiftReceiptbl inventoryGiftReceiptbl;
    private static InventoryOverflowReceiptbl inventoryOverflowReceiptbl;
    private static InventoryWarningReceiptbl inventoryWarningReceiptbl;

    private static CashBillReceiptbl cashReceiptbl;
    private static ChargeBillReceiptbl chargeReceiptbl;
    private static PaymentBillReceiptbl paymentReceiptbl;


    private static BusinessConditionbl businessConditionbl;

    private static goodsSearchInfo goodsSearch;

    private static PromotionListbl promotionListbl;


    private static UserManagerblService userbl;

    private static ReceiptMessageblService messagebl;

    private static Goodsbl goodsbl;

    private static Accountbl accountbl;

    private static MemberInfo_Impl memberInfo;

    /**
     * CheckInfo
     * */

    public static CheckInfo<SalesSellReceiptVO> getSalesSellReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (salesSellbl == null) {
            return salesSellbl = new SalesSellbl();
        }
        return salesSellbl;
    }

    public static CheckInfo<SalesRetReceiptVO> getSalesRetReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (salesRetbl == null) {
            return salesRetbl = new SalesRetbl();
        }
        return salesRetbl;
    }

    public static CheckInfo<StockPurReceiptVO> getStockPurReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (stockPurbl == null) {
            return stockPurbl = new StockPurbl();
        }
        return stockPurbl;
    }

    public static CheckInfo<StockRetReceiptVO> getStockRetReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (stockRetbl == null) {
            return stockRetbl = new StockRetbl();
        }
        return stockRetbl;
    }



    public static CheckInfo<InventoryDamageReceiptVO> getInventoryDamageReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryDamageReceiptbl == null) {
            return inventoryDamageReceiptbl = new InventoryDamageReceiptbl();
        }
        return inventoryDamageReceiptbl;
    }

    public static CheckInfo<InventoryGiftReceiptVO> getInventoryGiftReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryGiftReceiptbl == null) {
            return inventoryGiftReceiptbl = new InventoryGiftReceiptbl();
        }
        return inventoryGiftReceiptbl;
    }

    public static CheckInfo<InventoryOverflowReceiptVO> getInventoryOverflowReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryOverflowReceiptbl == null) {
            return inventoryOverflowReceiptbl = new InventoryOverflowReceiptbl();
        }
        return inventoryOverflowReceiptbl;
    }

    public static CheckInfo<InventoryWarningReceiptVO> getInventoryWarningReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryWarningReceiptbl == null) {
            return inventoryWarningReceiptbl = new InventoryWarningReceiptbl();
        }
        return inventoryWarningReceiptbl;
    }



    public static CheckInfo<CashReceiptVO> getCashReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (cashReceiptbl == null) {
            return cashReceiptbl = new CashBillReceiptbl();
        }
        return cashReceiptbl;
    }

    public static CheckInfo<ChargeReceiptVO> getChargeReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (chargeReceiptbl == null) {
            return chargeReceiptbl = new ChargeBillReceiptbl();
        }
        return chargeReceiptbl;
    }

    public static CheckInfo<PaymentReceiptVO> getPaymentReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (paymentReceiptbl == null) {
            return paymentReceiptbl = new PaymentBillReceiptbl();
        }
        return paymentReceiptbl;
    }

    /**
     * BusinessSearchInfo
     * */
    public static BusinessSearchInfo<SalesSellReceiptVO> getSalesSellSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (salesSellbl == null) {
            return salesSellbl = new SalesSellbl();
        }
        return salesSellbl;
    }

    public static BusinessSearchInfo<SalesRetReceiptVO> getSalesRetSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (salesRetbl == null) {
            return salesRetbl = new SalesRetbl();
        }
        return salesRetbl;
    }

    public static BusinessSearchInfo<StockPurReceiptVO> getStockPurSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (stockPurbl == null) {
            return stockPurbl = new StockPurbl();
        }
        return stockPurbl;
    }

    public static BusinessSearchInfo<StockRetReceiptVO> getStockRetSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (stockRetbl == null) {
            return stockRetbl = new StockRetbl();
        }
        return stockRetbl;
    }



    public static BusinessSearchInfo<InventoryDamageReceiptVO> getInventoryDamageSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryDamageReceiptbl == null) {
            return inventoryDamageReceiptbl = new InventoryDamageReceiptbl();
        }
        return inventoryDamageReceiptbl;
    }

    public static BusinessSearchInfo<InventoryGiftReceiptVO> getInventoryGiftSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryGiftReceiptbl == null) {
            return inventoryGiftReceiptbl = new InventoryGiftReceiptbl();
        }
        return inventoryGiftReceiptbl;
    }

    public static BusinessSearchInfo<InventoryOverflowReceiptVO> getInventoryOverflowSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryOverflowReceiptbl == null) {
            return inventoryOverflowReceiptbl = new InventoryOverflowReceiptbl();
        }
        return inventoryOverflowReceiptbl;
    }

    public static BusinessSearchInfo<InventoryWarningReceiptVO> getInventoryWarningSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryWarningReceiptbl == null) {
            return inventoryWarningReceiptbl = new InventoryWarningReceiptbl();
        }
        return inventoryWarningReceiptbl;
    }



    public static BusinessSearchInfo<CashReceiptVO> getCashReceiptSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (cashReceiptbl == null) {
            return cashReceiptbl = new CashBillReceiptbl();
        }
        return cashReceiptbl;
    }

    public static BusinessSearchInfo<ChargeReceiptVO> getChargeReceiptSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (chargeReceiptbl == null) {
            return chargeReceiptbl = new ChargeBillReceiptbl();
        }
        return chargeReceiptbl;
    }

    public static BusinessSearchInfo<PaymentReceiptVO> getPaymentReceiptSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (paymentReceiptbl == null) {
            return paymentReceiptbl = new PaymentBillReceiptbl();
        }
        return paymentReceiptbl;
    }

    /**
     * BusinessCondition
     * */

    public static BusinessConditionInfo getBusinessConditionInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (businessConditionbl == null) {
            return businessConditionbl = new BusinessConditionbl();
        }
        return businessConditionbl;
    }

    /**
     * Goods
     * */

    public static goodsSearchInfo getGoodsSearchInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (goodsSearch == null) {
            return goodsSearch = new goodsSearch();
        }
        return goodsSearch;
    }

    /**
     * User
     * */

    public static UserManagerblService getUserManagerblService() throws RemoteException {
        if (userbl == null) {
            try {
                return userbl = new Userbl();
            } catch (MalformedURLException | NotBoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return userbl;
    }

    /**
     * Promotion
     * */

    public static PromotionInfo getPromotionInfo() throws RemoteException{
        if (promotionListbl == null) {
            try {
                return promotionListbl = new PromotionListbl();
            } catch (NotBoundException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return promotionListbl;
    }



    /**
     * Goods
     * */

    public static GoodsblService getGoodsblService() throws RemoteException {
        if (goodsbl == null) {
            try {
                return goodsbl = new Goodsbl();
            } catch (NotBoundException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return goodsbl;
    }

    /**
     * Account
     * */

    public static AccountblService getAccountblService() throws RemoteException {
        if (accountbl == null) {
            try {
                return accountbl = new Accountbl();
            } catch (NotBoundException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return accountbl;
    }

    /**
     * Memberinfo
     * */

    public static MemberInfo getMemberInfo() throws RemoteException {
        if (memberInfo == null) {
            try {
                return memberInfo = new MemberInfo_Impl();
            } catch (MalformedURLException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        return memberInfo;
    }



    /**
     * Message 这个比较特殊
     * */

    public static ReceiptMessageblService getReceiptMessageblService() throws RemoteException {
        if (messageNumber == null) {
            return null; // 不可能的…
        }

        if (messagebl == null) {
            return messagebl = new ReceiptMessagebl(messageNumber);
        }
        return messagebl;
    }

    public static void setMessageNumber(IntegerProperty messageNumber) {
        MyServiceFactory.messageNumber = messageNumber;
    }



    /**
     * clear all
     * */

    static void clearAllSavedService() {
        salesSellbl = null;
        salesRetbl = null;
        stockPurbl = null;
        stockRetbl = null;

        inventoryDamageReceiptbl = null;
        inventoryGiftReceiptbl = null;
        inventoryOverflowReceiptbl = null;
        inventoryWarningReceiptbl = null;

        cashReceiptbl = null;
        chargeReceiptbl = null;
        paymentReceiptbl = null;


        businessConditionbl = null;

        goodsSearch = null;

        promotionListbl = null;

        userbl = null;
    }
}
