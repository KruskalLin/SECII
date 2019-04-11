package ui.messageui;

import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.JFXListCell;
import ui.common.dialog.MyOneButtonDialog;
import ui.util.RefreshablePane;
import vo.ReceiptMessageVO;

import java.rmi.RemoteException;

public class MyMessageCell extends JFXListCell<ReceiptMessageVO> {
//    private MyMessageHBox messageHBox;

    @Override
    public synchronized void updateItem(ReceiptMessageVO item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            MyMessageHBox messageHBox = new MyMessageHBox(item);
            setGraphic(messageHBox);
        } else {
            setGraphic(null);
        }
        setText(null);
    }

    public void clickAction() {
        ((RefreshablePane) getItem().getReceiptVO().getDetailPane()).refresh(false);
        try {
            MyServiceFactory.getReceiptMessageblService().delete(getItem().toPO());
        } catch (RemoteException e) { // TODO 还是不会做这种异常处理…
            e.printStackTrace();
            new MyOneButtonDialog("连接错误").show();
        }
    }
}
