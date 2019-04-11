package ui.userui.loginui;

import blService.userblService.LoginblService;
import businesslogic.userbl.Loginbl;
import com.jfoenix.controls.*;
import java.io.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
//import ui.exception.LoginRMIException;
import ui.util.*;
import util.ResultMessage;
import util.UserCategory;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import static ui.util.SetDraggable.setDraggable;

public class Login implements Initializable{
    @FXML
    AnchorPane pane;
    @FXML
    JFXRippler close;
    @FXML
    JFXRippler hide;
    @FXML
    JFXButton login;
    @FXML
    JFXTextField account;
    @FXML
    JFXPasswordField password;
    @FXML
    StackPane mainPane;



    @FXML
    JFXCheckBox keep;

    private LoginblService ls;

    private SimpleIntegerProperty state = new SimpleIntegerProperty(-1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PaneFactory.setLoginPane(mainPane);
    }



    @FXML
    public void close(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void login() throws Exception{
        LoginTask task = new LoginTask(account.getText(),password.getText());
        pane.getChildren().remove(3,8);
        Loading loading = new Loading();
        loading.setLayoutX(95);
        loading.setLayoutY(160);
        pane.getChildren().add(loading);
        task.valueProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if(state.getValue()==0){
                    try{

                        UserVO userVO = ls.getCategory(account.getText());
                        UserInfomation.userid = userVO.getId();
                        UserInfomation.userimage = userVO.getImage();
                        UserInfomation.usertype = userVO.getUsertype();
                        UserInfomation.username = userVO.getUsername();


                        switch (userVO.getUsertype()){
                            case UserManager:
                                changeToUserManger();
                                break;
                            case Salesman:
                                changeToSales();
                                break;
                            case SalesManager:
                                changeToSales();
                                break;
                            case Accountant:
                                changetoAccountant();
                                break;
                            case GeneralManager:
                                changetoGeneralManager();
                                break;
                            case InventoryManager:
                                changetoInventory();
                                break;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                   try{
                       changeToLogin();
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }
            }
        });

        new Thread(task).start();


    }

    @FXML
    public void forgetpassword(){
        TextFieldPane textFieldPane = new TextFieldPane();
        JFXDialog dialog = new JFXDialog(PaneFactory.getLoginPane(), textFieldPane, JFXDialog.DialogTransition.CENTER);
        textFieldPane.cencel(() -> {
            dialog.close();
        });
        textFieldPane.save(() -> {
            dialog.close();
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getLoginPane(),"","我们已经联系用户管理员，请等候相应信息","接受");
            oneButtonDialog.setButtonOne(()->{});
            oneButtonDialog.show();
        });
        textFieldPane.setPrompt("你的用户ID");
        dialog.show();
    }


    @FXML
    public void hide() throws Exception{
        Stage stage=(Stage)hide.getScene().getWindow();
        stage.setIconified(true);

    }

    public void changeToLogin() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/userui/login.fxml"));
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        if(state.getValue()==2) {
            OneButtonDialog dialog = new OneButtonDialog(PaneFactory.getLoginPane(), "错误", "您输入错误的账号或者密码", "接受");
            dialog.setButtonOne(() -> {
            });
            dialog.show();
        }else if(state.getValue()==1){
            OneButtonDialog dialog = new OneButtonDialog(PaneFactory.getLoginPane(), "错误", "网络错误", "接受");
            dialog.setButtonOne(() -> {
            });
            dialog.show();
        }

        setDraggable(scene,stage);
    }


    public void changeTo(String url) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Stage stage = (Stage) mainPane.getScene().getWindow();
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: transparent;");
        PaneFactory.setMainPane(stackPane);
        stackPane.getChildren().setAll(root);
        Scene scene = new Scene(stackPane);
        NodeHolder nodeHolder = new NodeHolder(stage,Duration.millis(1000), NodeAnimation.FADE);
        nodeHolder.apply();
        stage.setScene(scene);
        setDraggable(scene,stage);
    }

    public void changeToUserManger() throws Exception{
        changeTo("/userui/usermanager.fxml");
    }

    public void changeToSales() throws Exception{
        changeTo("/salesui/sales.fxml");
    }

    public void changetoAccountant() throws Exception{
        changeTo("/accountantui/accountantMain.fxml");
    }

    public void changetoInventory() throws Exception{
        changeTo("/inventoryui/inventoryMain.fxml");
    }
    public void changetoGeneralManager() throws Exception{
        changeTo("/managerui/managerMain.fxml");
    }


    public String deleteEven(String str){
        String s="";
        for(int i=1;i<str.length();i+=2){
            s+=str.charAt(i);
        }
        return s;
    }
    private class LoginTask extends Task<Boolean> {
        private String account;
        private String password;


        public LoginTask(String account,String password){
            this.account = account;
            this.password = password;
        }
        @Override
        protected Boolean call() {

            if(ls==null) {
                try {
                    ls = new Loginbl();
                }catch (Exception e){
                    state.set(1);
                    return false;
                }
            }

            ResultMessage resultMessage = null;
            try{
                resultMessage = ls.login(account,password);
            }catch (Exception e){
                e.printStackTrace();
                state.set(1);
                return false;
            }

            if(resultMessage == ResultMessage.SUCCESS){
                state.setValue(0);
                System.out.println("SUCCESS");
                return true;
            }else {
                state.set(2);
                System.out.println("FAIL");
                return false;
            }
        }
    }





}
