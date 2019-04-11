package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.dialog.MyOneButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsDetailPane;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

public class AddGoodsLabel extends PopUpListLabel implements FXMLLoadableMixer {
    private RecursiveGoodsClassificationVO goodsClassificationVO;

    public AddGoodsLabel(RecursiveGoodsClassificationVO goodsClassificationVO) {
        load();
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        if (!goodsClassificationVO.getChildren().isEmpty()) {
            new MyOneButtonDialog("当前商品分类下存在商品子分类，不可添加商品").show();
        } else {
            new MyGoodsDetailPane(goodsClassificationVO).refresh(true);
        }
    }
}
