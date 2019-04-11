package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsDetailPane;
import vo.inventoryVO.GoodsVO;

public class ModifyGoodsLabel extends PopUpListLabel implements FXMLLoadableMixer {
    private GoodsVO goodsVO;

    public ModifyGoodsLabel(GoodsVO goodsVO) {
        this.goodsVO = goodsVO;
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myModifyGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        new MyGoodsDetailPane(goodsVO, true).refresh(false);
    }
}