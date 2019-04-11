package ui.managerui.businessConditionui;

import blService.businessblservice.BusinessConditionblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRippler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import jxl.write.WriteException;
import po.BusinessConditionPO;
import ui.common.bigPane.GatePane;
import ui.common.mixer.ExcelExportableMixer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BusinessConditionPane extends GatePane implements ExcelExportableMixer {
    @FXML
    private JFXRippler search;
    @FXML
    private JFXDatePicker beginTimePicker, endTimePicker;
    @FXML
    private PieChart incomePieChart, costPieChart;
    @FXML
    private Label salesIncomeLabel, overflowIncomeLabel, purPriceAdjustIncomeLabel, priceDiffIncomeLabel, tokenIncomeLabel;
    @FXML
    private Label purCostLabel, damageCostLabel, giftCostLabel;
    @FXML
    private Label incomeLabel, discountLabel, costLabel, profitLabel;

    private BusinessConditionblService businessConditionblService;

    private BusinessConditionPO businessConditionPO;

    public BusinessConditionPane() {
        beginTimePicker.setValue(LocalDate.now().minusDays(30));
        endTimePicker.setValue(LocalDate.now().plusDays(30));
    }

    /**
     * implement GatePane abstract Methods
     */

    @Override
    protected String getURL() {
        return "/managerui/BusinessConditionPane.fxml";
    }

    @Override
    protected void refreshAfterMath() {
        setDataPresentation();
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        businessConditionblService = MyblServiceFactory.getService(BusinessConditionblService.class);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        businessConditionPO = businessConditionblService.search(LocalDateTime.of(beginTimePicker.getValue(), LocalTime.MIN), LocalDateTime.of(endTimePicker.getValue(), LocalTime.MIN));
    }

    /**
     * implement ExcelExportableMixer methods
     * */

    @Override
    public String getExcelName() {
        return "经营情况表";
    }

    @Override
    public void writeSheet() throws WriteException {
        // add something into the Excel sheet
        int i = 0;
        myAddCell(0, i++, "收入类");
        myAddCell(0, i++, "销售收入");
        myAddCell(0, i++, "商口报溢收入");
        myAddCell(0, i++, "成本调价收入");
        myAddCell(0, i++, "进货退货差价");
        myAddCell(0, i++, "代金券与实际收款差额");


        i = 0;
        myAddCell(1, i++, "金额（元）");
        myAddCell(1, i++, businessConditionPO.getSalesIncome());
        myAddCell(1, i++, businessConditionPO.getOverFlowIncome());
        myAddCell(1, i++, businessConditionPO.getPurPriceAdjustIncome());
        myAddCell(1, i++, businessConditionPO.getPriceDiffIncome());
        myAddCell(1, i++, businessConditionPO.getTokenIncome());

        i = 0;
        myAddCell(2, i++, "支出类");
        myAddCell(2, i++, "销售成本");
        myAddCell(2, i++, "商品报损支出");
        myAddCell(2, i++, "商吕赠出支出");

        i = 0;
        myAddCell(3, i++, "金额（元）");
        myAddCell(3, i++, businessConditionPO.getPurCost());
        myAddCell(3, i++, businessConditionPO.getDamageCost());
        myAddCell(3, i++, businessConditionPO.getGiftCost());

        i = 0;
        myAddCell(4, i++, "总结");
        myAddCell(4, i++, "总折让");

        i = 0;
        myAddCell(5, i++, "金额（元）");
        myAddCell(5, i++, businessConditionPO.getDiscount());
    }

    /**
     * FXML methods
     */

    @FXML
    private void search() {
        refresh(false);
    }

    @FXML
    private void exportExcel() { // 目前只能这样唉
        exportExcelMixer();
    }


    /**
     * private methods
     */

    private void setDataPresentation() {
        ObservableList<PieChart.Data> incomePieChartData = FXCollections.observableArrayList();
        incomePieChartData.addAll(new PieChart.Data("销售收入", businessConditionPO.getSalesIncome()),
                new PieChart.Data("商品报溢收入", businessConditionPO.getOverFlowIncome()),
                new PieChart.Data("成本调价收入", businessConditionPO.getPurPriceAdjustIncome()),
                new PieChart.Data("进货退货差价", businessConditionPO.getPriceDiffIncome()),
                new PieChart.Data("代金券与实际收款差额", businessConditionPO.getTokenIncome()));

        ObservableList<PieChart.Data> costPieChartData = FXCollections.observableArrayList();
        costPieChartData.addAll(new PieChart.Data("销售成本", businessConditionPO.getPurCost()),
                new PieChart.Data("商品报损支出", businessConditionPO.getDamageCost()),
                new PieChart.Data("商品赠出支出", businessConditionPO.getGiftCost()));

        incomePieChart.getData().setAll(incomePieChartData);
        costPieChart.getData().setAll(costPieChartData);

        salesIncomeLabel.setText("销售收入：" + businessConditionPO.getSalesIncome());
        overflowIncomeLabel.setText("商品报溢收入：" + businessConditionPO.getOverFlowIncome());
        purPriceAdjustIncomeLabel.setText("成本调价收入：" + businessConditionPO.getPurPriceAdjustIncome());
        priceDiffIncomeLabel.setText("进货退货差价收入：" + businessConditionPO.getPriceDiffIncome());
        tokenIncomeLabel.setText("代金券与实际收款差额：" + businessConditionPO.getTokenIncome());

        purCostLabel.setText("销售成本：" + businessConditionPO.getPurCost());
        damageCostLabel.setText("商品报损支出：" + businessConditionPO.getDamageCost());
        giftCostLabel.setText("商品赠出支出：" + businessConditionPO.getGiftCost());

        incomeLabel.setText("总收入：" + businessConditionPO.getTotalIncome());
        discountLabel.setText("折让：" + businessConditionPO.getDiscount());
        costLabel.setText("总支出：" + businessConditionPO.getTotalCost());
        profitLabel.setText("总利润：" + businessConditionPO.getTotalProfit());
    }
}
