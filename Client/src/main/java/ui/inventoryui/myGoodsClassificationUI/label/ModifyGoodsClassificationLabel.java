package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsClassificationModifyDialog;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

public class ModifyGoodsClassificationLabel extends PopUpListLabel implements FXMLLoadableMixer {
    private RecursiveGoodsClassificationVO goodsClassificationVO;

    public ModifyGoodsClassificationLabel(RecursiveGoodsClassificationVO goodsClassificationVO) {
        this.goodsClassificationVO = goodsClassificationVO;
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myModifyGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
        new MyGoodsClassificationModifyDialog(goodsClassificationVO).show();
    }
}
