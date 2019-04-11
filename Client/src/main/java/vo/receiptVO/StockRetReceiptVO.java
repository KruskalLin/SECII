package vo.receiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.StockRetReceiptPO;
import ui.stockui.stockRetui.StockRetDetailPane;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StockRetReceiptVO extends StockReceiptVO {
    public StockRetReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String stockName, double sum, ArrayList<ListGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, stockName, sum, items, comment);
    }


    public StockRetReceiptVO(StockRetReceiptPO stockRetReceiptPO) throws RemoteException, NotBoundException, MalformedURLException {
        super(stockRetReceiptPO);
    }

    public StockRetReceiptVO() {
    }

    @Override
    public CheckInfo<StockRetReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getStockRetReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new StockRetDetailPane(this);
    }

    @Override
    protected String getCodeName() {
        return "JHTHD";
    }

    @Override
    public StockRetReceiptPO toPO() {
        StockRetReceiptPO result = toStockReceiptPO(StockRetReceiptPO.class);
        return result;
    }

    @Override
    public StockRetListVO toListVO() {
        return new StockRetListVO(this);
    }
}
