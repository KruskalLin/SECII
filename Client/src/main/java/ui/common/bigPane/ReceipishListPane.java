package ui.common.bigPane;

import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.bigPane.FilterableListPane;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import vo.abstractVO.ReceipishVO;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class ReceipishListPane<TR extends ReceipishVO<TR>> extends FilterableListPane<TR> {
    protected Set<TR> chosenItems;

//    @Override
//    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
//        return null;
//    }



    /**
     * To be overriden
     * */
    @Override
    protected void initiateFields() {
        super.initiateFields();
        chosenItems = new HashSet<>();
    }


    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {

    }

    @Override
    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
        return null;
    }

    @Override
    protected MyTreeTableBorderPane<TR> getInitialTreeTable() {
        return null;
    }

    @Override
    protected ArrayList<TR> getNewListData() throws RemoteException {
        return null;
    }

    @Override
    protected String getURL() {
        return null;
    }
}
