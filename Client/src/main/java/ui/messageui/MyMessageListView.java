package ui.messageui;

import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXListView;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import org.controlsfx.control.PopOver;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.util.UserInfomation;
import vo.ReceiptMessageVO;

import java.rmi.RemoteException;
import java.util.List;

public class MyMessageListView extends JFXListView<ReceiptMessageVO> implements FXMLLoadableMixer{
    List<ReceiptMessageVO> data;
    public MyMessageListView(PopOver popOver) {
        load();

        this.setCellFactory(p -> {
            MyMessageCell cell =  new MyMessageCell();
            cell.setOnMouseClicked(e -> {
                popOver.hide();
                myRefresh(); // TODO 这个好像还没用？
                cell.clickAction();
            });
            return cell;
        });
    }

    @Override
    public String publicGetURL() {
        return "/messageui/message.fxml";
    }

    public void myRefresh() {
        try {
            data = MyServiceFactory.getReceiptMessageblService().selectByUser();

            this.setItems(FXCollections.observableArrayList(data));
        } catch (RemoteException e) {
            e.printStackTrace();
            new MyOneButtonDialog("连接错误").show();
        }
    }
}
