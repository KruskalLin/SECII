package ui.mainui;

import com.jfoenix.controls.JFXListCell;
import ui.salesui.GoodsChooseHBox;
import util.ReceiptState;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

public class GiveGoodsCell extends JFXListCell<ReceiptGoodsItemVO> {
    private GiveGoodsHBox giveGoodsHBox;
    @Override
    public synchronized void updateItem(ReceiptGoodsItemVO item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
        {   giveGoodsHBox = new GiveGoodsHBox();
            giveGoodsHBox.setGoodsId(item.getGoodsId());
            giveGoodsHBox.setName(item.getGoodsName());
            giveGoodsHBox.setNum(item.getSendNum()+"");
            setGraphic(giveGoodsHBox);
        }else{
            setGraphic(null);
        }

    }
}