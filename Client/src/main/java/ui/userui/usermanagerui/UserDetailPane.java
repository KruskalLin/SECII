package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import businesslogic.logbl.Logbl;
import businesslogic.userbl.Userbl;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import exceptions.ItemNotFoundException;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.*;
import util.EventCategory;
import util.UserCategory;
import vo.UserVO;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import static ui.util.ValidatorDecorator.RequireValid;

public class UserDetailPane extends RefreshablePane {
    @FXML
    private ModifyButton modify;
    @FXML
    private JFXButton reset, save, delete;

    final FileChooser fileChooser = new FileChooser();

    @FXML
    JFXRippler github;
    @FXML
    JFXRippler facebook;
    @FXML
    JFXRippler twitter;


    @FXML
    MaterialDesignIconView facebookIcon;
    @FXML
    MaterialDesignIconView githubIcon;
    @FXML
    MaterialDesignIconView twitterIcon;


    @FXML
    JFXTextField username;
    @FXML
    JFXComboBox<Label> usertype;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    JFXButton choose;
    @FXML
    ImageView imageview;
    @FXML
    TextArea comment;
    @FXML
    Label date;
    @FXML
    Label password;

    private Logbl logbl;
    private UserManagerblService userManagerblService;
    private UserVO userVO;
    private BooleanProperty modifyState = new SimpleBooleanProperty(true);
    private StackPane mainpane;
    /**
     * Constructors related
     */
    public UserDetailPane() {
        initialize();
    }

    public UserDetailPane(UserVO userVO) {
        this.userVO = userVO;
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

        this.mainpane = PaneFactory.getMainPane();
        this.modifyState.bind(modify.modifyProperty());


        save.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);
        choose.visibleProperty().bind(modifyState);


        username.disableProperty().bind(modifyState.not());
        usertype.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        date.setText(LocalDate.now().toString());
        RequireValid(username);


        github.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                userVO.setGithub(textFieldPane.getText());
                setColor(githubIcon, textFieldPane.getText());
            });
            textFieldPane.setPrompt("Github");
            textFieldPane.setText(userVO.getGithub());
            dialog.show();
        });

        facebook.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                userVO.setFacebook(textFieldPane.getText());
                setColor(facebookIcon, textFieldPane.getText());
            });
            textFieldPane.setPrompt("Facebook");
            textFieldPane.setText(userVO.getFacebook());
            dialog.show();
        });

        twitter.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                userVO.setTwitter(textFieldPane.getText());
                setColor(twitterIcon, textFieldPane.getText());
            });
            textFieldPane.setPrompt("Twitter");
            textFieldPane.setText(userVO.getTwitter());
            dialog.show();
        });


    }


    private String getURL() {
        return "/userui/userdetail.fxml";
    }

    /**
     * to be overriden
     */

    private boolean validate() {
        if(username.getText()==null||username.equals("")||password.getText()==null||password.getText().equals("")){
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"错误","你有未填的信息","接受");
            oneButtonDialog.show();
            oneButtonDialog.setButtonOne(()->{});
            return false;

        }
        return true;
    }

    private void updateUserVO() {
        username.setText(userVO.getUsername());
        usertype.getSelectionModel().select(userVO.getUsertype().ordinal());
        email.setText(userVO.getEmail());
        phone.setText(userVO.getPhone());
        comment.setText(userVO.getComment());
        password.setText(userVO.getPassword());
        imageview.setImage(userVO.getImage());
        setColor(facebookIcon,userVO.getFacebook()==null?"":userVO.getFacebook());
        setColor(githubIcon,userVO.getGithub()==null?"":userVO.getGithub());
        setColor(twitterIcon,userVO.getTwitter()==null?"":userVO.getTwitter());
    }

    @FXML
    private void reset() {
        TextFieldPane textFieldPane = new TextFieldPane();
        JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
        textFieldPane.cencel(() -> {
            dialog.close();
        });
        textFieldPane.save(() -> {
            dialog.close();
            password.setText(textFieldPane.getText());
        });
        textFieldPane.setText(password.getText());
        textFieldPane.setPrompt("Password");
        dialog.show();
    }

    private void setVO(){
        userVO.setComment(comment.getText());
        userVO.setEmail(email.getText());
        userVO.setImage(imageview.getImage());
        userVO.setPassword(password.getText());
        userVO.setPhone(phone.getText());
        userVO.setUsername(username.getText());
        userVO.setUsertype(UserCategory.map.get(usertype.getValue().getId()));
    }


    /**
     * FXML
     */

    @FXML
    private void save() { // 这里的save相当于提交，但是不想改名了…
        if (validate()) {
            saveTask();
            try {
                logbl.insertString(EventCategory.UpdateUser, "用户名为: " + userVO.getUsername());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"错误","你必填的信息未填","接受");
            oneButtonDialog.setButtonOne(()->{});
            oneButtonDialog.show();
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
                userManagerblService.update(userVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("sabi");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("jiushisabi");
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
                    userManagerblService.delete(userVO.getId());
                    try {
                        logbl.insertString(EventCategory.DeleteUser, "用户名为: " + userVO.getUsername());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("用户不存在，是否返回列表");
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
                if(logbl == null){
                    logbl = new Logbl();
                }
                if (userManagerblService == null) { // 这说明肯定是第一次
                    userManagerblService = new Userbl();
                    System.out.println(userVO);
                    if (userVO == null) {
                        userVO = userManagerblService.getNew();
                        delete.setVisible(false);
                        modify.setModify(true);
                        modify.setVisible(false);
                    }else{
                        userVO = userManagerblService.showDetail(userVO.getId());
                    }
                } else {
                    userVO = userManagerblService.showDetail(userVO.getId());
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(task).start();
    }

    public void setColor(MaterialDesignIconView materialDesignIconView, String s) {
        if (s.equals("")) {
            materialDesignIconView.setFill(Paint.valueOf("#000000"));
        } else {
            materialDesignIconView.setFill(Paint.valueOf("#006AF4"));
        }
    }

    @FXML
    public void choosefile() {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        Image image = null;
        try {
            image = new Image(file.toURL().toString(),100,100,true,true,true);
        }catch (Exception e){
            image = new Image("/default/timg.jpg");
            e.printStackTrace();
        }
        this.imageview.setImage(image);

    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Pictures Choose");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }



}

