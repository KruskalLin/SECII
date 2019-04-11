package businesslogic.stockbl;

import blService.memberblService.MemberInfo;
import blService.stockblService.StockRetblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import businesslogic.memberbl.MemberInfo_Impl;
import po.BusinessConditionPO;
import po.receiptPO.StockRetReceiptPO;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StockRetbl extends Receiptbl<StockRetReceiptVO, StockRetReceiptPO> implements StockRetblService {
    public StockRetbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockRetReceiptVO.class, StockRetReceiptPO.class);
    }

//    @Override
//    public ResultMessage approve(StockRetReceiptVO receiptVO) throws RemoteException {
//        try {
//            new GoodsSalesUpdate().goodsUpdateStorckRet(receiptVO.getItems());
//
//            MemberInfo memberInfo = new MemberInfo_Impl();
//            memberInfo.updateDebt(receiptVO.getMemberId(),receiptVO.getSum());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return ResultMessage.SUCCESS;
//    }

    @Override
    protected void updateInfoAfterApproval(StockRetReceiptVO receiptVO) throws RemoteException {
        try {
            // update member
            new GoodsSalesUpdate().goodsUpdateStorckRet(receiptVO.getItems());
            MemberInfo memberInfo = new MemberInfo_Impl();
            memberInfo.updateDebt(receiptVO.getMemberId(),receiptVO.getSum());


            // insert businessConditionPO
            BusinessConditionPO bpo = new BusinessConditionPO();
            bpo.setDate(receiptVO.getLastModifiedTime());

            double income = 0;
            for (ListGoodsItemVO listGoodsItemVO : receiptVO.getItems()) {
                GoodsVO gvo = MyServiceFactory.getGoodsblService().showDetail(listGoodsItemVO.getGoodsId());
                income += listGoodsItemVO.getGoodsNum() * (listGoodsItemVO.getPrice() - gvo.getRecentPurPrice());
            }

            bpo.setPriceDiffIncome(income);
            MyServiceFactory.getBusinessConditionInfo().insert(bpo);
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
