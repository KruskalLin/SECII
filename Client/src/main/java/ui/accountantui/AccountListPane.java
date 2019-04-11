package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.businessblservice.BusinessProgressblService;
import businesslogic.accountbl.Accountbl;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.common.bigPane.ListPane;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.businessProgressui.BusinessProgressTable;
import ui.util.*;
import util.ReceiptSearchCondition;
import vo.AccountListVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountListPane extends ListPane<AccountListVO> {

    private Set<AccountListVO> chosenItems;

    Set<AccountListVO> set;

    AccountblService accountblService;

    @FXML
    JFXRippler search;

    SimpleStringProperty match = new SimpleStringProperty("");

    public AccountListPane() throws Exception {
    }

    @FXML
    public void deleteList() {
        if (chosenItems.size() == 0) {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "", "请选择账户", "继续");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        } else {
            for (AccountListVO chosenItem : chosenItems) {
                if (chosenItem.getBalance() != 0) {
                    new MyTwoButtonDialog("你要删除的帐户中存在余额不为0的，您确认删除吗？", this::deleteTask).show();
                    return;
                }
            }
            deleteTask();
        }
    }

    private void deleteTask() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "", "请确定是否删除", "是", "否");
        doubleButtonDialog.setButtonOne(() -> {
            for (AccountListVO vo : chosenItems) {
                try {
                    accountblService.delete(vo.getID());
                    chosenItems.remove(vo);
                    refresh(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.show();
    }

    @FXML
    public void search() {
        if (keywordField.getText() != "" && keywordField.getText() != null) {
            match.setValue(keywordField.getText().toLowerCase());
            Set<AccountListVO> hashSet;
            hashSet = chosenItems.stream().filter(
                    s -> s.getName().contains(match.get())
            ).collect(Collectors.toSet());
            receiptListTreeTable.refresh(new ArrayList<>(hashSet));

        }
    }

    @FXML
    public void add() {
        AccountDetailPane accountDetailPane = new AccountDetailPane(true);
    }

    @Override
    protected ArrayList<AccountListVO> getNewListData() throws RemoteException {
        return new ArrayList<>(accountblService.getAll());
    }

    public void refresh(boolean toSwitch) {
        super.refresh(toSwitch);
    }

    @Override
    protected MyTreeTableBorderPane<AccountListVO> getInitialTreeTable() {
        return new AccountTreeTable(chosenItems, keywordField.textProperty());
    }

    @Override
    protected String getURL() {
        return "/accountantui/accountListPane.fxml";
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        accountblService = MyblServiceFactory.getService(AccountblService.class);
    }


    @Override
    protected void initiateFields() {
        super.initiateFields();
        chosenItems = new HashSet<>();
    }

}
