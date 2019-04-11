package businesslogic.inventorybl.inventoryInfo;

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

public class WarningReceiptInfoImpl implements InventoryWarningReceiptInfo{
    InventoryWarningReceiptbl inventoryWarningReceiptbl;
    Goodsbl goodsbl;

    /*
    构造inventoryWarningReceiptbl的时候有问题
     */
    public WarningReceiptInfoImpl() throws RemoteException, NotBoundException, MalformedURLException {
        inventoryWarningReceiptbl = new InventoryWarningReceiptbl();
        goodsbl = new Goodsbl();
    }

    @Override
    public boolean checkSaleRet(List<ListGoodsItemVO> list) throws RemoteException {
        List<ReceiptGoodsItemVO> warningList = new ArrayList<>();

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            int inventoryNum = goodsVO.getInventoryNum() - vo.getGoodsNum();

            if (checkWarning(inventoryNum, goodsVO.getInventoryNum())) {
                ReceiptGoodsItemVO goodsItemVO = new ReceiptGoodsItemVO();
                goodsItemVO.setGoodsId(goodsVO.getId());
                goodsItemVO.setGoodsName(goodsVO.getGoodName());
                goodsItemVO.setInventoryNum(inventoryNum);
                goodsItemVO.setWarningNum(goodsItemVO.getWarningNum());
                warningList.add(goodsItemVO);
            }
        }

        if(warningList.size() > 0){
            InventoryWarningReceiptVO receiptVO = inventoryWarningReceiptbl.getNew();
            receiptVO.setItems(warningList);
            receiptVO.setOperatorId(UserInfomation.userid);
            inventoryWarningReceiptbl.update(receiptVO);
        }
        return false;
    }

    @Override
    public boolean checkSaleSel(List<ListGoodsItemVO> list) throws RemoteException {
        List<ReceiptGoodsItemVO> warningList = new ArrayList<>();
        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();

            if(checkWarning(inventoryNum,goodsVO.getInventoryNum())){
                ReceiptGoodsItemVO goodsItemVO = new ReceiptGoodsItemVO();
                goodsItemVO.setGoodsId(goodsVO.getId());
                goodsItemVO.setGoodsName(goodsVO.getGoodName());
                goodsItemVO.setInventoryNum(inventoryNum);
                goodsItemVO.setWarningNum(goodsItemVO.getWarningNum());
                warningList.add(goodsItemVO);
            }


        }

        if(warningList.size() > 0){
            InventoryWarningReceiptVO receiptVO = inventoryWarningReceiptbl.getNew();
            receiptVO.setItems(warningList);
            receiptVO.setOperatorId(UserInfomation.userid);
            inventoryWarningReceiptbl.update(receiptVO);
        }

        return false;
    }



    @Override
    public boolean checkStorckRet(List<ListGoodsItemVO> list) throws RemoteException {
        List<ReceiptGoodsItemVO> warningList = new ArrayList<>();

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            int inventoryNum = goodsVO.getInventoryNum() - vo.getGoodsNum();

            if (checkWarning(inventoryNum, goodsVO.getInventoryNum())) {
                ReceiptGoodsItemVO goodsItemVO = new ReceiptGoodsItemVO();
                goodsItemVO.setGoodsId(goodsVO.getId());
                goodsItemVO.setGoodsName(goodsVO.getGoodName());
                goodsItemVO.setInventoryNum(inventoryNum);
                goodsItemVO.setWarningNum(goodsItemVO.getWarningNum());
                warningList.add(goodsItemVO);
            }
        }

        if(warningList.size() > 0){
            InventoryWarningReceiptVO receiptVO = inventoryWarningReceiptbl.getNew();
            receiptVO.setItems(warningList);
            receiptVO.setOperatorId(UserInfomation.userid);
            inventoryWarningReceiptbl.update(receiptVO);
        }

        return false;
    }

    private boolean checkWarning(int now,int inventoryNum){
        return (now<inventoryNum)?true:false;
    }
}
