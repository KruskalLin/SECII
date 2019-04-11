package vo.receiptVO;

import blService.checkblService.CheckInfo;
import javafx.scene.Node;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import vo.abstractVO.ReceipishVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public abstract class ReceiptVO extends ReceipishVO<ReceiptVO> {
    private int operatorId; // 很可能会改成名字之类

    private ReceiptState receiptState;

    public ReceiptVO() {
    }

    public ReceiptVO(ReceiptPO receiptPO) {
        super(receiptPO);
        this.operatorId = receiptPO.getOperatorId(); // TODO 这里以后要改成operator Name吧？或者反正就是自己，不管？
        this.receiptState = receiptPO.getReceiptState();
    }

    public ReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        this.id = id;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.receiptState = receiptState;
    }

    protected <T extends ReceiptPO> T toReceiptPO(Class<T> receiptClass) {
        T result = toReceipishPO(receiptClass);
        result.setOperatorId(operatorId);
        result.setReceiptState(receiptState);
        return result;
    }

    public abstract <TV extends ReceiptVO> CheckInfo<TV> getService() throws RemoteException, NotBoundException, MalformedURLException;

    public abstract Node getDetailPane();

    public abstract <TL extends ReceiptListVO<TL>> TL toListVO();

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }
}
