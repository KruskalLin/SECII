package businesslogic.goodsbl.goodsUpdate;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import businesslogic.goodsbl.Goodsbl;
import po.GoodsPO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class GoodsInventoryUpdate implements GoodsInventoryUpdateInfo {
    Goodsbl goodsbl;

    public GoodsInventoryUpdate() throws RemoteException, NotBoundException, MalformedURLException {
        this.goodsbl = new Goodsbl();
    }

    /**
     * 用于InventoryGift审批之后自动产生赠送单（草稿状态）
     * @param list
     * @throws RemoteException
     */
    @Override
    public void goodsGiftUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException {
        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = vo.getInventoryNum()-vo.getSendNum();

            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }
    }

    @Override
    public void goodsOverflowUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException {
        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = vo.getFactNum();

            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }

    }

    @Override
    public void goodsDamageUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException {
        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = vo.getFactNum();

            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }
    }
}
