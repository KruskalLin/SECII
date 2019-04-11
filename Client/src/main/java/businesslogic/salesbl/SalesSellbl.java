package businesslogic.salesbl;

import blService.memberblService.MemberInfo;
import blService.salesblService.SalesSellblService;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import businesslogic.inventorybl.inventoryInfo.GiftReceiptInfoImpl;
import businesslogic.inventorybl.inventoryInfo.InventoryGiftReceiptInfo;
import businesslogic.memberbl.MemberInfo_Impl;
import po.BusinessConditionPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SalesSellbl extends Receiptbl<SalesSellReceiptVO, SalesSellReceiptPO> implements SalesSellblService {


    public SalesSellbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesSellReceiptVO.class, SalesSellReceiptPO.class);
    }

    @Override
    protected void updateInfoAfterApproval(SalesSellReceiptVO receiptVO) throws RemoteException {
        try {
            new GoodsSalesUpdate().goodsUpdateSalesSel(receiptVO.getItems());

            if (receiptVO.getGifts() != null) {
                InventoryGiftReceiptInfo inventoryGiftReceiptInfo = new GiftReceiptInfoImpl();
                ArrayList<PromotionGoodsItemVO> gifts = Arrays.stream(receiptVO.getGifts()).map(PromotionGoodsItemVO::new).collect(Collectors.toCollection(ArrayList::new));
                inventoryGiftReceiptInfo.addInventoryGiftReceipt(gifts);
            }

            BusinessConditionbl businessConditionbl = new BusinessConditionbl();
            BusinessConditionPO businessConditionPO = new BusinessConditionPO();
            businessConditionPO.setDate(receiptVO.getLastModifiedTime());

            businessConditionPO.setSalesIncome(receiptVO.getOriginSum() - receiptVO.getDiscountAmount());
            double temp = receiptVO.getGiveTokenAmount() - (receiptVO.getOriginSum() - receiptVO.getDiscountAmount());
            businessConditionPO.setTokenIncome(temp > 0 ? temp : 0);
            businessConditionPO.setDiscount(receiptVO.getDiscountAmount());


            businessConditionbl.insert(businessConditionPO);

            MemberInfo memberInfo = new MemberInfo_Impl();
            memberInfo.updateDebt(receiptVO.getMemberId(), receiptVO.getOriginSum() - receiptVO.getDiscountAmount());

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
