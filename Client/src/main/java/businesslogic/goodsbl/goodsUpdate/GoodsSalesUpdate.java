package businesslogic.goodsbl.goodsUpdate;

import blService.goodsblService.GoodsSalesUpdateInfo;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.inventorybl.InventoryWarningReceiptbl;
import po.GoodsPO;
import ui.util.UserInfomation;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GoodsSalesUpdate implements GoodsSalesUpdateInfo {
    Goodsbl goodsbl;
    public GoodsSalesUpdate() throws RemoteException, NotBoundException, MalformedURLException {
        goodsbl = new Goodsbl();
    }


    /**
     * 用于在SaleRet审批后更新商品相关数据
     * @param list
     * @throws RemoteException
     */
    @Override
    public void goodsUpdateSaleRet(List<ListGoodsItemVO> list) throws RemoteException {

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            int inventoryNum = goodsVO.getInventoryNum()+vo.getGoodsNum();



            goodsVO.setRecentSalePrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }
    }

    /**
     * |当SaleSel得到审批之后更新相关的商品界面
     * @param list
     * @throws RemoteException
     */
    @Override
    public void goodsUpdateSalesSel(List<ListGoodsItemVO> list) throws RemoteException {

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();

            goodsVO.setRecentSalePrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);

        }
    }


    /**
     * 用于StockPur审批后更新相关商品数据
     * @param list
     * @throws RemoteException
     */
    @Override
    public void goodsUpdateStockPur(List<ListGoodsItemVO> list) throws RemoteException {
        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()+vo.getGoodsNum();

            goodsVO.setRecentPurPrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsVO.setIsStockPur(1);
            goodsbl.updateGoods(goodsVO);

        }

    }

    /**
     * 用于StockRet审批后更新相关数据
     * @param list
     * @throws RemoteException
     */
    @Override
    public void goodsUpdateStorckRet(List<ListGoodsItemVO> list) throws RemoteException {

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();


            goodsVO.setRecentPurPrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);

        }

    }



}
