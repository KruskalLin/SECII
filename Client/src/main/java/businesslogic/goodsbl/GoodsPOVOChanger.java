package businesslogic.goodsbl;

import po.GoodsPO;
import vo.inventoryVO.GoodsVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoodsPOVOChanger {
    public static GoodsVO oneToVO(GoodsPO po){
        GoodsVO vo = new GoodsVO();

        vo.setId(po.getId());
        vo.setGoodName(po.getGoodName());
        vo.setGoodType(po.getGoodType());
        vo.setClassifyId(po.getClassifyId());
        vo.setAlarmNumber(po.getAlarmNumber());
        vo.setInventoryNum(po.getInventoryNum());
        vo.setPurPrice(po.getPurPrice());
        vo.setRecentPurPrice(po.getRecentPurPrice());
        vo.setRecentSalePrice(po.getRecentSalePrice());
        vo.setSalePrice(po.getSalePrice());
        vo.setIsStockPur(po.getIsStockPur());

        return vo;
    }

    public static List<GoodsVO> allToVO(List<GoodsPO> POList){
        List<GoodsVO> VOList = new ArrayList<>(POList.size());
        Iterator<GoodsPO> it = POList.iterator();
        while(it.hasNext())
            VOList.add(oneToVO(it.next()));

        return VOList;
    }

    public static GoodsPO oneToPO(GoodsVO vo){
        GoodsPO po = new GoodsPO();

        po.setId(vo.getId());
        po.setGoodName(vo.getGoodName());
        po.setGoodType(vo.getGoodType());
        po.setClassifyId(vo.getClassifyId());
        po.setAlarmNumber(vo.getAlarmNumber());
        po.setInventoryNum(vo.getInventoryNum());
        po.setPurPrice(vo.getPurPrice());
        po.setRecentPurPrice(vo.getRecentPurPrice());
        po.setRecentSalePrice(vo.getRecentSalePrice());
        po.setSalePrice(vo.getSalePrice());
        po.setIsStockPur(vo.getIsStockPur());

        return po;
    }
}
