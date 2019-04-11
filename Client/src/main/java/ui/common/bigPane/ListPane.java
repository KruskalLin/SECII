package ui.common.bigPane;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import ui.common.bigPane.GatePane;
import ui.common.treeTableRelated.MyTreeTableBorderPane;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public abstract class ListPane<TR extends RecursiveTreeObject<TR>> extends GatePane {
    @FXML
    protected JFXTextField keywordField;

    protected MyTreeTableBorderPane<TR> receiptListTreeTable; // 不应该叫这个名字的，但是为了向下兼容…


    protected ArrayList<TR> tempList; // 给updateDataFromBl用的，这个其实已经不能叫tempList了

    public ListPane() {
        receiptListTreeTable = getInitialTreeTable();
        receiptListTreeTable.setLayoutX(20);
        receiptListTreeTable.setLayoutY(80);
        this.getChildren().add(receiptListTreeTable);
    }

    /**
     * abstract methods
     */
    protected abstract void initiateService() throws RemoteException, NotBoundException, MalformedURLException;

    protected abstract MyTreeTableBorderPane<TR> getInitialTreeTable();

    protected abstract ArrayList<TR> getNewListData() throws RemoteException;

    /**
     * implement methods
     */

    @Override
    protected void refreshAfterMath() {
        receiptListTreeTable.refresh(tempList);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        ArrayList<TR> newData = null;
        if ((newData = getNewListData()) == null) {
            throw new RemoteException("return null from bl");
        }
        tempList.clear();
        tempList.addAll(newData);
    }

    /**
     * override methods
     * */
    @Override
    protected void initiateFields() {
        super.initiateFields();
        tempList = new ArrayList<>();
    }
}
