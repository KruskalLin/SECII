package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import blService.goodsblService.GoodsblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import exceptions.ItemNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.common.BoardController;
import ui.common.bigPane.GatePane;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.ModifyButton;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MyGoodsDetailPane extends GatePane {
    @FXML
    private JFXTextField goodName;
    @FXML
    private TextField goodTextId;
    @FXML
    private TextField goodType;
    @FXML
    private TextField inventoryNum;
    @FXML
    private TextField classifyTextId; // TODO 这个商品分类怎么能显示id呢，当然是名字了，或者是类似于路径的名称链
    @FXML
    private TextField alarmNum;
    @FXML
    private TextField purPrice;
    @FXML
    private TextField salePrice;
    @FXML
    private TextField recentPurPrice;
    @FXML
    private TextField recentSalePrice;

    @FXML
    private JFXButton save;
    @FXML
    private JFXButton reject;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton reset;

    @FXML
    protected ModifyButton modify;

    @FXML
    private Label date;

//    private GoodsblService goodsblService;
    private GoodsVO vo;

    private boolean isAdd;
    private RecursiveGoodsClassificationVO recursiveGoodsClassificationVO;

    // 这个是查看的构造方法
    public MyGoodsDetailPane(GoodsVO goodsVO) {
        this.vo = goodsVO;
        isAdd = false;

        initiate();
    }

    public MyGoodsDetailPane(GoodsVO goodsVO, boolean isModify) {
        this(goodsVO);
        modify.setModify(isModify);
    }

    // 这个是新建的构造方法
    public MyGoodsDetailPane(RecursiveGoodsClassificationVO recursiveGoodsClassificationVO) {
        isAdd = true;
        this.recursiveGoodsClassificationVO = recursiveGoodsClassificationVO;
        this.vo = new GoodsVO();

        vo.setClassifyId(recursiveGoodsClassificationVO.getId());
        goodTextId.setPromptText("将在新建后自动生成");

        modify.setModify(true);

        initiate();
    }

    private void initiate() {
        goodTextId.setDisable(true);
        recentPurPrice.setDisable(true);
        recentSalePrice.setDisable(true);
        classifyTextId.setDisable(true);

        reset.visibleProperty().bind(modify.modifyProperty());
        save.visibleProperty().bind(modify.modifyProperty());
        goodName.disableProperty().bind(modify.modifyProperty().not());
        goodType.disableProperty().bind(modify.modifyProperty().not());
        alarmNum.disableProperty().bind(modify.modifyProperty().not());
        purPrice.disableProperty().bind(modify.modifyProperty().not());
        salePrice.disableProperty().bind(modify.modifyProperty().not());
        inventoryNum.disableProperty().bind(modify.modifyProperty().not());
    }

    @Override
    protected void refreshAfterMath() {
        reset();
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
//        goodsblService = MyblServiceFactory.getService(GoodsblService.class);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        if (!isAdd) {
            vo = getService().showDetail(vo.getId());
        }
    }

    @Override
    protected String getURL() {
        return "/inventoryui/goodui/myGoodsDetailPane.fxml";
    }

    /**
     * FXML
     */

    @FXML
    private void reset() {
        // TODO 汉乙写的reset不懂什么意思
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
    }

    @FXML
    private void delete() {
        if (isAdd) {
            new MyTwoButtonDialog("是否放弃新建？", () -> {
                BoardController.getBoardController().goBackAndNeverReturn();
            }).show();
        } else {
            if (vo.getRecentPurPrice() != 0) {
                new MyOneButtonDialog("已购买过该高品，不可删除").show();
            } else {
                new MyTwoButtonDialog("请确认删除", () -> {
                    BoardController boardController = BoardController.getBoardController();
                    boardController.Loading();

                    StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
                    new Thread(new GetTask(() -> {
                        new MyOneButtonDialog("删除成功", boardController::goBack).show();
                    }, new MyTwoButtonDialog(prompt, boardController::goBack), woid -> {
                        try {
                            getService().deleteGoods(vo);
                            return true;
                        } catch (ItemNotFoundException e) {
                            e.printStackTrace();
                            prompt.set("商品不存在，是否返回列表");
                            return false;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            prompt.set("连接错误");
                            return false;
                        }
                    })).start();
                }).show();
            }
        }
    }

    @FXML
    private void save() {
        if (validate()) {
            updateVO();

            BoardController boardController = BoardController.getBoardController();
            boardController.Loading();

            StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
            new Thread(new GetTask(() -> {
                new MyTwoButtonDialog("保存成功", boardController::goBack).show(); // 这个和delete不一样
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    if (isAdd) {
                        vo.setId(recursiveGoodsClassificationVO.getNextGoodsId());
                        recursiveGoodsClassificationVO.getGoods().add(vo);

                        getService().addGoods(vo);

                        // TODO 又一次深深意识到bl干的事情实在太少了
                        MyGoodsClassificationblService goodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
                        goodsClassificationblService.update(recursiveGoodsClassificationVO);
                    } else {
                        getService().updateGoods(vo);
                    }
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("商品不存在，是否返回商品总界面");
                    return false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    prompt.set("连接错误");
                    return false;
                }
            })).start();
        } else {
            // TODO show alert box
        }
    }


    /**
     * private
     * */
    private boolean validate() {
        return true;
        // TODO
    }

    private void updateVO() {
        vo.setGoodName(goodName.getText());
        vo.setGoodType(goodType.getText());
//        vo.setClassifyId(); // 这个不需要set
        vo.setInventoryNum(Integer.parseInt(inventoryNum.getText()));
        vo.setPurPrice(Double.parseDouble(purPrice.getText()));
        vo.setSalePrice(Double.parseDouble(salePrice.getText()));
//        vo.setRecentPurPrice(Double.parseDouble(recentPurPrice.getText())); // 这个感觉不是自己set的
//        vo.setRecentSalePrice(Double.parseDouble(recentSalePrice.getText())); // 这个同上
        vo.setAlarmNumber(Integer.parseInt(alarmNum.getText()));
    }

    private GoodsblService getService() {
        return ((GoodsblService)MyblServiceFactory.getService(GoodsblService.class));
    }
}
