package ui.salesui;

import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.common.dialog.MyOneButtonDialog;
import ui.memberui.ChooseList;
import ui.common.ItemTreeTable;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.util.*;
import vo.MemberVO;
import vo.receiptVO.*;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;

public abstract class SalesReceiptPane<T extends SalesReceiptVO> extends MyReceiptDetailPane<T> {

    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;
    @FXML
    JFXButton member;

    @FXML
    JFXTextField original;

    @FXML
    JFXRippler add;

    @FXML
    JFXTextField discount;

    @FXML
    JFXTextField token;

    @FXML
    TextField clerk;

    @FXML
    TextArea comment;

    SimpleDoubleProperty textSum;

    @FXML
    protected ItemTreeTable itemTreeTable;


    public SalesReceiptPane() {
    }

    public SalesReceiptPane(T receiptVO) {
        super(receiptVO);
    }

    @Override
    protected String getURL() {
        return "/salesui/salesreceipt.fxml";
    }

    @Override
    public void initiate() {
        super.initiate();
        provider.setDisable(true);
        operator.setDisable(true);
        original.setDisable(true);
        sum.setDisable(true);

        stock.disableProperty().bind(this.modify.modifyProperty().not());
        member.disableProperty().bind(this.modify.modifyProperty().not());
//        user.disableProperty().bind(modifyState.not());
        clerk.disableProperty().bind(this.modify.modifyProperty().not());
        comment.disableProperty().bind(this.modify.modifyProperty().not());
        token.disableProperty().bind(this.modify.modifyProperty().not());
        discount.disableProperty().bind(this.modify.modifyProperty().not());
        add.visibleProperty().bind(this.modify.modifyProperty());


        RequireValid(operator);
        RequireValid(provider);
        DoubleValid(token);
        DoubleValid(discount);


        original.setText("0");
        sum.setText("0");
        token.setText("0");
        discount.setText("0");


        textSum = new SimpleDoubleProperty(0);
        textSum.addListener((b, o, n) -> {
            sum.setText("" + n);
        });


        itemTreeTable.sumProperty().addListener(t -> {
            original.setText(itemTreeTable.getSum() + "");
            if (DoubleValidation(discount) && DoubleValidation(token))
                textSum.set(Double.parseDouble(original.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(token.getText()));
        });

        discount.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        if (DoubleValidation(discount) && DoubleValidation(token))
                            textSum.set((Double.parseDouble(original.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(token.getText())) < 0 ? 0 : (Double.parseDouble(original.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(token.getText())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        token.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        if (DoubleValidation(discount) && DoubleValidation(token))
                            textSum.set((Double.parseDouble(original.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(token.getText())) < 0 ? 0 : (Double.parseDouble(original.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(token.getText())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void reset() {
        super.reset();
        provider.setText(receiptVO.getMemberName());
        operator.setText(UserInfomation.username);
        stock.setText(receiptVO.getStockName());
        comment.setText(receiptVO.getComment());
        clerk.setText(receiptVO.getClerkName());
        sum.setText((receiptVO.getOriginSum() - receiptVO.getTokenAmount()) + "");
        token.setText(receiptVO.getTokenAmount() + "");
        discount.setText(receiptVO.getDiscountAmount() + "");
        original.setText(receiptVO.getOriginSum() + "");
        itemTreeTable.setList(receiptVO.getItems());
    }


    @Override
    public void updateReceiptVO() {
        super.updateReceiptVO();
        receiptVO.setLastModifiedTime(LocalDateTime.now());
        receiptVO.setMemberName(provider.getText());
        receiptVO.setClerkName(clerk.getText());
        receiptVO.setStockName(stock.getText());
        receiptVO.setItems(itemTreeTable.getList());
        receiptVO.setComment(comment.getText());
        receiptVO.setDiscountAmount(Double.parseDouble(discount.getText()));
        receiptVO.setTokenAmount(Double.parseDouble(token.getText()));
        receiptVO.setOriginSum(Double.parseDouble(original.getText()));
    }

    @Override
    protected boolean validate() {
        if (super.validate()) {
            try {
                if (MyServiceFactory.getMemberInfo().getALL().stream()
                        .noneMatch(memberVO -> {
                            return memberVO.getMemberId() == receiptVO.getMemberId();
                        })) {
                    new MyOneButtonDialog("销售商不存在").show();
                    return false;
                }
                if (!DoubleValidation(discount) || !DoubleValidation(token)) {
                    return false;
                }

//            if (!itemTreeTable.refreshAndCheck()) {
//                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "����", "�����������", "����");
//                oneButtonDialog.show();
//                oneButtonDialog.setButtonOne(() -> {
//                });
//                return false;
//            }

                if (itemTreeTable.getList().isEmpty()) {
                    new MyOneButtonDialog("不可提交空单据").show();
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

    @FXML
    public void addTransfer() {
        GoodsList goodsList = new GoodsList(itemTreeTable);
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(goodsList);
        dialog.setDialogContainer(PaneFactory.getMainPane());
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }

    @FXML
    private void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO, () -> {
            provider.setText(memberVO.getName());
            receiptVO.setMemberId(memberVO.getMemberId());
            receiptVO.setMemberVO(memberVO);
        });
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }

    private boolean DoubleValidation(JFXTextField jfxTextField) {
        if (jfxTextField.getText() == null || jfxTextField.getText().equals("")) {
            jfxTextField.validate();
            return false;
        } else if (Double.parseDouble(jfxTextField.getText()) < 0) {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "����", "Ҫ����ȷ����", "����");
            oneButtonDialog.show();
            oneButtonDialog.setButtonOne(() -> {
            });
            return false;
        }
        return true;
    }


}
