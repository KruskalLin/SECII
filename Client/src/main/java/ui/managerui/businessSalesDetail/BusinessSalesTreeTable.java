package ui.managerui.businessSalesDetail;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import vo.ListGoodsItemVO;

public class BusinessSalesTreeTable extends MyTreeTableBorderPane<ListGoodsItemVO>{
    public BusinessSalesTreeTable(StringProperty keywordProperty) {
        // TODO 加上时间信息
        JFXTreeTableColumn<ListGoodsItemVO, String> dateColumn = new SearchableStringColumn<>("时间", 100, keywordProperty, l -> l.getTime().toString());
        JFXTreeTableColumn<ListGoodsItemVO, String> nameColumn = new SearchableStringColumn<>("商品名", 100, keywordProperty, ListGoodsItemVO::getGoodsName);
        JFXTreeTableColumn<ListGoodsItemVO, String> typeColumn = new SearchableStringColumn<>("型号", 100, keywordProperty, ListGoodsItemVO::getType);
        JFXTreeTableColumn<ListGoodsItemVO, String> numColumn = new SearchableStringColumn<>("数量", 60, keywordProperty, l -> String.valueOf(l.getGoodsNum()));
        JFXTreeTableColumn<ListGoodsItemVO, String> priceColumn = new SearchableStringColumn<>("单价", 60, keywordProperty, l -> String.valueOf(l.getPrice()));
        JFXTreeTableColumn<ListGoodsItemVO, String> totalColumn = new SearchableStringColumn<>("总价", 60, keywordProperty, l -> String.valueOf(l.getSum()));

        myTreeTable.getColumns().addAll(dateColumn, nameColumn, typeColumn, numColumn, priceColumn, totalColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<ListGoodsItemVO> row) {
    }
}
