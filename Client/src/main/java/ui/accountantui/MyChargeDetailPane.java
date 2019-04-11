package ui.accountantui;

import blService.billblservice.ChargeBillReceiptblService;
import blService.genericblService.ReceiptblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.common.dialog.MyOneButtonDialog;
import ui.memberui.ChooseList;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import vo.MemberVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static ui.util.ValidatorDecorator.*;

public class MyChargeDetailPane extends MyReceiptDetailPane<ChargeReceiptVO> {
    @FXML
    private TextField clientField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;
    @FXML
    private JFXButton member;

    @FXML
    private ChargeItemTreeTable chargeItemTreeTable;
    @FXML
    private TextField operator;
    @FXML
    private JFXRippler addTransferButton;
    @FXML
    private TextField createtime;
    @FXML
    private TextField lastmodifiedtime;


    public MyChargeDetailPane() {
    }

    public MyChargeDetailPane(ChargeReceiptVO receiptVO) {
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

        addTransferButton.visibleProperty().bind(modify.modifyProperty());
        member.disableProperty().bind(modify.modifyProperty().not());

        chargeItemTreeTable.sumProperty().addListener(t -> {
            sumField.setText(chargeItemTreeTable.getSum() + "");
        });
    }


    @Override
    protected String getURL() {
        return "/myAccountantui/myChargeDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<ChargeReceiptVO>> getServiceClass() {
        return ChargeBillReceiptblService.class;
    }


    @Override
    protected boolean validate() {
        if (super.validate()) {
            try {
                if (MyServiceFactory.getMemberInfo().getALL().stream()
                        .noneMatch(memberVO -> {
                            return String.valueOf(memberVO.getMemberId()).equals(clientField.getText());
                        })) {
                    new MyOneButtonDialog("销售商不存在").show();
                    return false;
                }
                if (chargeItemTreeTable.getList().isEmpty()) {
                    new MyOneButtonDialog("不可提交空单据").show();
                    return false;
                }
                for (TransferItemVO transferItemVO : chargeItemTreeTable.getList()) {
                    if (MyServiceFactory.getAccountblService().getAll().stream()
                            .noneMatch(accountListVO -> accountListVO.getID() == transferItemVO.getAccountID())) {
                        new MyOneButtonDialog("银行帐户" + transferItemVO.getAccountID() + "不存在").show();
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
        receiptVO.setClientID(Integer.parseInt(clientField.getText()));
        receiptVO.setTransferList(chargeItemTreeTable.getList());
    }

    @Override
    protected void setRedCredit(ChargeReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setClientID(receiptVO.getClientID());
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
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        sumField.setText(String.valueOf(receiptVO.getSum()));
        chargeItemTreeTable.setList(receiptVO.getTransferList());
        clientField.setText(String.valueOf(receiptVO.getClientID()));
        createtime.setText(receiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(receiptVO.getLastModifiedTime().toString());
    }


    @FXML
    private void addTransfer() {
        ArrayList<TransferItemVO> temp = chargeItemTreeTable.getList();
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
            chargeItemTreeTable.add(new TransferItemVO(-1, 0, ""));
    }

    @FXML
    private void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO, () -> {
            clientField.setText(String.valueOf(memberVO.getMemberId()));
            receiptVO.setClientID(memberVO.getMemberId());
        });
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }
}
