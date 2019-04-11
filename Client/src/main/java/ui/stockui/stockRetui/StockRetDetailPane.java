package ui.stockui.stockRetui;

import blService.genericblService.ReceiptblService;
import blService.stockblService.StockRetblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import ui.common.dialog.MyOneButtonDialog;
import ui.stockui.StockReceiptPane;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.receiptVO.StockRetReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class StockRetDetailPane extends StockReceiptPane<StockRetReceiptVO> {
    public StockRetDetailPane() {
    }

    public StockRetDetailPane(StockRetReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected Class<? extends ReceiptblService<StockRetReceiptVO>> getServiceClass() {
        return StockRetblService.class;
    }

    @Override
    public boolean validate() {
        if (super.validate()) {
            try {
                for (ListGoodsItemVO listGoodsItemVO : itemTreeTable.getList()) {
                    GoodsVO goodsVO = MyServiceFactory.getGoodsblService().showDetail(listGoodsItemVO.getGoodsId());
                    if (goodsVO.getInventoryNum() < listGoodsItemVO.getGoodsNum()) {
                        new MyOneButtonDialog(listGoodsItemVO.getGoodsName() + "退货数量大于库存数量").show();
                        return false;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                new MyOneButtonDialog("连接错误").show();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void setRedCredit(StockRetReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setSum(-redCreditVO.getSum());
        ArrayList<ListGoodsItemVO> list = redCreditVO.getItems();
        for (ListGoodsItemVO vo : list) {
            vo.setSum(-vo.getSum());
        }
        redCreditVO.setItems(list);
    }

}