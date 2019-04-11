package businesslogic.salesbl;

import blService.memberblService.MemberInfo;
import blService.salesblService.SalesRetblService;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import businesslogic.memberbl.MemberInfo_Impl;
import po.receiptPO.SalesRetReceiptPO;
import util.ResultMessage;
import vo.receiptVO.SalesRetReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SalesRetbl extends Receiptbl<SalesRetReceiptVO, SalesRetReceiptPO> implements SalesRetblService {

    public SalesRetbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesRetReceiptVO.class, SalesRetReceiptPO.class);
    }



//    @Override
//    public ResultMessage approve(SalesRetReceiptVO receiptVO) throws RemoteException {
//        try {
//            new GoodsSalesUpdate().goodsUpdateSaleRet(receiptVO.getItems());
//            MemberInfo memberInfo = new MemberInfo_Impl();
//            memberInfo.updateCredit(receiptVO.getMemberId(),receiptVO.getOriginSum()-receiptVO.getDiscountAmount());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return ResultMessage.SUCCESS;
//    }

    @Override
    protected void updateInfoAfterApproval(SalesRetReceiptVO receiptVO) throws RemoteException {

        try {
            new GoodsSalesUpdate().goodsUpdateSaleRet(receiptVO.getItems());
            MemberInfo memberInfo = new MemberInfo_Impl();
            memberInfo.updateCredit(receiptVO.getMemberId(),receiptVO.getOriginSum()-receiptVO.getDiscountAmount());


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

