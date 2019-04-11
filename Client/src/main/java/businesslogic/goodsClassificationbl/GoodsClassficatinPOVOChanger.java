package businesslogic.goodsClassificationbl;

import po.GoodsClassificationPO;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GoodsClassficatinPOVOChanger {

    public  List<GoodsClassificationVO> allToVO(List<GoodsClassificationPO> POList){
        GoodsClassficatinPOVOChanger changer = new GoodsClassficatinPOVOChanger();
        Iterator<GoodsClassificationPO> it = POList.iterator();
        List<GoodsClassificationVO> VOList = new ArrayList<>();

        while (it.hasNext())
            VOList.add(changer.oneToVO(it.next()));
        return VOList;
    }

    public GoodsClassificationVO oneToVO(GoodsClassificationPO po){
        GoodsClassificationVO vo = new GoodsClassificationVO();
        vo.setID(po.getId());
        vo.setFatherID(po.getFatherID());
        vo.setName(po.getName());

        List<String> childrenId;
        if(po.getChildrenId() != null)
            childrenId = new ArrayList<>(Arrays.asList(po.getChildrenId()));
        else
            childrenId = new ArrayList<>();

        vo.setChildrenId(childrenId);

        List<String> goodsId;
        if(po.getGoodsId() != null)
            goodsId = new ArrayList<>(Arrays.asList(po.getGoodsId()));
        else
            goodsId = new ArrayList<>();
        vo.setGoodsID(goodsId);

        return vo;
    }

    public GoodsClassificationPO oneToPO(GoodsClassificationVO vo){
        GoodsClassificationPO po = new GoodsClassificationPO();
        po.setId(vo.getID());
        po.set_name(vo.getName());
        po.setFatherId(vo.getFatherID());


        List<String> childList = vo.getChildrenId();

        String[] childrenId = null;
        if(childList != null) {
            childrenId = new String[childList.size()];
            childList.toArray(childrenId);
        }
        po.setChildrenId(childrenId);

        List<String> goodList = vo.getGoodsID();

        String[] goodsId = null;
        if(goodList != null) {
            goodsId = new String[goodList.size()];
            goodList.toArray(goodsId);
        }
        po.setGoodsId(goodsId);
        return po;
    }
}
