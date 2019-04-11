package ui.inventoryui.goodsui;

import blService.goodsblService.GoodsblService;
import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.common.BoardController;
import ui.util.*;
import vo.inventoryVO.GoodsVO;


import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.RequireValid;

public class GoodDetailPane extends ReceiptDetailPane<GoodsVO> {

    String goodId = "-1";

    GoodsblService goodsblService;

    private String classifyId;

    private int order;

    @FXML
    JFXTextField goodName;
    @FXML
    TextField goodTextId;
    @FXML
    TextField goodType;
    @FXML
    TextField inventoryNum;
    @FXML
    TextField classifyTextId;
    @FXML
    TextField alarmNum;
    @FXML
    TextField purPrice;
    @FXML
    TextField salePrice;
    @FXML
    TextField recentPurPrice;
    @FXML
    TextField recentSalePrice;

    @FXML
    JFXButton reset;

    @FXML
    Label date;

    GoodsVO goodsVO;

    public GoodDetailPane(String id) throws RemoteException, NotBoundException, MalformedURLException {
        this(false);
        this.goodId = id;

        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });

      //  goodName.disableProperty().bind(modifyState.not());


        reset.visibleProperty().bind(modifyState);
        save.visibleProperty().bind(modifyState);
    }

    public void setGoodId(String goodId){
        this.goodId = goodId;
    }

    public String getGoodId() {
        return goodId;
    }

    public GoodDetailPane(GoodsVO vo) throws RemoteException, NotBoundException, MalformedURLException {
        this(false);
        this.goodsVO = vo;

        this.goodId = vo.getId();

        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });

        reset.visibleProperty().bind(modifyState);
        save.visibleProperty().bind(modifyState);
    }

    public GoodDetailPane(boolean isAdd) throws RemoteException, NotBoundException, MalformedURLException {
        super("/inventoryui/goodui/goodsdetail.fxml");
        this.goodsblService = new Goodsbl();


        goodTextId.setDisable(true);
        classifyTextId.setDisable(true);

        delete.setVisible(false);
        date.setText(LocalDate.now().toString());

        RequireValid(goodName);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
//            switchPane(true);
            BoardController.getBoardController().switchTo(this);
        }

        reset.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();

            });
            textFieldPane.save(() -> {
                dialog.close();
            });
            textFieldPane.setPrompt("Password");
            dialog.show();
        });

    }

    public void setGoodsblService(GoodsblService goodsblService) {
        this.goodsblService = goodsblService;
    }

    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "删除商品", "确定删除？", "确定", "取消");
        doubleButtonDialog.setButtonOne(() -> {
            GoodsVO goodsVO = new GoodsVO();
            goodsVO.setId(goodTextId.getText());
            goodsVO.setClassifyId(classifyTextId.getText());

            try {
                goodsblService.deleteGoods(goodsVO);
                BoardController.getBoardController().goBack();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        doubleButtonDialog.setButtonTwo(() -> {
            BoardController.getBoardController().goBack();
        });
        doubleButtonDialog.show();
    }

    @Override
    public void savePendingReceipt() {

    }

    @Override
    public void saveDraftReceipt() {

    }

    @Override
    public boolean valid() {
        return true;
    }

    @Override
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "增加/更新商品", "确定增加吗/更新", "是", "否");
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.setButtonOne(() -> {
                if (goodId.equals("-1")) {
                    try {
                        goodId = goodsblService.getID(classifyId,order);
                        goodTextId.setText(goodId);
                        goodsblService.addGoods(new GoodsVO(goodId, goodName.getText(), goodType.getText(), classifyId,
                                Integer.parseInt(inventoryNum.getText()),
                                Double.parseDouble(purPrice.getText()),
                                Double.parseDouble(salePrice.getText()),
                                Double.parseDouble(recentPurPrice.getText()),
                                Double.parseDouble(recentSalePrice.getText()), Integer.parseInt(alarmNum.getText()),0));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        goodsblService.updateGoods(new GoodsVO(goodId, goodName.getText(), goodType.getText(), classifyTextId.getText(),
                                Integer.parseInt(inventoryNum.getText()),
                                Double.parseDouble(purPrice.getText()),
                                Double.parseDouble(salePrice.getText()),
                                Double.parseDouble(recentPurPrice.getText()),
                                Double.parseDouble(recentSalePrice.getText()),
                                Integer.parseInt(alarmNum.getText()),0));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                BoardController.getBoardController().goBack();
            });

            doubleButtonDialog.show();

        } else {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "???", "Stupid!", "Accept");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            if (!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> boardController.Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
                    try {
                        if ((vo = goodsblService.showDetail(goodId)) != null)
                            return true;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            goodName.setText(vo.getGoodName());
                            goodTextId.setText(vo.getId());
                            classifyTextId.setText(vo.getClassifyId());
                            goodType.setText(vo.getGoodType());
                            inventoryNum.setText(String.valueOf(vo.getInventoryNum()));
                            salePrice.setText(String.valueOf(vo.getSalePrice()));
                            purPrice.setText(String.valueOf(vo.getPurPrice()));
                            recentPurPrice.setText(String.valueOf(vo.getRecentPurPrice()));
                            recentSalePrice.setText(String.valueOf(vo.getRecentSalePrice()));
                            alarmNum.setText(String.valueOf(vo.getAlarmNumber()));

                            BoardController.getBoardController().switchTo(this);
                        }, buttonDialog, p);

                new Thread(task).start();
            } else {
//                switchPane(toSwitch);
                BoardController.getBoardController().switchTo(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
