package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.ImageColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import util.UserCategory;
import vo.UserListVO;

import java.util.Set;

public class UserTablePane extends MyTreeTableBorderPane<UserListVO> {

    public UserTablePane(Set<UserListVO> chosenItems, StringProperty keywordProperty){

        JFXTreeTableColumn<UserListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<UserListVO,String> userId = new SearchableStringColumn<>("ID", 20, keywordProperty, p -> String.valueOf(p.getUserID()));
        JFXTreeTableColumn image = new ImageColumn("姓名");
        JFXTreeTableColumn<UserListVO, String> userName = new SearchableStringColumn<>(" ", 60, keywordProperty, p -> p.getUserName());
        JFXTreeTableColumn<UserListVO, String> phone = new SearchableStringColumn<>("电话", 150, keywordProperty, p -> p.getPhone());
        JFXTreeTableColumn<UserListVO, String> stateColumn = new JFXTreeTableColumn<>("类型");
        stateColumn.setPrefWidth(150);
        stateColumn.setCellValueFactory(param ->param.getValue()==null? new ReadOnlyObjectWrapper<>(""):new ReadOnlyObjectWrapper<>(param.getValue().getValue().getUserCategory().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<UserListVO>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setCivText(UserCategory.chinese.get(UserCategory.map.get(item)));
                    setButtonStyle(UserCategory.color.get(item)+";-fx-pref-width:100");

                }
            }
        });

        myTreeTable.getColumns().addAll(choose,userId,image,userName, stateColumn,phone);
    }
    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<UserListVO> row) {
        UserDetailPane userDetailPane = new UserDetailPane(row.getTreeItem().getValue().toVO());
        userDetailPane.refresh(false);
    }
}
