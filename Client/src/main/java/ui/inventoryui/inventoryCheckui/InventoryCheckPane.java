package ui.inventoryui.inventoryCheckui;

import blService.inventoryblService.InventoryCheckblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.inventorybl.InventoryCheckbl;
import businesslogic.logbl.Logbl;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import jxl.write.WriteException;
import ui.common.bigPane.ListPane;
import ui.common.mixer.ExcelExportableMixer;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.*;
import util.EventCategory;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InventoryCheckPane extends ListPane<InventoryCheckItemVO> implements ExcelExportableMixer {

    private InventoryCheckblService inventoryCheckblService;

    private InventoryCheckVO inventoryCheckVO;

    private Logbl logbl;

    Pagination pagination;

    public InventoryCheckPane() throws Exception {
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventorycheckpane.fxml";
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        inventoryCheckblService = MyblServiceFactory.getService(InventoryCheckblService.class);

    }

    @Override
    protected void initiateFields() {
        super.initiateFields();
    }

    @Override
    protected MyTreeTableBorderPane<InventoryCheckItemVO> getInitialTreeTable() {
        return new InventoryCheckTreeTable();
    }

    @Override
    protected ArrayList<InventoryCheckItemVO> getNewListData() throws RemoteException {
        try {
            inventoryCheckVO = inventoryCheckblService.inventoryCheck();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(inventoryCheckVO.getCheckList());
    }

    public void refresh(boolean toSwitch) {
        super.refresh(toSwitch);
        try {
            if(logbl == null)
                logbl = new Logbl();
            logbl.insertString(EventCategory.InventoryCheck,"进行库存盘点");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getExcelName() {
        return "库存盘点";
    }

    @Override
    public void writeSheet() throws WriteException {
        int i = 0;
        myAddCell(i++,0,"商品名");
        myAddCell(i++,0,"商品编号");
        myAddCell(i++,0,"库存数量");
        myAddCell(i++,0,"售价");
        myAddCell(i++,0,"出厂日期");
        myAddCell(i++,0,"批次");
        myAddCell(i++,0,"批号");

        List<InventoryCheckItemVO> list = new ArrayList<>(inventoryCheckVO.getCheckList());

        for (int j = 0; j < list.size(); j++) {
            myAddCell(0,j+1,list.get(j).getGoodName());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(1,j+1,list.get(j).getGoodId());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(2,j+1,list.get(j).getInventoryNum());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(3,j+1,list.get(j).getAvePrice());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(4,j+1,list.get(j).getStockOutDate());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(5,j+1,list.get(j).getBatch());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(6,j+1,list.get(j).getBatchNum());
        }



    }

    @FXML
    public void exportExcel(){
        exportExcelMixer();
        try {
            logbl.insertString(EventCategory.ExportExcel,"库存盘点导出Excel");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
