package ui.managerui.promotionui.promotionDetailPane;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.common.treeTableRelated.StateCell;
import util.PromotionState;
import vo.promotionVO.PromotionVO;

import java.util.Set;

public class PromotionTreeTableBorderPane extends MyTreeTableBorderPane<PromotionVO>{
    public PromotionTreeTableBorderPane(Set<PromotionVO> chosenItems, StringProperty keywordProperty) {
        JFXTreeTableColumn<PromotionVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<PromotionVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, PromotionVO::getId);
        JFXTreeTableColumn<PromotionVO, String> beginTimeColumn = new SearchableStringColumn<>("开始时间", 100, keywordProperty, p -> p.getBeginTime().toLocalDate().toString());
        JFXTreeTableColumn<PromotionVO, String> endTimeColumn = new SearchableStringColumn<>("结束时间", 100, keywordProperty, p -> p.getEndTime().toLocalDate().toString());
        JFXTreeTableColumn<PromotionVO, String> commentColumn = new SearchableStringColumn<>("备注", 100, keywordProperty, PromotionVO::getComment);

        JFXTreeTableColumn<PromotionVO, PromotionState> stateColumn = new JFXTreeTableColumn<>("状态");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getPromotionState()));
        stateColumn.setCellFactory(param -> new StateCell<>(ps -> ps.chineseName, ps -> ps.buttonStyle));

        myTreeTable.getColumns().addAll(choose, idColumn, beginTimeColumn, /*endTimeColumn,*/ commentColumn, stateColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<PromotionVO> row) {
        row.getTreeItem().getValue().getDetailPane().refresh(true);
    }
}
