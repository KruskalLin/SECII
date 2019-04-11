package vo.inventoryVO.goodsTreeTableView;

import com.jfoenix.controls.JFXPopup;
import ui.common.popupList.MyPopUpListView;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsDetailPane;
import ui.inventoryui.myGoodsClassificationUI.label.*;
import vo.inventoryVO.GoodsVO;

public class GoodsTreeTableViewVO extends AbstractGoodsTreeTableViewVO {
    private GoodsVO goodsVO;
    public GoodsTreeTableViewVO(GoodsVO goodsVO) {
        this.goodsVO = goodsVO;
    }

    @Override
    public String getName() {
        return goodsVO.getGoodName();
    }

    @Override
    public String getType() {
        return goodsVO.getGoodType();
    }

    @Override
    public String getNum() {
        return String.valueOf(goodsVO.getInventoryNum());
    }

    @Override
    public String getPurPrice() {
        return String.valueOf(goodsVO.getPurPrice());
    }

    @Override
    public String getSalePrice() {
        return String.valueOf(goodsVO.getSalePrice());
    }

    @Override
    public String getAlarmNumber() {
        return String.valueOf(goodsVO.getAlarmNumber());
    }

    @Override
    public MyPopUpListView clickSecondaryPopUpList(JFXPopup popup) {
        MyPopUpListView result = new MyPopUpListView(popup);

        result.getItems().add(new ModifyGoodsLabel(goodsVO));
        result.getItems().add(new DeleteGoodsLabel(goodsVO));

        return result;
    }

    @Override
    public void clickTwiceAftermath() {
        // TODO getGoodsDetailPane
        new MyGoodsDetailPane(goodsVO).refresh(true);
    }
}
