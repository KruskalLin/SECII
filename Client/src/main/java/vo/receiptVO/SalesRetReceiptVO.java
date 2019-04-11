package vo.receiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.SalesRetReceiptPO;
import ui.salesui.salesRetui.SalesRetDetailPane;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesRetReceiptVO extends SalesReceiptVO {

    public SalesRetReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, items, comment, discountAmount, tokenAmount, originSum);
    }

    public SalesRetReceiptVO(){
    }

    public SalesRetReceiptVO(SalesRetReceiptPO salesRetReceiptPO) throws RemoteException, NotBoundException, MalformedURLException {
       super(salesRetReceiptPO);
    }


    @Override
    public CheckInfo<SalesRetReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getSalesRetReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new SalesRetDetailPane(this);
    }

    @Override
    public SalesRetListVO toListVO() {
        SalesRetListVO salesRetListVO = new SalesRetListVO(this);
        return salesRetListVO;
    }


    @Override
    protected String getCodeName() {
        return "XSTHD";
    }

    @Override
    public SalesRetReceiptPO toPO() {
        SalesRetReceiptPO salesRetReceiptPO = toSalesReceiptPO(SalesRetReceiptPO.class);
        return salesRetReceiptPO;
    }

}
