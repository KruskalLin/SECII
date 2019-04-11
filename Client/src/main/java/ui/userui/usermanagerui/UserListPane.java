package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import businesslogic.logbl.Logbl;
import businesslogic.userbl.Userbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.*;
import util.EventCategory;
import util.UserSearchCondition;
import vo.UserListVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserListPane extends RefreshablePane {

    @FXML
    private JFXRippler search;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton filter;

    private UserTablePane userTreeTablePane;

    private SimpleStringProperty match = new SimpleStringProperty("");

    private UserSearchCondition userSearchCondition = new UserSearchCondition();

    private Set<UserListVO> chosenItems = new HashSet<>();

    private UserManagerblService userManagerblService;

    private UserFilterPane userFilterPane;

    private PopOver filterPopOver;

    private Logbl logbl;

    private ArrayList<UserListVO> list;
    /**
     * Constructor related
     */

    public UserListPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO 这个Treetable设位置应该让配置文件来干

        initiateTreeTable();
        setFilter();
        userTreeTablePane.setLayoutX(20);
        userTreeTablePane.setLayoutY(80);
        this.getChildren().add(userTreeTablePane);
    }


    private void setFilter(){
        filterPopOver = new PopOver();
        userFilterPane = new UserFilterPane(filterPopOver, userSearchCondition);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(userFilterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }

    private static String getURL(){
        return "/myAccountantui/myReceiptListPane.fxml";
    }

    /**
     * Abstract methods
     */
    private  void initiateTreeTable(){
        userTreeTablePane = new UserTablePane(chosenItems, searchField.textProperty());
    }

    /**
     * FXML methods
     */

    @FXML
    private void deleteList() {

        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController myBoardController = BoardController.getBoardController();
            myBoardController.Loading();
            ArrayList<UserListVO> tempList = new ArrayList<>();

            DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
            buttonDialog.setButtonOne(this::deleteList);
            buttonDialog.setButtonTwo(myBoardController::Ret);

            GetTask getTask = new GetTask(() -> {
                userTreeTablePane.refresh(list);
                myBoardController.switchTo(this);

            }, buttonDialog, woid -> {
                try {
                    for (UserListVO chosenItem : new ArrayList<>(chosenItems)) {
                        userManagerblService.delete(chosenItem.toVO().getId());
                        logbl.insertString(EventCategory.DeleteUser,"该用户名字为: "+chosenItem.getUserName());
                        chosenItems.remove(chosenItem);
                    }

                    if ((list = userManagerblService.search(userSearchCondition)) == null) {
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
            new Thread(getTask).start();
        }).show();
    }

    @FXML
    private void search() {
        if (!searchField.getText().equals("")) {
            match.setValue(searchField.getText());
            ArrayList<UserListVO> templist = list;
            templist = templist.stream().filter(
                    s -> s.getUserCategory().name().contains(match.get()) ||
                            s.getUserName().contains(match.get())||
                            String.valueOf(s.getUserID()).contains(match.get())
            ).collect(Collectors.toCollection(ArrayList::new));
            userTreeTablePane.refresh(templist);
        }
    }

    @FXML
    private void add() {
        new UserDetailPane().refresh(true);
        try{
        logbl.insertString(EventCategory.CreateUser,"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Refresh
     */
    @Override
    public void refresh(boolean toSwitch) {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            userTreeTablePane.refresh(list);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            try {
                if (userManagerblService == null) { // 如果这里扔出exception，十有八九是因为命名不对应。
                    userManagerblService = new Userbl();
                }
                if(logbl == null){
                    logbl = new Logbl();
                }

                if ((list = userManagerblService.search(userSearchCondition)) == null) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        })).start();
    }

}
