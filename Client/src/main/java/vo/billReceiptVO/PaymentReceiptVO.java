package vo.billReceiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.TransferItemPO;
import po.receiptPO.PaymentBillReceiptPO;
import ui.accountantui.MyPaymentDetailPane;
import util.ReceiptState;

import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentReceiptVO extends ReceiptVO {


    private int clientID;
    private List<TransferItemVO> transferList;
    private double sum;


    public PaymentReceiptVO() {

    }

    public PaymentReceiptVO(PaymentBillReceiptPO paymentBillReceiptPO) {
        super(paymentBillReceiptPO);
        this.clientID = paymentBillReceiptPO.getClientId();
        this.sum = paymentBillReceiptPO.getSum();
        List<TransferItemVO> temp = new ArrayList<>();
        if (paymentBillReceiptPO.getTransferList() != null) {
            for (TransferItemPO transferItemPO : paymentBillReceiptPO.getTransferList()) {
                TransferItemVO vo = new TransferItemVO(transferItemPO.getAccountID(), transferItemPO.getSum(), transferItemPO.getComment());
                temp.add(vo);
            }
        }
        transferList = temp;
    }

    public PaymentReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.clientID = clientID;
        this.transferList = transferList;
        this.sum = sum;
    }

    @Override
    public String getCodeName() {
        return "FKD";
    }

    @Override
    public PaymentBillReceiptPO toPO() {
        PaymentBillReceiptPO result = toReceiptPO(PaymentBillReceiptPO.class);
        result.setClientId(clientID);
        result.setSum(sum);
        TransferItemPO temp[] = new TransferItemPO[transferList.size()];
        for (int i = 0; i < transferList.size(); i++) {
            temp[i] = transferList.get(i).toPO();
        }
        result.setTransferList(temp);
        return result;
    }

    @Override
    public CheckInfo<PaymentReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getPaymentReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new MyPaymentDetailPane(this);
    }

    @Override
    public PaymentReceiptListVO toListVO() {
        return new PaymentReceiptListVO(this);
    }

    public int getclientID() {
        return clientID;
    }

    public void setclientID(int clientID) {
        this.clientID = clientID;
    }

    public List<TransferItemVO> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<TransferItemVO> transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }


}


