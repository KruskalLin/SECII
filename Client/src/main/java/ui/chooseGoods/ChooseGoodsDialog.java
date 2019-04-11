package ui.chooseGoods;

import com.jfoenix.controls.JFXDialog;
import ui.salesui.GoodsList;
import ui.util.PaneFactory;
import vo.inventoryVO.GoodsVO;

import java.util.function.Consumer;

public class ChooseGoodsDialog extends JFXDialog {
    public ChooseGoodsDialog(Consumer<GoodsVO> clickAction) {
        MyGoodsList goodsList = new MyGoodsList(clickAction);

        this.setContent(goodsList);
        this.setDialogContainer(PaneFactory.getMainPane());
        this.setTransitionType(JFXDialog.DialogTransition.CENTER);
    }
}
