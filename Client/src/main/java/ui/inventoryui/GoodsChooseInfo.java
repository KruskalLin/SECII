package ui.inventoryui;

import javafx.collections.ObservableList;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.List;

public interface GoodsChooseInfo {

    public List<String> choose(ObservableList<ReceiptGoodsItemVO> observableList);
}
