package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.logblService.LogblService;
import businesslogic.accountbl.Accountbl;
import businesslogic.logbl.Logbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import ui.common.BoardController;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.*;
import util.EventCategory;
import vo.AccountListVO;

import java.rmi.RemoteException;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;
import static ui.util.ValidatorDecorator.isDouble;

public class AccountDetailPane extends RefreshablePane {

    AccountListVO vo;

    private AccountblService accountblService;

    private AccountListVO accountListVO;

    private Logbl logbl;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField id;

    @FXML
    JFXTextField balance;

    @FXML
    protected ModifyButton modify;
    @FXML
    protected MaterialDesignIconView pen;
    @FXML
    protected JFXButton save;

    @FXML
    protected JFXButton reject;

    @FXML
    protected JFXButton delete;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);
    SimpleBooleanProperty updateState = new SimpleBooleanProperty();

    boolean isAdd = false;

    public AccountDetailPane(AccountListVO accountListVO) {
        this(false);
        this.accountListVO = accountListVO;
        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });

        name.disableProperty().bind(modifyState.not());
        balance.disableProperty().bind(modifyState.not());
    }


    public AccountDetailPane(boolean isAdd) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/accountdetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        save.setText("Add");
        updateState.set(true);
        modify.setVisible(false);
        delete.setVisible(false);

        try {
            this.accountblService = new Accountbl();
            logbl = new Logbl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.isAdd = isAdd;

        id.setText("auto");
        id.setDisable(true);
        delete.setVisible(false);
        DoubleValid(balance);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
        }
    }


    public void delete() {
        if (vo.getBalance() != 0) {
            new MyTwoButtonDialog("帐户余额不为0， 您确认要删除吗？", this::deleteTask).show();
        } else {
            deleteTask();
        }

    }

    private void deleteTask() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "", "请确定是否删除", "是", "否");
        doubleButtonDialog.setButtonOne(() -> {
            try {
                accountblService.delete(Integer.parseInt(id.getText()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            setBack();
        });
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.show();
        try{
            logbl.insertString(EventCategory.DeleteAccount,"账户编号为"+id.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        BoardController.getBoardController().Loading();
        try {
            if (!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> BoardController.getBoardController().Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
                    if ((vo = accountListVO) != null) {
                        System.out.println("11111");
                        return true;
                    } else
                        return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            id.setText(String.valueOf(vo.getID()));
                            name.setText(vo.getName());
                            balance.setText(String.valueOf(vo.getBalance()));
                            BoardController.getBoardController().switchTo(this);
                        }, buttonDialog, p);

                new Thread(task).start();
            } else {
                switchPane(toSwitch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchPane(boolean toSwtich) {
//        if (toSwtich == true) {
        BoardController.getBoardController().switchTo(this);
//        } else {
//            boardController.setAll(this);
//        }
    }

    @FXML
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            if (!isAdd && name.getText().equals(accountListVO.getName()) && balance.getText().equals(String.valueOf(accountListVO.getBalance()))) {
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "", "您未做修改", "继续");
                oneButtonDialog.setButtonOne(() -> {
                });
                oneButtonDialog.show();
            } else {

                DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "", "请确定是否保存", "是", "否");
                doubleButtonDialog.setButtonTwo(() -> {
                });
                doubleButtonDialog.setButtonOne(() -> {
                    try {
                        if (isAdd) {
                            accountblService.add(new AccountListVO(
                                    0,
                                    name.getText(),
                                    Double.parseDouble(balance.getText())
                            ));
                            try{
                                logbl.insertString(EventCategory.CreateAccount,"账户编号为"+id.getText());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        } else {
                            accountblService.update(new AccountListVO(
                                    Integer.parseInt(id.getText()),
                                    name.getText(),
                                    Double.parseDouble(balance.getText())
                            ));
                            try{
                                logbl.insertString(EventCategory.UpdateAccount,"账户编号为"+id.getText());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        BoardController.getBoardController().switchTo(this);
                        OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "提示", "操作成功", "继续");
                        oneButtonDialog.setButtonOne(() -> {
                        });
                        oneButtonDialog.show();
                        setBack();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
                doubleButtonDialog.show();
            }

        } else {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "Error！", "数据错误！", "继续");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        }
    }


    public boolean valid() {
        if (!name.getText().equals("") && !id.getText().equals("") && !balance.getText().equals("") && isDouble(balance.getText()))
            return true;
        return false;
    }

    public void setBack() {

        BoardController.getBoardController().goBack();
    }

}

