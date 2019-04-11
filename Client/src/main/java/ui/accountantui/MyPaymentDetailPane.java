package ui.accountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.genericblService.ReceiptblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.common.dialog.MyOneButtonDialog;
import ui.memberui.ChooseList;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import vo.AccountListVO;
import vo.MemberVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MyPaymentDetailPane extends MyReceiptDetailPane<PaymentReceiptVO> {
    @FXML
    private TextField clientField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;
    @FXML
    private JFXButton member;

    @FXML
    private PaymentItemTreeTable paymentItemTreeTable;
    @FXML
    private TextField operator;
    @FXML
    private JFXRippler addTransferButton;
    @FXML
    private TextField createtime;
    @FXML
    private TextField lastmodifiedtime;

    public MyPaymentDetailPane() {
    }

    public MyPaymentDetailPane(PaymentReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected void initiate() {
        super.initiate();

        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);
        clientField.setDisable(true);

        member.disableProperty().bind(modify.modifyProperty().not());
        addTransferButton.visibleProperty().bind(modify.modifyProperty());
        paymentItemTreeTable.sumProperty().addListener(t -> {
            sumField.setText(paymentItemTreeTable.getSum() + "");
        });
    }


    @Override
    protected String getURL() {
        return "/myAccountantui/myPaymentDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<PaymentReceiptVO>> getServiceClass() {
        return PaymentBillReceiptblService.class;
    }


    @Override
    protected boolean validate() {
        if (super.validate()) {
            try {
                if (MyServiceFactory.getMemberInfo().getALL().stream()
                        .noneMatch(memberVO -> {
                            return String.valueOf(memberVO.getMemberId()).equals(clientField.getText());
                        })) {
                    new MyOneButtonDialog("供应商不存在").show();
                    return false;
                }
                if (paymentItemTreeTable.getList().isEmpty()) {
                    new MyOneButtonDialog("不可提交空单据").show();
                    return false;
                }
                for (TransferItemVO transferItemVO : paymentItemTreeTable.getList()) {
                    Set<AccountListVO> list = MyServiceFactory.getAccountblService().getAll();
                    if (list.stream().noneMatch(accountListVO -> accountListVO.getID() == transferItemVO.getAccountID())) {
                        new MyOneButtonDialog("银行帐户" + transferItemVO.getAccountID() + "不存在").show();
                        return false;
                    }
                    if (list.stream() // TODO 这里的stream用的不好，而且查的是一条而不是多条的总和
                            .filter(accountListVO -> accountListVO.getID() == transferItemVO.getAccountID())
                            .anyMatch(accountListVO -> accountListVO.getBalance() < transferItemVO.getSum())) {
                        new MyOneButtonDialog("银行帐户" + transferItemVO.getAccountID() + "余额不足").show();
                        return false;
                    }
                    if (transferItemVO.getSum() <= 0) {
                        new MyOneButtonDialog("金额不可为0或负数").show();
                        return false;
                    }
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
        receiptVO.setSum(Double.parseDouble(sumField.getText()));
        receiptVO.setclientID(Integer.parseInt(clientField.getText()));
        receiptVO.setTransferList(paymentItemTreeTable.getList());

    }

    @Override
    protected void setRedCredit(PaymentReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setclientID(receiptVO.getclientID());
        redCreditVO.setSum(-receiptVO.getSum());
        List<TransferItemVO> list = receiptVO.getTransferList();
        List<TransferItemVO> temp = new ArrayList<>();
        for (TransferItemVO vo : list) {
            temp.add(new TransferItemVO(vo.getAccountID(), -vo.getSum(), vo.getComment()));
        }
        redCreditVO.setTransferList(temp);
    }


    @FXML
    @Override
    protected void reset() {
        super.reset();
//        operator.setText(UserInfomation.username);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        sumField.setText(String.valueOf(receiptVO.getSum()));
        paymentItemTreeTable.setList(receiptVO.getTransferList());
        clientField.setText(String.valueOf(receiptVO.getclientID()));
        createtime.setText(receiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(receiptVO.getLastModifiedTime().toString());
    }


    @FXML
    private void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO, () -> {
            clientField.setText(memberVO.getMemberId() + "");
        });
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }

    @FXML
    private void addTransfer() {
        ArrayList<TransferItemVO> temp = paymentItemTreeTable.getList();
        boolean flag = false;
        for (TransferItemVO vo : temp) {
            if (vo.getAccountID() == -1) {
                flag = true;
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "", "请先完成已添加的转账信息", "继续");
                oneButtonDialog.setButtonOne(() -> {
                });
                oneButtonDialog.show();
            }
            break;
        }
        if (!flag)
            paymentItemTreeTable.add(new TransferItemVO(-1, 0, ""));
    }


}
