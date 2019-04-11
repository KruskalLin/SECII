package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.MemberPromotionblService;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import ui.common.dialog.MyOneButtonDialog;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.util.ArrayList;

import static ui.util.ValidatorDecorator.*;

public class MemberPromotionDetailPane extends PromotionDetailPane<MemberPromotionVO> {
    @FXML
    private JFXSlider requiredLevelSlider;
    @FXML
    private JFXTextField tokenField, discountField;

    /**
     * Constructor related
     */
    public MemberPromotionDetailPane() {
    }

    public MemberPromotionDetailPane(MemberPromotionVO promotionVO) {
        super(promotionVO);
    }

    @Override
    protected void initialize() {
        super.initialize();
        tokenField.disableProperty().bind(modifyState.not());
        discountField.disableProperty().bind(modifyState.not());
        requiredLevelSlider.disableProperty().bind(modifyState.not());
        requiredLevelSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            requiredLevelSlider.setValue(newValue.intValue());
        });

        DoubleValid(discountField);
        DoubleValid(tokenField);
    }

    /**
     * implement
     */
    @Override
    protected String getURL() {
        return "/managerui/memberPromotionDetailPane.fxml";
    }

    @Override
    protected Class<MemberPromotionblService> getServiceClass() {
        return MemberPromotionblService.class;
    }


    /**
     * Override
     */
    @Override
    protected boolean validate() {
        if (super.validate()) {
            if (!tokenField.validate() || Double.parseDouble(tokenField.getText()) < 0) {
                new MyOneButtonDialog("请填写正确代金券金额").show();
                return false;
            }
            if (!discountField.validate()
                    || !(0 < Double.parseDouble(discountField.getText()) && (Double.parseDouble(discountField.getText())) <= 1)) {
                new MyOneButtonDialog("请输入0~1之间的折扣").show();
                return false;
            }

            int effect = 0;
            if (Double.parseDouble(tokenField.getText()) > 0) {
                effect++;
            }
            if (Double.parseDouble(discountField.getText()) < 1) {
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
//            if (Double.parseDouble(tokenField.getText()) == 0
//                    && Double.parseDouble(discountField.getText()) == 1
//                    && observableList.isEmpty()) {
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
        promotionVO.setRequiredLevel((int) requiredLevelSlider.getValue());
        promotionVO.setTokenAmount(Double.parseDouble(tokenField.getText()));
        promotionVO.setDiscountFraction(Double.parseDouble(discountField.getText()));
        promotionVO.setGifts(new ArrayList<>(observableList));
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
//        requiredLevelField.setText(String.valueOf(promotionVO.getRequiredLevel()));
        requiredLevelSlider.setValue(promotionVO.getRequiredLevel());

        tokenField.setText(String.valueOf(promotionVO.getTokenAmount()));
        discountField.setText(String.valueOf(promotionVO.getDiscountFraction()));
        if (promotionVO.getGifts() != null) {
            goodsTreeTable.setAll(observableList = FXCollections.observableArrayList(promotionVO.getGifts()));
        } else {
            goodsTreeTable.setAll(observableList = FXCollections.observableArrayList());
        }
    }

}
