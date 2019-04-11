package blService.goodsblService;

import po.GoodsPO;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.rmi.RemoteException;
import java.util.List;

public interface GoodsSalesUpdateInfo {

    /**
     * 提供给销售人员单据审批之后用来修改商品属性的方法
     */

    public void goodsUpdateSaleRet(List<ListGoodsItemVO> list) throws RemoteException;

    public void goodsUpdateSalesSel(List<ListGoodsItemVO> list) throws RemoteException;

    public void goodsUpdateStockPur(List<ListGoodsItemVO> list) throws RemoteException;

    public void goodsUpdateStorckRet(List<ListGoodsItemVO> list) throws RemoteException;
}
