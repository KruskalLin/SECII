package ui.managerui.businessProgressui;

import blService.businessblservice.BusinessProgressblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import jxl.write.WriteException;
import org.controlsfx.control.PopOver;
import ui.common.bigPane.FilterableListPane;
import ui.common.mixer.ExcelExportableMixer;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import util.ReceiptSearchCondition;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BusinessProgressPane extends FilterableListPane<ReceiptVO> implements ExcelExportableMixer {
    private Set<ReceiptVO> chosenItems;// chosenItems这个都是自己的?
    private BusinessProgressblService businessProgressblService;

    private ReceiptSearchCondition searchCondition;

    public BusinessProgressPane() {
    }

    /**
     * implement FilterableListPane
     */

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        businessProgressblService = MyblServiceFactory.getService(BusinessProgressblService.class);
    }

    @Override
    protected MyTreeTableBorderPane<ReceiptVO> getInitialTreeTable() {
        return new BusinessProgressTable(chosenItems, keywordField.textProperty(), new ArrayList<>());
    }

    @Override
    protected ArrayList<ReceiptVO> getNewListData() throws RemoteException {
        return businessProgressblService.search(searchCondition);
    }

    @Override
    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
        return new BusinessProgressFilterPane(filterPopOver, this, searchCondition);
    }

    @Override
    protected String getURL() {
        return "/managerui/businessProgressPane.fxml";
    }

    @Override
    protected void initiateFields() {
        super.initiateFields();
        searchCondition = new ReceiptSearchCondition();
        chosenItems = new HashSet<>();
    }

    /**
     * implement ExcelExportableMixer
     */

    @Override
    public String getExcelName() {
        return "经营历程表";
    }

    @Override
    public void writeSheet() throws WriteException {
        myAddCell(0, 0, "单据编号");
        myAddCell(1, 0, "通过时间");
        myAddCell(2, 0, "操作员");
        myAddCell(3, 0, "信息");

        // TODO 对于tempList遍历。最后一栏信息就让自己定义吧。

        for (int i = 0; i < tempList.size(); i++) {
            ReceiptVO pvo = tempList.get(i);
            myAddCell(0, i + 1, pvo.getId());
            myAddCell(1, i + 1, pvo.getLastModifiedTime().toString());
            myAddCell(2, i + 1, pvo.getOperatorId());
        }
    }

    // TODO
    @FXML
    private void exportExcel() { // 目前只能这样唉
        exportExcelMixer();
    }
}