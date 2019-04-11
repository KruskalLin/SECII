package vo.receiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.StockPurReceiptPO;
import ui.stockui.stockPurui.StockPurDetailPane;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StockPurReceiptVO extends StockReceiptVO {

    public StockPurReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String stockName, double sum, ArrayList<ListGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, stockName, sum, items, comment);
    }

    public StockPurReceiptVO() {
    }

    public StockPurReceiptVO(StockPurReceiptPO stockReceiptPO) throws RemoteException, NotBoundException, MalformedURLException {
        super(stockReceiptPO);
    }

    @Override
    public CheckInfo<StockPurReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getStockPurReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new StockPurDetailPane(this);
    }

    @Override
    public StockPurListVO toListVO() {
        return new StockPurListVO(this);
    }

    @Override
    protected String getCodeName() {
        return "JHD";
    }

    @Override
    public StockPurReceiptPO toPO() {
        StockPurReceiptPO result = toStockReceiptPO(StockPurReceiptPO.class);
        return result;
    }
}
