package ui.salesui;

import businesslogic.salesbl.SalesRetbl;
import businesslogic.salesbl.SalesSellbl;
import businesslogic.stockbl.StockPurbl;
import businesslogic.stockbl.StockRetbl;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.inventoryVO.InventoryViewItemVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockPurReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ViewInfo {
    private SalesRetbl salesRetbl;
    private SalesSellbl salesSellbl;
    private StockPurbl stockPurbl;
    private StockRetbl stockRetbl;

    public ViewInfo() throws Exception {
        salesRetbl = new SalesRetbl();
        salesSellbl = new SalesSellbl();
        stockPurbl = new StockPurbl();
        stockRetbl = new StockRetbl();
    }

    public ArrayList<InventoryViewItemVO> view(LocalDateTime floor, LocalDateTime ceil) throws RemoteException {
        ReceiptSearchCondition receiptSearchCondition = new ReceiptSearchCondition();
        receiptSearchCondition.setBegin(floor);
        receiptSearchCondition.setEnd(ceil);
        ArrayList<SalesRetReceiptVO> salesRetReceiptVOS = salesRetbl.search(receiptSearchCondition);
        ArrayList<SalesSellReceiptVO> salesSellReceiptVOS = salesSellbl.search(receiptSearchCondition);
        ArrayList<StockPurReceiptVO> stockPurReceiptVOS = stockPurbl.search(receiptSearchCondition);
        ArrayList<StockRetReceiptVO> stockRetReceiptVOS = stockRetbl.search(receiptSearchCondition);
        HashMap<String, InventoryViewItemVO> hashMap = new HashMap<>();

        for (SalesSellReceiptVO receiptVO : salesSellReceiptVOS) {
            for (ListGoodsItemVO listGoodsItemVO : receiptVO.getItems()) {
                if (hashMap.containsKey(listGoodsItemVO.getGoodsId())) {
                    InventoryViewItemVO inventoryViewItemVO = hashMap.get(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setSaleNum(inventoryViewItemVO.getSaleNum() + listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setSaleMoney(inventoryViewItemVO.getSaleMoney() + listGoodsItemVO.getSum());
                    inventoryViewItemVO.setStockOutNum(inventoryViewItemVO.getStockOutNum()+listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockOutMoney(inventoryViewItemVO.getStockOutMoney()+listGoodsItemVO.getSum());
                    hashMap.replace(listGoodsItemVO.getGoodsId(), inventoryViewItemVO);
                } else {
                    InventoryViewItemVO inventoryViewItemVO = new InventoryViewItemVO();
                    inventoryViewItemVO.setGoodId(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setGoodName(listGoodsItemVO.getGoodsName());
                    inventoryViewItemVO.setSaleMoney(listGoodsItemVO.getSum());
                    inventoryViewItemVO.setSaleNum(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockOutNum(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockOutMoney(listGoodsItemVO.getSum());
                    hashMap.put(listGoodsItemVO.getGoodsId(),inventoryViewItemVO);
                }
            }
        }
        for (SalesRetReceiptVO receiptVO : salesRetReceiptVOS) {
            for (ListGoodsItemVO listGoodsItemVO : receiptVO.getItems()) {
                if (hashMap.containsKey(listGoodsItemVO.getGoodsId())) {
                    InventoryViewItemVO inventoryViewItemVO = hashMap.get(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setStockInNum(inventoryViewItemVO.getStockInNum()+listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockInMoney(inventoryViewItemVO.getStockInMoney()+listGoodsItemVO.getSum());
                    hashMap.replace(listGoodsItemVO.getGoodsId(), inventoryViewItemVO);
                } else {
                    InventoryViewItemVO inventoryViewItemVO = new InventoryViewItemVO();
                    inventoryViewItemVO.setGoodId(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setGoodName(listGoodsItemVO.getGoodsName());
                    inventoryViewItemVO.setStockInNum(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockInMoney(listGoodsItemVO.getSum());
                    hashMap.put(listGoodsItemVO.getGoodsId(),inventoryViewItemVO);

                }
            }
        }
        for (StockPurReceiptVO receiptVO : stockPurReceiptVOS) {
            for (ListGoodsItemVO listGoodsItemVO : receiptVO.getItems()) {
                if (hashMap.containsKey(listGoodsItemVO.getGoodsId())) {
                    InventoryViewItemVO inventoryViewItemVO = hashMap.get(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setStockPurNum(inventoryViewItemVO.getStockPurNum()+listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockPurMoney(inventoryViewItemVO.getStockPurMoney()+listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockInNum(inventoryViewItemVO.getStockInNum()+listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockInMoney(inventoryViewItemVO.getStockInMoney()+listGoodsItemVO.getSum());
                    hashMap.replace(listGoodsItemVO.getGoodsId(), inventoryViewItemVO);
                } else {
                    InventoryViewItemVO inventoryViewItemVO = new InventoryViewItemVO();
                    inventoryViewItemVO.setGoodId(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setGoodName(listGoodsItemVO.getGoodsName());
                    inventoryViewItemVO.setStockPurNum(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockPurMoney(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockInNum(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockInMoney(listGoodsItemVO.getSum());
                    hashMap.put(listGoodsItemVO.getGoodsId(),inventoryViewItemVO);

                }
            }
        }
        for (StockRetReceiptVO receiptVO : stockRetReceiptVOS) {
            for (ListGoodsItemVO listGoodsItemVO : receiptVO.getItems()) {
                if (hashMap.containsKey(listGoodsItemVO.getGoodsId())) {
                    InventoryViewItemVO inventoryViewItemVO = hashMap.get(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setStockOutNum(inventoryViewItemVO.getStockOutNum()+listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockOutMoney(inventoryViewItemVO.getStockOutMoney()+listGoodsItemVO.getSum());
                    hashMap.replace(listGoodsItemVO.getGoodsId(), inventoryViewItemVO);
                } else {
                    InventoryViewItemVO inventoryViewItemVO = new InventoryViewItemVO();
                    inventoryViewItemVO.setGoodId(listGoodsItemVO.getGoodsId());
                    inventoryViewItemVO.setGoodName(listGoodsItemVO.getGoodsName());
                    inventoryViewItemVO.setStockOutNum(listGoodsItemVO.getGoodsNum());
                    inventoryViewItemVO.setStockOutMoney(listGoodsItemVO.getSum());
                    hashMap.put(listGoodsItemVO.getGoodsId(),inventoryViewItemVO);

                }
            }


        }
        return hashMap.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }



}

