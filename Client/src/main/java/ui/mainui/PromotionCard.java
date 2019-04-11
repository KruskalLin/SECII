package ui.mainui;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import ui.common.bigPane.FXMLAnchorPane;
import ui.common.mixer.FXMLLoadableMixer;
import vo.promotionVO.PromotionVO;


public class PromotionCard extends FXMLAnchorPane{
    @FXML
    protected Label title;
    @FXML
    protected Label num;

    @FXML
    public JFXButton choose;

    private PromotionVO promotionVO;

    public PromotionCard() {
    }

    public PromotionCard(PromotionVO promotionVO){
        this.promotionVO = promotionVO;
    }

    @Override
    protected String getURL() {
        return "/salesui/promotionCard.fxml";
    }

    public PromotionVO getPromotionVO() {
        return promotionVO;
    }
}