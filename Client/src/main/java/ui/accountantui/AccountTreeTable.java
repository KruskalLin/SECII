package ui.accountantui;

import blService.accountblService.AccountblService;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.common.BoardController;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.memberui.MemberDetailPane;
import ui.util.*;
import util.ReceiptState;
import vo.AccountListVO;
import vo.MemberListVO;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public class AccountTreeTable extends MyTreeTableBorderPane<AccountListVO> {

    public AccountTreeTable(Set<AccountListVO> chosenItems, StringProperty keywordProperty) {

        // TODO 这个是checkTable复制过来的

        JFXTreeTableColumn<AccountListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<AccountListVO, Integer> id = new JFXTreeTableColumn<>("编号");
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<AccountListVO, Integer> param) -> {
            if (id.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getID());
            } else {
                return id.getComputedValue(param);
            }
        });
        id.setPrefWidth(150);

        JFXTreeTableColumn<AccountListVO, String> name = new SearchableStringColumn<>("账户名称", 150, keywordProperty, p -> p.getName());

        JFXTreeTableColumn<AccountListVO, Double> balance = new JFXTreeTableColumn<>("余额");
        balance.setPrefWidth(150);
        balance.setCellValueFactory((TreeTableColumn.CellDataFeatures<AccountListVO, Double> param) -> {
            if (balance.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getBalance());
            } else {
                return balance.getComputedValue(param);
            }
        });

        myTreeTable.getColumns().addAll(choose, id, name, balance);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<AccountListVO> row) {
        (row.getTreeItem().getValue().getDetailPane()).refresh(false);
    }

}
