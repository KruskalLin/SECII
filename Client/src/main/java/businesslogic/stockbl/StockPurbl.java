package businesslogic.stockbl;

import blService.memberblService.MemberInfo;
import blService.stockblService.StockPurblService;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import businesslogic.memberbl.MemberInfo_Impl;
import po.BusinessConditionPO;
import po.receiptPO.StockPurReceiptPO;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.receiptVO.StockPurReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StockPurbl extends Receiptbl<StockPurReceiptVO, StockPurReceiptPO> implements StockPurblService {
    public StockPurbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockPurReceiptVO.class, StockPurReceiptPO.class);
    }

//    @Override
//    public ResultMessage approve(StockPurReceiptVO receiptVO) throws RemoteException{
//        try {
//            new GoodsSalesUpdate().goodsUpdateStockPur(receiptVO.getItems());
//
//            MemberInfo memberInfo = new MemberInfo_Impl();
//            memberInfo.updateCredit(receiptVO.getMemberId(),receiptVO.getSum());
//
//            BusinessConditionbl businessConditionbl = new BusinessConditionbl();
//            BusinessConditionPO businessConditionPO = new BusinessConditionPO();
//            businessConditionPO.setPurCost(receiptVO.getSum());
//
//            Goodsbl goodsbl = new Goodsbl();
//            double sum = 0;
//            for(ListGoodsItemVO listGoodsItemVO:receiptVO.getItems()){
//                GoodsVO goodsVO = goodsbl.showDetail(listGoodsItemVO.getGoodsId());
//                sum+= goodsVO.getInventoryNum()*(goodsVO.getPurPrice()-listGoodsItemVO.getPrice());
//            }
//
//            businessConditionPO.setPurPriceAdjustIncome(sum);
//            businessConditionbl.insert(businessConditionPO);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return ResultMessage.SUCCESS;
//    }

    @Override
    protected void updateInfoAfterApproval(StockPurReceiptVO receiptVO) throws RemoteException {
        try {
            new GoodsSalesUpdate().goodsUpdateStockPur(receiptVO.getItems());

            MemberInfo memberInfo = new MemberInfo_Impl();
            memberInfo.updateCredit(receiptVO.getMemberId(), receiptVO.getSum());

            BusinessConditionbl businessConditionbl = new BusinessConditionbl();
            BusinessConditionPO businessConditionPO = new BusinessConditionPO();
            businessConditionPO.setDate(receiptVO.getLastModifiedTime());

            businessConditionPO.setPurCost(receiptVO.getSum());

            Goodsbl goodsbl = null;

            goodsbl = new Goodsbl();
            double sum = 0;
            for (ListGoodsItemVO listGoodsItemVO : receiptVO.getItems()) {
                GoodsVO goodsVO = goodsbl.showDetail(listGoodsItemVO.getGoodsId());
                if (goodsVO.getRecentPurPrice() == 0) { // 第一次如果也算进去那么太可怕了。
                    continue;
                }
                sum += goodsVO.getInventoryNum() * (goodsVO.getPurPrice() - listGoodsItemVO.getPrice());
            }

            businessConditionPO.setPurPriceAdjustIncome(sum);
            businessConditionbl.insert(businessConditionPO);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
