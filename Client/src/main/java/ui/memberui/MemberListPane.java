package ui.memberui;

import blService.memberblService.MemberblService;
import businesslogic.logbl.Logbl;
import businesslogic.memberbl.Memberbl;
import com.jfoenix.controls.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.common.dialog.MyTwoButtonDialog;
import ui.mainui.Carousel;
import ui.util.*;
import util.EventCategory;
import util.MemberSearchCondition;
import vo.MemberListVO;
import vo.UserListVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberListPane extends RefreshablePane {
    @FXML
    private JFXRippler search;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton filter;

    private MemberTreeTable memberTreeTable;

    private SimpleStringProperty match = new SimpleStringProperty("");

    private MemberSearchCondition memberSearchCondition = new MemberSearchCondition();

    private Set<MemberListVO> chosenItems = new HashSet<>();

    private MemberblService memberblService;

    private MemberFilterPane memberFilterPane;

    private PopOver filterPopOver;

    private ArrayList<MemberListVO> list;

    private Logbl logbl;
    /**
     * Constructor related
     */

    public MemberListPane() {
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
        memberTreeTable.setLayoutX(20);
        memberTreeTable.setLayoutY(80);
        this.getChildren().add(memberTreeTable);
    }


    private void setFilter(){
        filterPopOver = new PopOver();
        memberFilterPane = new MemberFilterPane(filterPopOver, memberSearchCondition);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(memberFilterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }

    private static String getURL(){
        return "/myAccountantui/myReceiptListPane.fxml";
    }

    /**
     * Abstract methods
     */
    private  void initiateTreeTable(){
        memberTreeTable = new MemberTreeTable(chosenItems, searchField.textProperty());
    }

    /**
     * FXML methods
     */

    @FXML
    private void deleteList() {

        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController myBoardController = BoardController.getBoardController();
            myBoardController.Loading();
            DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "错误", "连接失败", "重试", "返回");
            buttonDialog.setButtonOne(this::deleteList);
            buttonDialog.setButtonTwo(myBoardController::Ret);
            GetTask getTask = new GetTask(() -> {
                memberTreeTable.refresh(list);
                myBoardController.switchTo(this);

            }, buttonDialog, woid -> {
                try {
                    for (MemberListVO chosenItem : new ArrayList<>(chosenItems)) {
                        memberblService.delete(chosenItem.toVO().getMemberId());
                        logbl.insertString(EventCategory.DeleteMember,"");
                        chosenItems.remove(chosenItem);
                    }

                    if ((list = memberblService.search(memberSearchCondition)) == null) {
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
            match.setValue(searchField.getText().toLowerCase());
            ArrayList<MemberListVO> templist = new ArrayList<>();
            templist = templist.stream().filter(
                    s -> s.getMemberCategory().name().contains(match.get()) ||
                            s.getName().contains(match.get())||
                            String.valueOf(s.getMemberId()).contains(match.get())
            ).collect(Collectors.toCollection(ArrayList::new));
            memberTreeTable.refresh(templist);
        }
    }

    @FXML
    private void add() {
        new MemberDetailPane().refresh(true);
        try {
            logbl.insertString(EventCategory.CreateMember, "");
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

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "错误", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            memberTreeTable.refresh(list);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            try {
                if(logbl==null){
                    logbl=new Logbl();
                }

                if (memberblService == null) { // 如果这里扔出exception，十有八九是因为命名不对应。
                    memberblService = new Memberbl();
                }
                if ((list = memberblService.search(memberSearchCondition)) == null) {
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
