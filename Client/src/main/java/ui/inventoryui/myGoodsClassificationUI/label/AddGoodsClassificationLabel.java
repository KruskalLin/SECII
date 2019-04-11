package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.dialog.MyOneButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsClassificationAddDialog;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

public class AddGoodsClassificationLabel extends PopUpListLabel implements FXMLLoadableMixer {
    private RecursiveGoodsClassificationVO goodsClassificationVO;

    public AddGoodsClassificationLabel(RecursiveGoodsClassificationVO goodsClassificationVO) {
        load();
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
//        System.out.println("add");
        if (!goodsClassificationVO.getGoods().isEmpty()) {
            new MyOneButtonDialog("当前商品分类下有商品，不可添加商品分类").show();
        } else {
//            new MyOneButtonDialog("增加商品分类的还没有做😅").show();
            new MyGoodsClassificationAddDialog(goodsClassificationVO).show();
        }
    }
}
