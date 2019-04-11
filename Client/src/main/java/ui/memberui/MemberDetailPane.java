package ui.memberui;

import blService.memberblService.MemberblService;
import businesslogic.logbl.Logbl;
import businesslogic.memberbl.Memberbl;
import com.jfoenix.controls.*;
import exceptions.ItemNotFoundException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.Rating;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.*;
import util.EventCategory;
import util.MemberCategory;
import util.UserCategory;
import vo.MemberVO;

import java.io.IOException;
import java.rmi.RemoteException;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class MemberDetailPane extends RefreshablePane {
    @FXML
    private ModifyButton modify;
    @FXML
    private JFXButton reset, save, delete;
    @FXML
    Rating rating;
    @FXML
    TextField address;
    @FXML
    TextField clerkName;
    @FXML
    JFXTextField receiableAmount;
    @FXML
    JFXTextField give;
    @FXML
    JFXTextField get;
    @FXML
    JFXTextField memberName;
    @FXML
    JFXComboBox<Label> memberCategory;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    ImageView imageview;
    @FXML
    TextArea comment;
    @FXML
    TextField zipcode;
    @FXML
    Label memberId;

    Logbl logbl;

    private MemberblService memberblService;
    private MemberVO memberVO;
    private BooleanProperty modifyState = new SimpleBooleanProperty(true);
    private StackPane mainpane;
    /**
     * Constructors related
     */
    public MemberDetailPane() {
        initialize();
    }

    public MemberDetailPane(MemberVO memberVO) {
        this.memberVO = memberVO;
        initialize();
    }

    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.modifyState.bind(modify.modifyProperty());
        this.mainpane = PaneFactory.getMainPane();
        memberName.disableProperty().bind(modifyState.not());
        memberCategory.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        address.disableProperty().bind(modifyState.not());
        clerkName.disableProperty().bind(modifyState.not());
        receiableAmount.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        zipcode.disableProperty().bind(modifyState.not());
        save.visibleProperty().bind(modifyState);

        RequireValid(memberName);
        DoubleValid(receiableAmount);

    }


    private String getURL() {
        return "/memberui/memberdetailpane.fxml";
    }

    /**
     * to be overriden
     */

    private boolean validate() {
        if(memberName.getText()==null||receiableAmount.getText()==null||memberName.getText().equals("")||receiableAmount.getText().equals("")){
            memberName.validate();
            receiableAmount.validate();
            return false;
        }else if(receiableAmount.getText()!=null&&!receiableAmount.getText().equals("")){
                if(Double.parseDouble(receiableAmount.getText())<0){
                    OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "错误", "你只能写>=0的数", "接受");
                    oneButtonDialog.show();
                    oneButtonDialog.setButtonOne(() -> {
                    });
                    return false;

                }

                if(UserInfomation.usertype== UserCategory.Salesman&&Double.parseDouble(receiableAmount.getText())>1000) {
                    OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "错误", "你只能写<=1000的数", "接受");
                    oneButtonDialog.show();
                    oneButtonDialog.setButtonOne(() -> {
                    });
                    return false;

                }
                if(UserInfomation.usertype== UserCategory.SalesManager&&Double.parseDouble(receiableAmount.getText())>5000) {
                    OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "错误", "你只能写<=5000的数", "接受");
                    oneButtonDialog.show();
                    oneButtonDialog.setButtonOne(() -> {
                    });
                    return false;

                }
            }

        return true;
    }

    private void updateUserVO() {
        if(memberVO!=null) {
            memberName.setText(memberVO.getName());
            memberCategory.getSelectionModel().select(memberVO.getMemberCategory().ordinal());
            clerkName.setText(memberVO.getClerkName());
            comment.setText(memberVO.getComment());
            address.setText(memberVO.getAddress());
            email.setText(memberVO.getEmail());
            phone.setText(memberVO.getPhone());
            rating.setRating(memberVO.getDegree());
            receiableAmount.setText(String.valueOf(memberVO.getReceiableAmount()));
            give.setText(String.valueOf(memberVO.getGive()));
            get.setText(String.valueOf(memberVO.getGet()));
            imageview.setImage(memberVO.getImage());
            memberId.setText(String.valueOf(memberVO.getMemberId()));
            zipcode.setText(memberVO.getZipCode());
        }
    }


    private void setVO(){
        memberVO.setPhone(phone.getText());
        memberVO.setEmail(email.getText());
        memberVO.setAddress(address.getText());
        memberVO.setClerkName(clerkName.getText());
        memberVO.setComment(comment.getText());
        memberVO.setDegree((int)rating.getRating());
        memberVO.setGet(Double.parseDouble(get.getText()));
        memberVO.setGive(Double.parseDouble(give.getText()));
        memberVO.setReceiableAmount(Double.parseDouble(receiableAmount.getText()));
        memberVO.setImage(imageview.getImage());
        if(memberCategory.getValue()!=null)
        memberVO.setMemberCategory(MemberCategory.map.get(memberCategory.getValue().getId()));
        memberVO.setName(memberName.getText());
        memberVO.setZipCode(zipcode.getText());

    }


    /**
     * FXML
     */

    @FXML
    private void save() { // 这里的save相当于提交，但是不想改名了…
        if (validate()) {
            saveTask();
            try {
                logbl.insertString(EventCategory.UpdateMember, "");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void saveTask() {
        BoardController boardController = BoardController.getBoardController();
        boardController.Loading();

        setVO();

        StringProperty prompt = new SimpleStringProperty();
        new Thread(new GetTask(() -> {
            new MyTwoButtonDialog("保存成功", boardController::goBack).show();
        }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
            try {
                memberblService.update(memberVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("未找到对应id");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("网络错误");
                return false;
            }
        })).start();
    }

    @FXML
    private void delete() {
        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController boardController = BoardController.getBoardController();
            boardController.Loading();

            StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("删除成功", boardController::goBack).show();
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    memberblService.delete(memberVO.getMemberId());
                    try {
                        logbl.insertString(EventCategory.DeleteMember, "");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("用户不存在，是否返回用户列表");
                    return false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    prompt.set("连接错误");
                    return false;
                }
            })).start();
        }).show();
    }

    @FXML
    private void modify() {
        modifyState.set(!modifyState.get());
    }

    /**
     * refresh
     */
    @Override
    public void refresh(boolean toSwitch) {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

        GetTask task = new GetTask(() -> {
            myBoardController.switchTo(this); // 感觉这个分两次来明显就是为了可以有等待…
            updateUserVO();
        }, dialog, woid -> {
            try {
                if(logbl==null){
                    logbl = new Logbl();
                }


                if (memberblService == null) { // 这说明肯定是第一次
                    memberblService = new Memberbl();
                    System.out.println(memberVO);
                    if (memberVO == null) {
                        memberVO = memberblService.getNew();
                        System.out.println(memberVO);
                        delete.setVisible(false);
                        modify.setModify(true);
                        modify.setVisible(false);
                    } else {
                        memberVO = memberblService.showDetail(memberVO.getMemberId());
                    }
                } else {
                    memberVO = memberblService.showDetail(memberVO.getMemberId());
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(task).start();
    }





}
