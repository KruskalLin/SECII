package ui.salesui;

import com.jfoenix.controls.JFXListCell;
import vo.inventoryVO.GoodsVO;

public class GoodsChooseCell  extends JFXListCell<GoodsVO> {
    private GoodsChooseHBox goodsChooseHBox;
    @Override
    public synchronized void updateItem(GoodsVO item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
        { goodsChooseHBox = new GoodsChooseHBox();
        goodsChooseHBox.setType(item.getGoodType());
        goodsChooseHBox.setGoodsId(item.getId());
        goodsChooseHBox.setName(item.getGoodName());
        goodsChooseHBox.setPrice(item.getSalePrice()+"");
        goodsChooseHBox.setNum(item.getInventoryNum()+"");
        setGraphic(goodsChooseHBox);
        }else{
            setGraphic(null);
        }

    }
}