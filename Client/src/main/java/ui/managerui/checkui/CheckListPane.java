package ui.managerui.checkui;

import blService.checkblService.CheckblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import ui.common.BoardController;
import ui.common.ExceptionRunnable;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import ui.util.RefreshablePane;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CheckListPane extends RefreshablePane {
    @FXML
    private JFXButton approve, reject;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXTextField keywordField;
    private CheckTable checkTable;

    private CheckblService checkblService;
    private Set<ReceiptVO> chosenItems = new HashSet<>();

    public CheckListPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/checkListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        checkTable = new CheckTable(chosenItems, keywordField.textProperty(), new ArrayList<>());
        checkTable.setLayoutX(20);
        checkTable.setLayoutY(80);
        this.getChildren().add(checkTable);
    }

    @FXML
    private void searchKeyword() {
    }

    @FXML
    private void approve() {
        approveRejectPattern(() -> {
            for (ReceiptVO chosenItem : new ArrayList<>(chosenItems)) {
                checkblService.approve(chosenItem);
                chosenItems.remove(chosenItem);
            }
        });
    }

    @FXML
    private void reject() {
        approveRejectPattern(() -> {
            for (ReceiptVO chosenItem : new ArrayList<>(chosenItems)) {
                checkblService.reject(chosenItem);
                chosenItems.remove(chosenItem);
            }
        });
    }

    private void approveRejectPattern(ExceptionRunnable runnable) {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();
        ArrayList<ReceiptVO> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            checkTable.refresh(tempList);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            try {

                runnable.run();

                ArrayList<ReceiptVO> receipts;
                if ((receipts = checkblService.initCheck()) == null) {
                    return false;
                }
                tempList.clear();
                tempList.addAll(receipts);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        })).start();
    }

    @Override
    public void refresh(boolean toSwitch) {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();
        ArrayList<ReceiptVO> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            checkTable.refresh(tempList);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            try {
                if (checkblService == null) {
                    checkblService = MyblServiceFactory.getService(CheckblService.class);
                }

                ArrayList<ReceiptVO> receipts;
                if ((receipts = checkblService.initCheck()) == null) {
                    return false;
                }
                tempList.clear();
                tempList.addAll(receipts);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })).start();
    }
}
