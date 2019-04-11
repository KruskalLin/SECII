package ui.common.bigPane;

import blService.checkblService.CheckInfo;
import blService.genericblService.ReceiptblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.logbl.Logbl;
import com.jfoenix.controls.JFXButton;
import exceptions.ItemNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.ModifyButton;
import ui.util.RefreshablePane;
import ui.util.UserInfomation;
import util.EventCategory;
import util.ReceiptState;
import util.UserCategory;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public abstract class MyReceiptDetailPane<TV extends ReceiptVO> extends RefreshablePane {
    @FXML
    protected JFXButton reset, save, saveAsDraft, delete, approve, reject, redCredit, redCreditCopy;
    @FXML
    protected ModifyButton modify;
    @FXML
    protected Label idLabel;

    protected TV receiptVO;

    private boolean isFirst = true;

    private Logbl logbl;


    /**
     * Constructors related
     */
    public MyReceiptDetailPane() {
        initiate();
    }

    public MyReceiptDetailPane(TV receiptVO) {
        this.receiptVO = receiptVO;
        initiate();
    }

    protected void initiate() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        save.setText("提交");

        approve.setVisible(false);
        reject.setVisible(false);
        redCredit.setVisible(false);
        redCreditCopy.setVisible(false);

        if (receiptVO != null) {
            // 默认是草稿的状态，如果执行到下面说明不是。
            if (receiptVO.getReceiptState() != ReceiptState.DRAFT) {
                saveAsDraft.setVisible(false);
                reset.setLayoutX(saveAsDraft.getLayoutX());
            }

            // 如果不是总经理，提交后的单据只能看。如果是审批通过的或者就只能看
            if (receiptVO.getReceiptState() == ReceiptState.APPROVED
                    || UserInfomation.usertype != UserCategory.GeneralManager && receiptVO.getReceiptState() == ReceiptState.PENDING) {
                modify.setVisible(false); // 这句还是个问题
                reset.setVisible(false);
                save.setVisible(false);
                saveAsDraft.setVisible(false);
                delete.setVisible(false);
            }
            if (receiptVO.getReceiptState() == ReceiptState.APPROVED //&& SomeParameter.isRedCreditable
                    && UserInfomation.usertype == UserCategory.Accountant) {
                redCredit.setVisible(true);
                redCreditCopy.setVisible(true);
                save.setVisible(false);
                saveAsDraft.setVisible(false);
                reset.setVisible(false);
            }

            if (UserInfomation.usertype == UserCategory.GeneralManager && receiptVO.getReceiptState() == ReceiptState.PENDING) { // 如果是总经理肯定只能是pending的
                reset.setVisible(false);
                save.setVisible(false);
                saveAsDraft.setVisible(false);
                delete.setVisible(false);

                // modify, reset, save, saveAsDraft, delete;

                approve.setVisible(true);
                reject.setVisible(true);
            }
        }
    }

    /**
     * abstract methods
     */
    protected abstract String getURL();

    protected abstract Class<? extends ReceiptblService<TV>> getServiceClass();

    /**
     * to be overriden
     */

    protected boolean validate() {
        return true;
    }

    protected void updateReceiptVO() {
    }

    protected void setRedCredit(TV redCreditVO) {
        redCreditVO.setReceiptState(receiptVO.getReceiptState());
        redCreditVO.setOperatorId(receiptVO.getOperatorId());
    }

    @FXML
    protected void reset() {
        idLabel.setText(receiptVO.getId());
    }

    /**
     * FXML
     */

    @FXML
    protected void save() { // 这里的save相当于提交，但是不想改名了…
        if (validate()) { // TODO 这里的逻辑大都差不多，实在应该重构一下
            BoardController boardController = BoardController.getBoardController();
            boardController.Loading();

            updateReceiptVO();

            StringProperty prompt = new SimpleStringProperty();
            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("保存成功", boardController::goBack).show();
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    getReceiptblService().submit(receiptVO);
                    logbl.insertString(EventCategory.UpdateReceipt,"");
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("单据不存在，是否返回单据列表"); // 这是不可能的吧…除了自己没人会删，不过要是考虑多端登陆的情况…
                    return false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    prompt.set("连接错误");
                    return false;
                }
            })).start();
        }
    }

    @FXML
    private void saveAsDraft() {
        BoardController boardController = BoardController.getBoardController();
        boardController.Loading();

        updateReceiptVO();

        StringProperty prompt = new SimpleStringProperty();
        new Thread(new GetTask(() -> {
            new MyOneButtonDialog("保存成功", boardController::goBack).show();
        }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
            try {
                getReceiptblService().update(receiptVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("单据不存在，是否返回单据列表"); // 这是不可能的吧…除了自己没人会删，不过要是考虑多端登陆的情况…
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("连接错误");
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
            }, new MyTwoButtonDialog(prompt, boardController::goBack), woid -> {
                try {
                    getReceiptblService().delete(receiptVO);
                    logbl.insertString(EventCategory.DeleteReceipt,"");
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("单据不存在，是否返回单据列表");
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
    private void approve() { // approve的时候顺便update单据
        if (validate()) {
            System.out.println("MyReceiptDetailPane approve called");
            receiptVO.setReceiptState(ReceiptState.APPROVED); // TODO 这个其实不应该在这里set的

            BoardController myBoardController = BoardController.getBoardController();
            myBoardController.Loading();

            updateReceiptVO();

            MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("审批通过", myBoardController::goBack).show();
            }, dialog, woid -> {
                try {
                    logbl.insertString(EventCategory.ApproveReceipt,"");
                    getCheckInfo().approve(receiptVO);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            })).start();
        }
    }

    @FXML
    private void reject() { // reject就不需要update数据
        receiptVO.setReceiptState(ReceiptState.REJECTED);

        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

        new Thread(new GetTask(() -> {
            new MyOneButtonDialog("驳回单据", myBoardController::goBack).show();
        }, dialog, woid -> {
            try {
                logbl.insertString(EventCategory.RejectReceipt,"");
                getCheckInfo().reject(receiptVO);
                System.out.println(" reject success");
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })).start();
    }

    @FXML
    private void clickRedCredit() {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        updateReceiptVO();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

        new Thread(new GetTask(() -> {
            new MyOneButtonDialog("红冲成功", myBoardController::goBack).show();
        }, dialog, woid -> {
            try {
                TV redCreditVO = getReceiptblService().getNew();
                redCreditVO.setOperatorId(UserInfomation.userid);
                setRedCredit(redCreditVO);
                redCreditVO.setReceiptState(ReceiptState.APPROVED);

                getCheckInfo().approve(receiptVO);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })).start();

    }

    @FXML
    protected void clickRedCreditCopy() {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        updateReceiptVO();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

        new Thread(new GetTask(() -> {
            redCredit.setVisible(false);
            redCreditCopy.setText("保存");
            modify.setModify(true);

            redCreditCopy.setOnMouseClicked(e -> {
                new Thread(new GetTask(() -> {
                    new MyOneButtonDialog("保存成功", () -> {
                        BoardController.getBoardController().goBack();
                    }).show();
                }, new MyOneButtonDialog("连接错误"), woid -> {
                    try {
                        receiptVO.setReceiptState(ReceiptState.APPROVED);
                        getCheckInfo().approve(receiptVO);
                        return true;
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                        return false;
                    }
                })).start();
            });

            this.refresh(false);
        }, dialog, woid -> {
            try {
                TV redCreditVO = getReceiptblService().getNew();
                redCreditVO.setOperatorId(UserInfomation.userid);
                setRedCredit(redCreditVO);
                redCreditVO.setReceiptState(ReceiptState.DRAFT);
                getReceiptblService().update(redCreditVO);

                receiptVO = redCreditVO; //

                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })).start();
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
            reset();
        }, dialog, woid -> {
            try {
                if(logbl==null){
                    try{
                        logbl = new Logbl();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if (isFirst) { // 这说明肯定是第一次
                    isFirst = false;
                    if (receiptVO == null) {
                        receiptVO = getReceiptblService().getNew();

                        // TODO 应该只有这里需要set一次（而且这个set应该写在bl里面）
                        receiptVO.setOperatorId(UserInfomation.userid);
                    } else {
                        receiptVO = getReceiptblService().selectByMold(receiptVO);
                        MyServiceFactory.getReceiptMessageblService().deleteByReceipt(receiptVO);
                    }
                } else {
                    receiptVO = getReceiptblService().selectByMold(receiptVO);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(task).start();
    }

    /**
     * private methods
     */
    private ReceiptblService<TV> getReceiptblService() {
        return MyblServiceFactory.getService(getServiceClass());
    }

    private CheckInfo<TV> getCheckInfo() throws RemoteException {
        try {
            return receiptVO.getService();
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
