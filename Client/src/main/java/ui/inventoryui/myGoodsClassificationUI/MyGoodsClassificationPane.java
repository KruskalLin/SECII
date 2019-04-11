package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.MessageObjectFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import network.ServerInterface;
import ui.common.bigPane.GatePane;
import ui.common.dialog.MyOneButtonDialog;
import ui.util.GetTask;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyGoodsClassificationPane extends GatePane {
    @FXML
    private JFXTextField keywordField;

    private MyGoodsClasssificationTreeTableView myGoodsClasssificationTreeTableView;
    private RecursiveGoodsClassificationVO root;

//    private MyGoodsClassificationblService myGoodsClassificationblService;

    public MyGoodsClassificationPane() {
        myGoodsClasssificationTreeTableView = new MyGoodsClasssificationTreeTableView();
        myGoodsClasssificationTreeTableView.setLayoutX(27);
        myGoodsClasssificationTreeTableView.setLayoutY(75);
        myGoodsClasssificationTreeTableView.setPrefHeight(450);
        this.getChildren().add(myGoodsClasssificationTreeTableView);


//        // 下面这几行仅供测试
//        Button testButton = new Button("测试消息功能用的");
//        testButton.setOnAction(e -> {
//            try {
//                ServerInterface serverInterface = MessageObjectFactory.getServerInterface();
////                serverInterface.send("测试一下而己");
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        });
//        testButton.setLayoutX(100);
//        testButton.setLayoutY(40);
//        this.getChildren().add(testButton);
    }

    @Override
    protected void refreshAfterMath() {
        myGoodsClasssificationTreeTableView.refresh(root);
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
//        myGoodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        if (keywordField.getText() != null && keywordField.getText().equals("")) {
            root = ((MyGoodsClassificationblService) MyblServiceFactory.getService(MyGoodsClassificationblService.class)).selectRoot();
        } else {
            root = ((MyGoodsClassificationblService) MyblServiceFactory.getService(MyGoodsClassificationblService.class)).fuzzySearchRoot(keywordField.getText());
            System.out.println("只是为了能设断点");
        }
    }

    @Override
    protected String getURL() {
        return "/inventoryui/goodui/myGoodsClassificationPane.fxml";
    }


    @Override
    protected void initiateFields() {
        super.initiateFields();
    }

    /**
     * FXML
     */
    @FXML
    private void fuzzySearch() {
        refresh(false);
    }
}
