package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.PromotionblService;
import blService.promotionblService.TotalPromotionblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.*;
import com.jfoenix.validation.NumberValidator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import ui.common.dialog.MyOneButtonDialog;
import vo.inventoryVO.GoodsVO;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.TotalPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static ui.util.ValidatorDecorator.*;

public class TotalPromotionDetailPane extends PromotionDetailPane<TotalPromotionVO> {
    @FXML
    private JFXTextField totalField, tokenField;


    @Override
    protected String getURL() {
        return "/managerui/totalPromotionDetailPane.fxml";
    }

    @Override
    protected Class<? extends PromotionblService<TotalPromotionVO>> getServiceClass() {
        return TotalPromotionblService.class;
    }

    public TotalPromotionDetailPane(TotalPromotionVO promotionVO) {
        super(promotionVO);
    }

    public TotalPromotionDetailPane() {
    }

    @Override
    protected void initialize() {
        super.initialize();

        totalField.disableProperty().bind(modifyState.not());
        tokenField.disableProperty().bind(modifyState.not());

        DoubleValid(totalField);
        DoubleValid(tokenField);
    }

    @Override
    protected boolean validate() {
        if (super.validate()) {
            if (! totalField.validate() || Double.parseDouble(totalField.getText()) <= 0) {
                new MyOneButtonDialog("请填写正确总价").show();
                return false;
            }
            if (!tokenField.validate() || Double.parseDouble(tokenField.getText()) < 0) {
                new MyOneButtonDialog("请填写正确代金券金额").show();
                return false;
            }

            int effect = 0;
            if (Double.parseDouble(tokenField.getText()) > 0) {
                effect++;
            }
            if (!observableList.isEmpty()) {
                effect++;
            }
            if (effect == 0) {
                new MyOneButtonDialog("不可制定无任何效果的促销策略").show();
                return false;
            }
            if (effect > 1) {
                new MyOneButtonDialog("不可制定多重效果的促销策略").show();
                return false;
            }

//            if (Double.parseDouble(tokenField.getText()) == 0 && observableList.isEmpty()) {
//                new MyOneButtonDialog("不可制定无任何效果的促销策略").show();
//                return false;
//            }

            for (PromotionGoodsItemVO promotionGoodsItemVO : observableList) {
                if (promotionGoodsItemVO.getNum() == 0) {
                    new MyOneButtonDialog("赠品数量不可为0").show();
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected void updatePromotionVO() {
        super.updatePromotionVO();
        promotionVO.setRequiredTotal(Double.parseDouble(totalField.getText()));
        promotionVO.setTokenAmount(Double.parseDouble(tokenField.getText()));
        promotionVO.setGifts(new ArrayList<>(observableList));
    }


    @FXML
    @Override
    protected void reset() {
        super.reset();
        totalField.setText(String.valueOf(promotionVO.getRequiredTotal()));
        tokenField.setText(String.valueOf(promotionVO.getTokenAmount()));
        if (promotionVO.getGifts() != null) {
            goodsTreeTable.setAll(observableList = FXCollections.observableArrayList(promotionVO.getGifts()));
        } else {
            goodsTreeTable.setAll(observableList = FXCollections.observableArrayList());
        }
    }


}
