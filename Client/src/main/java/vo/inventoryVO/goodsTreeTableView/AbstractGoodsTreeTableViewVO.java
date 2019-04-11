package vo.inventoryVO.goodsTreeTableView;

import com.jfoenix.controls.JFXPopup;
import ui.common.popupList.MyPopUpListView;

public abstract class AbstractGoodsTreeTableViewVO {
    public abstract String getName();

    public abstract String getType();

    public abstract String getNum();

    public abstract String getPurPrice();

    public abstract String getSalePrice();

    public abstract String getAlarmNumber();

    public abstract MyPopUpListView clickSecondaryPopUpList(JFXPopup popup);

    public void clickTwiceAftermath() {
    }

}
