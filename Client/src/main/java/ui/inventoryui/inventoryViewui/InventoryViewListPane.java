package ui.inventoryui.inventoryViewui;

import blService.inventoryblService.InventoryViewblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.inventorybl.InventoryViewbl;
import businesslogic.logbl.Logbl;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import ui.common.bigPane.ListPane;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptListPane;
import util.EventCategory;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class InventoryViewListPane extends ListPane<InventoryViewItemVO> {
    @FXML
    TextField totalSum;

    private InventoryViewblService inventoryViewblService;
    private InventoryViewVO inventoryViewVO;

    private LocalDate startTime;
    private LocalDate endTime;

    private Logbl logbl;

    Pagination pagination;

    public InventoryViewListPane() throws Exception {
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryviewui/inventoryviewlistpane.fxml";
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        inventoryViewblService = MyblServiceFactory.getService(InventoryViewblService.class);
    }

    @Override
    protected void initiateFields() {
        super.initiateFields();
    }

    @Override
    protected MyTreeTableBorderPane<InventoryViewItemVO> getInitialTreeTable() {
        return new InventoryViewTreeTable();
    }

    @Override
    protected ArrayList<InventoryViewItemVO> getNewListData() throws RemoteException {
        try {
            inventoryViewVO = inventoryViewblService.inventoryView(startTime,endTime);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        totalSum.setDisable(true);
        totalSum.setText(String.valueOf(inventoryViewVO.getTotalNum()));
        return new ArrayList<>(inventoryViewVO.getViewList());
    }

    public void refresh(boolean toSwitch) {
        super.refresh(toSwitch);
            try {
                if(logbl == null)
                    logbl = new Logbl();
                logbl.insertString(EventCategory.InventoryView,"进行库存查看");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
    }


    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
