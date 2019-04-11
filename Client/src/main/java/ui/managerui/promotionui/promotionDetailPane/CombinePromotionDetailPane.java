package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.CombinePromotionblService;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import ui.common.dialog.MyOneButtonDialog;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.util.ArrayList;

import static ui.util.ValidatorDecorator.*;

public class CombinePromotionDetailPane extends PromotionDetailPane<CombinePromotionVO> {
    @FXML
    private JFXTextField discountField;

    @Override
    protected String getURL() {
        return "/managerui/combinePromotionDetailPane.fxml";
    }

    @Override
    protected Class<CombinePromotionblService> getServiceClass() {
        return CombinePromotionblService.class;
    }

    public CombinePromotionDetailPane(CombinePromotionVO promotionVO) {
        super(promotionVO);
    }

    public CombinePromotionDetailPane() {
    }

    protected void initialize() {
        super.initialize();
        discountField.disableProperty().bind(modifyState.not());

        DoubleValid(discountField);
    }

    @Override
    protected boolean validate() {
        if (super.validate()) {
            if (observableList.isEmpty()) {
                new MyOneButtonDialog("请选择组合品").show();
                return false;
            }
            for (PromotionGoodsItemVO promotionGoodsItemVO : observableList) {
                if (promotionGoodsItemVO.getNum() == 0) {
                    new MyOneButtonDialog("商品数量不可为0").show();
                    return false;
                }
            }
            if (!discountField.validate() || Double.parseDouble(discountField.getText()) <= 0) {
                new MyOneButtonDialog("折让金额必须为正数").show();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void updatePromotionVO() {
        super.updatePromotionVO();
        promotionVO.setDiscountAmount(Double.parseDouble(discountField.getText()));
        promotionVO.setGoodsCombination(new ArrayList<>(observableList));
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        discountField.setText(String.valueOf(promotionVO.getDiscountAmount()));
        if (promotionVO.getGoodsCombination() != null) {
            goodsTreeTable.setAll(observableList = FXCollections.observableArrayList(promotionVO.getGoodsCombination()));
        } else {
            goodsTreeTable.setAll(observableList = FXCollections.observableArrayList());
        }
    }
}
