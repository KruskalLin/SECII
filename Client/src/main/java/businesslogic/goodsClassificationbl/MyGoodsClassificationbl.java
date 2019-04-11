package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.goodsbl.GoodsPOVOChanger;
import dataService.goodsdataService.GoodsClassificationDataService;
import dataService.goodsdataService.GoodsDataService;
import po.GoodsClassificationPO;
import po.GoodsPO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MyGoodsClassificationbl implements MyGoodsClassificationblService {
    private GoodsClassificationDataService goodsClassificationData;
    private GoodsDataService goodsDataService;

    public MyGoodsClassificationbl() throws RemoteException, NotBoundException, MalformedURLException {
        goodsClassificationData = (GoodsClassificationDataService) Naming.lookup("rmi://localhost:1099/GoodsClassificationData");
        goodsDataService = (GoodsDataService) Naming.lookup("rmi://localhost:1099/GoodsData");
    }

    @Override
    public RecursiveGoodsClassificationVO fuzzySearchRoot(String keyword) throws RemoteException {
        RecursiveGoodsClassificationVO root = selectRoot();
        mark(root, keyword);

        if (!root.mark) {
            return null;
        }

        filter(root);
        return root; // 这里如果return null表示没有搜索结果
    }

    private void mark(RecursiveGoodsClassificationVO root, String keyword) {
        if (root.getGoods().isEmpty()) { // 两个list都空的情况也包括在这里了
            for (RecursiveGoodsClassificationVO vo : root.getChildren()) {
                mark(vo, keyword);
            }
        } else {
            for (GoodsVO vo : root.getGoods()) {
                if (match(vo, keyword)) {
                    vo.mark = true;

                    RecursiveGoodsClassificationVO r = root;
                    while (!r.getId().equals("root") && !r.mark) {
                        // 总之感觉这个算法还是很垃圾
                        r.mark = true;
                        r = r.getFather();
                    }
                    r.mark = true; // 这里的mark是针对root来的，至于本来就mark的多mark一次也无所谓
                }
            }
        }
    }

    private void filter(RecursiveGoodsClassificationVO root) {
        if (root.getGoods().isEmpty()) {
            for (RecursiveGoodsClassificationVO vo : new ArrayList<>(root.getChildren())) {
                if (vo.mark) {
                    filter(vo);
                } else {
                    root.getChildren().remove(vo);
                }
            }
        } else {
            for (GoodsVO vo: new ArrayList<>(root.getGoods())) {
                if (!vo.mark) {
                    root.getGoods().remove(vo);
                }
            }
        }
    }

    private boolean match(GoodsVO vo, String keyword) {
        // 这个第一种成功条件就隐含了keyword必须是root开头才可能成
        return vo.getId().startsWith(keyword) || vo.getGoodName().contains(keyword);
    }

    @Override
    public RecursiveGoodsClassificationVO selectRoot() throws RemoteException {
        return selectById("root");
    }

    @Override
    public RecursiveGoodsClassificationVO selectById(String id) throws RemoteException {
        GoodsClassificationPO gcpo = goodsClassificationData.getById(id);
        RecursiveGoodsClassificationVO result = new RecursiveGoodsClassificationVO(gcpo);


        if (gcpo.getGoodsId() != null && gcpo.getGoodsId().length != 0) {
            for (String s : gcpo.getGoodsId()) {
                GoodsPO goodsPO = goodsDataService.selectById(s);
                result.getGoods().add(GoodsPOVOChanger.oneToVO(goodsPO));
            }
            return result;
        }

        if (gcpo.getChildrenId() != null) {
            for (String s : gcpo.getChildrenId()) {
                RecursiveGoodsClassificationVO node = selectById(s);
                node.setFather(result);
                // 我怎么感觉不set也是可以的
                // 这个方法如果不是root调用那么返回值也不会赋father
                result.getChildren().add(node);
            }
        }
        return result;
    }

    @Override
    public void add(RecursiveGoodsClassificationVO vo) throws RemoteException {
        goodsClassificationData.insert(vo.toPO());
    }

    @Override
    public void delete(RecursiveGoodsClassificationVO vo) throws RemoteException {
        goodsClassificationData.delete(vo.getId());
    }

    @Override
    public void update(RecursiveGoodsClassificationVO vo) throws RemoteException {
        goodsClassificationData.update(vo.toPO());
    }
}
