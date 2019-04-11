package vo.billReceiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.CashItemPO;
import po.receiptPO.CashBillReceiptPO;
import ui.accountantui.MyCashDetailPane;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CashReceiptVO extends ReceiptVO {

    private int accountID;
    private double total;
    private List<CashItemVO> cashList;


    public CashReceiptVO(){

    }

    public CashReceiptVO(CashBillReceiptPO cashBillReceiptPO) {
        super(cashBillReceiptPO);
        this.accountID = cashBillReceiptPO.getAccountId();
        this.total = cashBillReceiptPO.getTotal();
        List<CashItemVO> temp = new ArrayList<>();
        if (cashBillReceiptPO.getItemList() != null) {
            for (CashItemPO cashItemPO : cashBillReceiptPO.getItemList()) {
                CashItemVO vo = new CashItemVO(cashItemPO.getName(),cashItemPO.getSum(), cashItemPO.getComment());
                temp.add(vo);
            }
        }
        this.cashList = temp;
    }

    public CashReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int accountID, double total, List<CashItemVO> cashList) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.accountID = accountID;
        this.total = total;
        this.cashList = cashList;
    }

    @Override
    public String getCodeName() {
        return "XJFYD";
    }

    @Override
    public CashBillReceiptPO toPO(){
        CashBillReceiptPO result = toReceiptPO(CashBillReceiptPO.class);
        result.setAccountId(accountID);
        CashItemPO temp[] = new CashItemPO[cashList.size()];
        for(int i=0;i<cashList.size();i++){
            temp[i] = cashList.get(i).toPO();
        }
        result.setItemList(temp);
        result.setTotal(total);
        return result;
    }

    @Override
    public CheckInfo<CashReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getCashReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new MyCashDetailPane(this);
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CashItemVO> getCashList() {
        return cashList;
    }

    public void setCashList(List<CashItemVO> cashList) {
        this.cashList = cashList;
    }


    @Override
    public CashReceiptListVO toListVO() {
        return new CashReceiptListVO(this);
    }
}
