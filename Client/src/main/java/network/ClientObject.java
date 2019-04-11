package network;

import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import po.ReceiptMessagePO;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.RefreshablePane;
import ui.util.UserInfomation;
import util.MessagePurpose;
import util.UserCategory;
import vo.ReceiptMessageVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientObject extends UnicastRemoteObject implements ClientInterface {
    public ClientObject() throws RemoteException {
    }

    @Override
    public void receive(ReceiptMessagePO messagePO) throws RemoteException {
        ReceiptMessageVO messageVO = new ReceiptMessageVO(messagePO);
//        StringProperty content2 = new SimpleStringProperty();

        MyServiceFactory.getReceiptMessageblService().selectByUser();
        Platform.runLater(() -> {
            new MyTwoButtonDialog(messageVO.getPrompt() + "\n"
                    + "单据编号：" + messageVO.getReceiptVO().getId() + "\n"
                    + "是否现在查看", () -> {
                try {
//                    System.out.println("messagePO id" + messagePO.getId());
//                    System.out.println("messageVO id" + messageVO.getId());
                    MyServiceFactory.getReceiptMessageblService().delete(messagePO);
                } catch (RemoteException e) {
                    // TODO 这个应该不可能吧呃
                    e.printStackTrace();
                }
                ((RefreshablePane) messageVO.getReceiptVO().getDetailPane()).refresh(false);
            }).show(); // 这里返回的时候不作刷新处理，因为比如正在写草稿…你一刷新这也太坑了吧
        });
    }

    @Override
    public int getUserId() throws RemoteException {
        return UserInfomation.userid; // 直接这样真的可以吗…
    }

    @Override
    public UserCategory getUserCategory() throws RemoteException {
        return UserInfomation.usertype;
    }
}
