package businesslogic.inventorybl.inventoryInfo;

import po.GoodsPO;
import vo.ListGoodsItemVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.rmi.RemoteException;
import java.util.List;

public interface InventoryGiftReceiptInfo {
    /**
     * 当销售人员的单据完成审批后，要调用这个方法生成相应的库存赠送单，并交审批
     * @param goodsList
     */
    public void addInventoryGiftReceipt(List<PromotionGoodsItemVO> goodsList) throws RemoteException;
}
