package ui.common.bigPane;

import blService.genericblService.ReceiptblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.logbl.Logbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.common.MyFilterPane;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import ui.util.RefreshablePane;
import util.EventCategory;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import vo.receiptVO.ReceiptListVO;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class MyReceiptListPane<TL extends ReceiptListVO<TL>, TV extends ReceiptVO> extends RefreshablePane {
    @FXML
    protected JFXRippler search;
    @FXML
    protected JFXButton filter;
    @FXML
    protected JFXTextField searchField;
    protected MyTreeTableBorderPane<TL> receiptListTreeTable;

    private RespectiveReceiptSearchCondition respectiveReceiptSearchCondition = new RespectiveReceiptSearchCondition();

    private Logbl logbl;

    protected Set<TL> chosenItems = new HashSet<>();

    protected ReceiptblService<TV> receiptblService;

    /**
     * Constructor related
     */

    public MyReceiptListPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/myAccountantui/myReceiptListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO 这个pane里面还没有做筛选，也没有做对于审批的适配

        initiateTreeTable();
        receiptListTreeTable.setLayoutX(20);
        receiptListTreeTable.setLayoutY(80);
        this.getChildren().add(receiptListTreeTable);

        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        MyFilterPane myFilterPane = new MyFilterPane(filterPopOver, this, respectiveReceiptSearchCondition);
        filterPopOver.setContentNode(myFilterPane);
        filter.setOnAction(e -> filterPopOver.show(filter));
    }

    /**
     * Abstract methods
     */
    protected abstract void initiateTreeTable();
    protected abstract Class<? extends ReceiptblService<TV>> getServiceClass();
    protected abstract RefreshablePane getNewDetailPane();


    /**
     * FXML methods
     */

    @FXML
    private void deleteList() {
        if (chosenItems.isEmpty()) {
            new MyOneButtonDialog("请选择删除项目").show();
            return;
        }
        for (TL chosenItem : new ArrayList<>(chosenItems)) {
            ReceiptState receiptState = chosenItem.toVO().getReceiptState();
            if (receiptState == ReceiptState.PENDING || receiptState == ReceiptState.APPROVED) {
                new MyOneButtonDialog("不可删除提交或已批准单据").show();
                return;
            }
        }
        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController myBoardController = BoardController.getBoardController();
            myBoardController.Loading();
            ArrayList<TL> tempList = new ArrayList<>();

            DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
            buttonDialog.setButtonOne(this::deleteList);
            buttonDialog.setButtonTwo(myBoardController::Ret);

            GetTask getTask = new GetTask(() -> {
                refresh(false);

            }, buttonDialog, woid -> {
                try {
                    for (TL chosenItem : new ArrayList<>(chosenItems)) {
                        receiptblService.delete(chosenItem.toVO());
                        logbl.insertString(EventCategory.DeleteReceipt,"");
                        chosenItems.remove(chosenItem);
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
    protected void search() {

    }

    @FXML
    private void add() {
        getNewDetailPane().refresh(true);
        try{
            logbl.insertString(EventCategory.CreateReceipt,"");
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

        ArrayList<TL> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            chosenItems.clear();
            receiptListTreeTable.refresh(tempList);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            try {

                if(logbl == null){
                    try {
                        logbl = new Logbl();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if (receiptblService == null) { // 如果这里扔出exception，十有八九是因为命名不对应。
                    receiptblService = MyblServiceFactory.getService(getServiceClass());
                }

                ArrayList<TV> receipts = null;
                if ((receipts = receiptblService.search(respectiveReceiptSearchCondition)) == null) {
                    return false;
                }
                tempList.clear(); // TODO 我也不知道下面这里为什么高亮，我猜可能是因为类型推断太弱了？
                tempList.addAll(receipts.stream().map(v -> (TL) v.toListVO()).collect(Collectors.toCollection(ArrayList::new)));
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })).start();
    }
}
