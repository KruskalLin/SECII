package ui.memberui;

import blService.memberblService.MemberInfo;
import businesslogic.memberbl.MemberInfo_Impl;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import ui.messageui.MessageCell;
import vo.MemberVO;

public class ChooseList extends StackPane {


    ObservableList<MemberVO> observableList= FXCollections.observableArrayList();

    MemberInfo memberInfo;

    JFXDialog dialog;


    @FXML
    private JFXListView<MemberVO> list;


    public ChooseList(MemberVO memberVO,Runnable runnable){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/memChoose.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            list.setCellFactory(new Callback<ListView<MemberVO>, ListCell<MemberVO>>() {
                @Override
                public ListCell<MemberVO> call(ListView<MemberVO> param) {
                    ChooseListCell chooseListCell = new ChooseListCell();
                    chooseListCell.setOnMouseClicked(t->{
                        if(t.getClickCount()==2) {
                            memberVO.setName(chooseListCell.getItem().getName());
                            memberVO.setMemberId(chooseListCell.getItem().getMemberId());
                            memberVO.setDegree(chooseListCell.getItem().getDegree());
                            Platform.runLater(runnable);
                            dialog.close();
                        }
                    });
                    return chooseListCell;
                }

            });
            memberInfo = new MemberInfo_Impl();
            observableList.setAll(memberInfo.getALL());
            list.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JFXDialog getDialog() {
        return dialog;
    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
