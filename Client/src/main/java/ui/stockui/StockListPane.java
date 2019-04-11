package ui.stockui;

import ui.common.bigPane.MyReceiptListPane;
import vo.receiptVO.StockReceiptListVO;
import vo.receiptVO.StockReceiptVO;

/**
 * @Author: Lin Yuchao
 * @Description: listpane  注意所有见面跳转职责都分给自己，要跳到谁，由后者自己改，因为刷新也是这样
 * @ModifyBy: Lin Yuchao
**/

public abstract class StockListPane<T extends StockReceiptListVO<T>,S extends StockReceiptVO> extends MyReceiptListPane<T,S> {

}




