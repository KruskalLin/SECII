package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.PromotionblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import exceptions.ItemNotFoundException;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ui.chooseGoods.ChooseGoodsDialog;
import ui.common.BoardController;
import ui.inventoryui.goodsui.GoodChoose;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.managerui.promotionui.GoodsTreeTable;
import ui.util.GetTask;
import ui.util.RefreshablePane;
import util.PromotionState;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class PromotionDetailPane<T extends PromotionVO> extends RefreshablePane {
    @FXML
    protected GoodsTreeTable goodsTreeTable;
    @FXML
    protected Label idLabel;
    @FXML
    protected JFXButton modify, reset, save, saveAsDraft;
    @FXML
    protected MaterialDesignIconView pen;
    @FXML
    protected JFXDatePicker beginTimePicker, endTimePicker;
    @FXML
    protected TextArea commentArea; // TODO 设字数限制
    @FXML
    protected JFXRippler add;

    protected T promotionVO;
    protected PromotionblService<T> promotionblService;
    protected BooleanProperty modifyState = new SimpleBooleanProperty(true);


    protected ObservableList<PromotionGoodsItemVO> observableList = FXCollections.observableArrayList();

    /**
     * Constructors related
     */

    protected PromotionDetailPane() {
        initialize();
    }

    // 现在这样就是必须第一次new的时候call完这个就call refresh。虽然感觉这样有点问题
    protected PromotionDetailPane(T promotionVO) {
        initialize();
        this.promotionVO = promotionVO;

//        modifyState.set(false); // 这句不知道什么就会报错

        if (promotionVO.getPromotionState() == PromotionState.SAVED) {
            saveAsDraft.setVisible(false);
            reset.setLayoutX(saveAsDraft.getLayoutX());
        }
    }

    protected void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        modifyState.addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                modify.setBackground(new Background(new BackgroundFill(Color.valueOf("#03A9F4"), modify.getBackground().getFills().get(0).getRadii(), null)));
                pen.setFill(Paint.valueOf("#FFFFFF"));
            } else {
                modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));
                pen.setFill(Paint.valueOf("#000000"));
            }
        }));
        beginTimePicker.disableProperty().bind(modifyState.not());
        endTimePicker.disableProperty().bind(modifyState.not());
        commentArea.disableProperty().bind(modifyState.not());
        add.visibleProperty().bind(modifyState);
        save.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);
    }

    /**
     * abstract
     */
    protected abstract Class<? extends PromotionblService<T>> getServiceClass();

    protected abstract String getURL();


    protected boolean validate() {
        if (beginTimePicker.getValue().compareTo(endTimePicker.getValue()) >= 0) {
            new MyOneButtonDialog("策略开时时间应该早于结束时间").show();
            return false;
        }
        return true;
    }

    protected void updatePromotionVO() {
        promotionVO.setBeginTime(LocalDateTime.of(beginTimePicker.getValue(), LocalTime.MIN));
        promotionVO.setEndTime(LocalDateTime.of(endTimePicker.getValue(), LocalTime.MIN));
        promotionVO.setComment(commentArea.getText());
    }

    @FXML
    private void save() {
        if (validate()) {
            promotionVO.setPromotionState(PromotionState.SAVED);
            saveTask();
        }
    }

    @FXML
    private void saveAsDraft() {
        saveTask();
    }

    protected void saveTask() {
        updatePromotionVO();

        BoardController boardController = BoardController.getBoardController();
        boardController.Loading();

        StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
        new Thread(new GetTask(() -> {
            new MyTwoButtonDialog("保存成功", boardController::goBack).show(); // 这个和delete不一样
        }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
            try {
                promotionblService.update(promotionVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("策略不存在，是否返回单据列表");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("连接错误");
                return false;
            }
        })).start();
    }

    @FXML
    protected void addGift() {
        new ChooseGoodsDialog((g) -> {
            if (observableList.stream().anyMatch(gi -> gi.getId().equals(g.getId()))) {
                new MyOneButtonDialog("不可重复添加").show();
            } else {
                observableList.add(new PromotionGoodsItemVO(g));
            }
        }).show();
    }

    @FXML
    private void modify() {
        modifyState.set(!modifyState.get());
    }

    @FXML
    private void delete() {
        if (LocalDate.now().isAfter(beginTimePicker.getValue()) && LocalDate.now().isBefore(endTimePicker.getValue())) {
            new MyTwoButtonDialog("该促销策略正在生效期，请确认删除", this::deleteTask).show();
        } else {
            new MyTwoButtonDialog("请确认删除", this::deleteTask).show();
        }
    }

    private void deleteTask() {
        BoardController boardController = BoardController.getBoardController();
        boardController.Loading();

        StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
        new Thread(new GetTask(() -> {
            new MyOneButtonDialog("删除成功", boardController::goBack).show();
        }, new MyTwoButtonDialog(prompt, boardController::goBack), woid -> {
            try {
                promotionblService.delete(promotionVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("策略不存在，是否返回列表");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("连接错误");
                return false;
            }
        })).start();
    }

    @FXML
    protected void reset() {
//        System.out.println("PromotionDetail pane reset called");
        idLabel.setText(promotionVO.getId());
        if (promotionVO.getBeginTime() != null) {
            beginTimePicker.setValue(promotionVO.getBeginTime().toLocalDate());
        }
        if (promotionVO.getEndTime() != null) {
            endTimePicker.setValue(promotionVO.getEndTime().toLocalDate());
        }
        commentArea.setText(promotionVO.getComment());

    }

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
                if (promotionblService == null) { // 这说明肯定是第一次
                    promotionblService = MyblServiceFactory.getService(getServiceClass());
                    if (promotionVO == null) {
                        promotionVO = promotionblService.getNew();
                    }
                }
                return true; // TODO 如果不是第一次refresh目前就是什么都不做，但是好像是希望去数据库刷新
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(task).start();
    }
}
