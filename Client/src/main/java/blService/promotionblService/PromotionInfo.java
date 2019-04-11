package blService.promotionblService;

import vo.promotionVO.PromotionVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionInfo {
    ArrayList<PromotionVO> getMatch(SalesSellReceiptVO salesSellReceiptVO) throws RemoteException;
}
