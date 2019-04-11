package blService.goodsblService;

import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.rmi.RemoteException;
import java.util.List;

public interface GoodsInventoryUpdateInfo {
    /**
     *
     * 提供给库存管理人员单据审批之后用来修改商品属性的方法
     * @param list
     * @throws RemoteException
     */
    public void goodsGiftUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException;

    public void goodsOverflowUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException;

    public void goodsDamageUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException;
}
