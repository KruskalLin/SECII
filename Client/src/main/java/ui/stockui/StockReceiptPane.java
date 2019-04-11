package ui.stockui;

import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.common.dialog.MyOneButtonDialog;
import ui.memberui.ChooseList;
import ui.common.ItemTreeTable;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.salesui.GoodsList;
import ui.util.*;
import vo.ListGoodsItemVO;
import vo.MemberVO;
import vo.receiptVO.StockReceiptVO;

import java.rmi.RemoteException;
import java.time.*;

import static ui.util.ValidatorDecorator.RequireValid;


public abstract class StockReceiptPane<T extends StockReceiptVO> extends MyReceiptDetailPane<T> {
    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;

    @FXML
    JFXRippler add;

    @FXML
    JFXButton member;

    @FXML
    TextArea comment;

    @FXML
    protected ItemTreeTable itemTreeTable;


    public StockReceiptPane() {
    }

    public StockReceiptPane(T receiptVO) {
        super(receiptVO);
    }

    @Override
    public void initiate() {
        super.initiate();
        provider.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        stock.disableProperty().bind(this.modify.modifyProperty().not());
        member.disableProperty().bind(this.modify.modifyProperty().not());
        add.visibleProperty().bind(this.modify.modifyProperty());

        RequireValid(operator);
        RequireValid(provider);


        comment.disableProperty().bind(this.modify.modifyProperty().not());
        sum.setText("0");
        itemTreeTable.sumProperty().addListener(t -> {
            sum.setText(itemTreeTable.getSum() + "");
        });

    }


    @Override
    protected String getURL() {
        return "/stockui/stockreceipt.fxml";
    }

    @Override
    protected void updateReceiptVO() {
        super.updateReceiptVO();
//        receiptVO.setOperatorId(UserInfomation.userid); // by 连。不能在这里设，如果是总经理打开这个界面之后就乱了。
        receiptVO.setLastModifiedTime(LocalDateTime.now());
        receiptVO.setMemberName(provider.getText());
        receiptVO.setStockName(stock.getText());
        receiptVO.setSum(Double.parseDouble(sum.getText()));
        receiptVO.setItems(itemTreeTable.getList());
//        System.out.println("this is the killer"); // by 连。所以的system.out。如果只是为了测试。请在测试之后删除或注释掉。或都在sout的语句中提供足够的信息让人能够定位这条语句在哪里。
        System.out.println(itemTreeTable.getList());

        receiptVO.setComment(comment.getText());
    }

    @Override
    protected void reset() {
        super.reset();
        provider.setText(receiptVO.getMemberName());
        operator.setText(UserInfomation.username);
        stock.setText(receiptVO.getStockName());
        comment.setText(receiptVO.getComment());
        sum.setText(receiptVO.getSum() + "");
        itemTreeTable.setList(receiptVO.getItems());
    }

    @Override
    public boolean validate() {
        if (super.validate()) {
            try {
                if (MyServiceFactory.getMemberInfo().getALL().stream()
                        .noneMatch(memberVO -> {
                            return memberVO.getMemberId() == receiptVO.getMemberId();
                        })) {
                    new MyOneButtonDialog("供应商不存在").show();
                    return false;
                }
                if (sum.getText() == null || sum.getText().equals("")) {
                    sum.validate();
                    return false;
//            } else if (!itemTreeTable.refreshAndCheck()) {
////                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "����", "�����������", "����");
////                oneButtonDialog.show();
////                oneButtonDialog.setButtonOne(() -> {
////                });
//                new MyOneButtonDialog("库存数量小于商品数量").show();
//                return false;
//            }
                }
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
    public void addTransfer() throws Exception {
        GoodsList goodsList = new GoodsList(itemTreeTable);
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(goodsList);
        dialog.setDialogContainer(PaneFactory.getMainPane());
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }

    @FXML
    public void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO, () -> {
            provider.setText(memberVO.getName());
            receiptVO.setMemberId(memberVO.getMemberId());
        });
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }


}
