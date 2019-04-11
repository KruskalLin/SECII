package ui.salesui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.common.bigPane.FXMLAnchorPane;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionVO;

public class PromotionDetail extends FXMLAnchorPane {

    @FXML
    Label detail;


    @Override
    protected String getURL() {
        return "/salesui/promotionDetail.fxml";
    }

    public PromotionDetail(){
        super();


    }


    public void setPromotion(PromotionVO promotion){
//        this.detail.setText(promotion.getInfoLabel().getText());
    }


}



