package ui.accountantui;

import blService.billblservice.CashBillReceiptblService;
import blService.genericblService.ReceiptblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.common.dialog.MyOneButtonDialog;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import vo.AccountListVO;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.CashReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyCashDetailPane extends MyReceiptDetailPane<CashReceiptVO> {
    @FXML
    private TextField accountField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    @FXML
    private CashItemTreeTable cashItemTreeTable;
    @FXML
    private TextField operator;
    @FXML
    private JFXRippler addCashButton;
    @FXML
    private TextField createtime;
    @FXML
    private TextField lastmodifiedtime;


    public MyCashDetailPane() {
    }

    public MyCashDetailPane(CashReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected void initiate() {
        super.initiate();

        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        accountField.disableProperty().bind(modify.modifyProperty().not());
        addCashButton.visibleProperty().bind(modify.modifyProperty());

        cashItemTreeTable.sumProperty().addListener(t->{sumField.setText(cashItemTreeTable.getSum()+"");});
    }


    @Override
    protected String getURL() {
        return "/myAccountantui/myCashDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<CashReceiptVO>> getServiceClass() {
        return CashBillReceiptblService.class;
    }


    @Override
    protected boolean validate() {
        if (super.validate()) {
            try {
                Set<AccountListVO> list = MyServiceFactory.getAccountblService().getAll();
                if (list.stream().noneMatch(accountListVO -> String.valueOf(accountListVO.getID()).equals(accountField.getText()))) {
                    new MyOneButtonDialog("银行帐户" + accountField.getText() + "不存在").show();
                    return false;
                }

                double sum = cashItemTreeTable.getList().stream().mapToDouble(CashItemVO::getPrice).sum();
                if (list.stream().filter(accountListVO -> String.valueOf(accountListVO.getID()).equals(accountField.getText()))
                        .anyMatch(accountListVO -> accountListVO.getBalance() < sum)) {
                    new MyOneButtonDialog("银行帐户" + accountField.getText() + "余额不足").show();
                    return false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                new MyOneButtonDialog("连接错误").show();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void updateReceiptVO() {
        super.updateReceiptVO();

        receiptVO.setTotal(Double.parseDouble(sumField.getText()));
        receiptVO.setAccountID(Integer.parseInt(accountField.getText()));
        receiptVO.setCashList(cashItemTreeTable.getList());
    }

    @Override
    protected void setRedCredit(CashReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setTotal(-receiptVO.getTotal());
        redCreditVO.setAccountID(receiptVO.getAccountID());

        List<CashItemVO> list = receiptVO.getCashList();
        List<CashItemVO> temp = new ArrayList<>();
        for(CashItemVO vo:list){
            temp.add(new CashItemVO(vo.getName(),-vo.getPrice(),vo.getComment()));
        }
        redCreditVO.setCashList(temp);

    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
//        operator.setText(UserInfomation.username);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        sumField.setText(String.valueOf(receiptVO.getTotal()));
        cashItemTreeTable.setList(receiptVO.getCashList());
        accountField.setText(String.valueOf(receiptVO.getAccountID()));
        createtime.setText(receiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(receiptVO.getLastModifiedTime().toString());
    }

    @FXML
    private void addTransfer() {
        ArrayList<CashItemVO> temp = cashItemTreeTable.getList();
        boolean flag = false;
        for(CashItemVO vo:temp){
            if(vo.getPrice()==0){
                flag  =true;
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","请先完成已添加的条目信息","继续");
                oneButtonDialog.setButtonOne(()->{});
                oneButtonDialog.show();
            }
            break;
        }
        if(!flag)
            cashItemTreeTable.add(new CashItemVO("TODO",0,"TODO"));
    }
}
