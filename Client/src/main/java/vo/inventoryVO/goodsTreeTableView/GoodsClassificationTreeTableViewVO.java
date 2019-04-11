package vo.inventoryVO.goodsTreeTableView;

import com.jfoenix.controls.JFXPopup;
import ui.common.popupList.MyPopUpListView;
import ui.inventoryui.myGoodsClassificationUI.label.*;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

public class GoodsClassificationTreeTableViewVO extends AbstractGoodsTreeTableViewVO {
    private RecursiveGoodsClassificationVO goodsClassificationVO;

    public GoodsClassificationTreeTableViewVO(RecursiveGoodsClassificationVO goodsClassificationVO) {
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String getName() {
        return goodsClassificationVO.getName();
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String getNum() {
        return "";
    }

    @Override
    public String getPurPrice() {
        return "";
    }

    @Override
    public String getSalePrice() {
        return "";
    }

    @Override
    public String getAlarmNumber() {
        return "";
    }

    @Override
    public MyPopUpListView clickSecondaryPopUpList(JFXPopup popup) {
        MyPopUpListView result = new MyPopUpListView(popup);

        result.getItems().add(new AddGoodsLabel(goodsClassificationVO));
        result.getItems().add(new AddGoodsClassificationLabel(goodsClassificationVO));
        result.getItems().add(new ModifyGoodsClassificationLabel(goodsClassificationVO));
        result.getItems().add(new DeleteGoodsClassificationLabel(goodsClassificationVO));

        return result;
    }
}
