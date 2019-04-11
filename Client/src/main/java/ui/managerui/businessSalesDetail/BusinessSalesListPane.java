package ui.managerui.businessSalesDetail;

import blService.businessblservice.SalesDetailblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import jxl.write.WriteException;
import org.controlsfx.control.PopOver;
import ui.common.bigPane.FilterableListPane;
import ui.common.mixer.ExcelExportableMixer;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class BusinessSalesListPane extends FilterableListPane<ListGoodsItemVO> implements ExcelExportableMixer {
    private SalesDetailblService salesDetailblService;
    private ReceiptSearchCondition receiptSearchCondition;

    public BusinessSalesListPane() {
    }

    @Override
    protected String getURL() {
        return "/managerui/businessSalesListPane.fxml";
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        salesDetailblService = MyblServiceFactory.getService(SalesDetailblService.class);
    }

    @Override
    protected MyTreeTableBorderPane<ListGoodsItemVO> getInitialTreeTable() {
        return new BusinessSalesTreeTable(keywordField.textProperty());
    }

    @Override
    protected void initiateFields() {
        super.initiateFields();
        receiptSearchCondition = new ReceiptSearchCondition();
    }

    @Override
    protected ArrayList<ListGoodsItemVO> getNewListData() throws RemoteException {
        return salesDetailblService.searchSalesDetail(receiptSearchCondition);
    }

    @Override
    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
        return new BusinessSalesDetailFilterPane(this, receiptSearchCondition);
    }

    /**
     * implement ExcelExportableMixer
     */

    @Override
    public String getExcelName() {
        return "销售明细表";
    }

    @Override
    public void writeSheet() throws WriteException {
        myAddCell(0, 0, "时间");
        myAddCell(1, 0, "商品名");
        myAddCell(2, 0, "型号");
        myAddCell(3, 0, "数量");
        myAddCell(4, 0, "单价");
        myAddCell(5, 0, "总价");

        for (int i = 0; i < tempList.size(); i++) {
            ListGoodsItemVO lgi = tempList.get(i);
            myAddCell(0, i + 1, lgi.getTime().toString());
            myAddCell(1, i + 1, lgi.getGoodsName());
            myAddCell(2, i + 1, lgi.getType());
            myAddCell(3, i + 1, lgi.getGoodsNum());
            myAddCell(4, i + 1, lgi.getPrice());
            myAddCell(5, i + 1, lgi.getSum());
        }
    }

    /**
     * FXML
     */

    @FXML
    public void exportExcel() {
        exportExcelMixer();
    }
}
