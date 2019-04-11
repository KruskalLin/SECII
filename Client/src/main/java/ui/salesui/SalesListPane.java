package ui.salesui;

import javafx.beans.property.SimpleStringProperty;
import ui.common.bigPane.MyReceiptListPane;
import vo.receiptVO.SalesReceiptListVO;
import vo.receiptVO.SalesReceiptVO;

public abstract class SalesListPane<T extends SalesReceiptListVO<T>,S extends SalesReceiptVO> extends MyReceiptListPane<T,S> {

    private SimpleStringProperty match = new SimpleStringProperty("");

    @Override
    protected void search() {

    }


}
