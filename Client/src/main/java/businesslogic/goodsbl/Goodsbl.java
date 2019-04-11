package businesslogic.goodsbl;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import blService.goodsblService.GoodsblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import businesslogic.goodsClassificationbl.GoodsClassificationInfo;
import dataService.goodsdataService.GoodsDataService;
import po.GoodsPO;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Goodsbl implements GoodsblService {
    private GoodsDataService dataService;

    private GoodsPOVOChanger changer;

    private GoodsClassificationInfo info = new GoodsClassificationInfo();

    public Goodsbl() throws RemoteException, NotBoundException, MalformedURLException {
        changer = new GoodsPOVOChanger();
        dataService = (GoodsDataService) Naming.lookup("rmi://localhost:1099/GoodsData");
    }

    /**
     * 返回所有商品
     *
     * @return
     */
    public Set<GoodsVO> show() throws RemoteException {
        List<GoodsPO> POList = dataService.show();

//        for (int i = 0; i < POList.size(); i++) {
//            GoodsPO goodsPO = POList.get(i);
//
//            if(goodsPO.getIsDelete() == 1) // TODO by 连。有编译错误
//                POList.remove(i);
//        }
        List<GoodsVO> VOList = changer.allToVO(POList);
        return new HashSet<>(VOList);
    }

    /**
     * 添加商品
     *
     * @param goodsVO
     * @return
     */
    public ResultMessage addGoods(GoodsVO goodsVO) throws RemoteException {
        GoodsPO po = changer.oneToPO(goodsVO);

        info.addGoods(goodsVO.getClassifyId(), goodsVO.getId());

        dataService.insert(po);

        return ResultMessage.SUCCESS;
    }

    /**
     * 删除商品
     *
     * @param goodsVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteGoods(GoodsVO goodsVO) throws RemoteException {
        System.out.println("delete goods called");

        // update its father 注意：这里必须要先delete他的father，不然如果先删儿子，那么父新就拿不上来了
        MyGoodsClassificationblService goodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
        RecursiveGoodsClassificationVO rgvo = goodsClassificationblService.selectById(goodsVO.getClassifyId());
        for (GoodsVO g : new ArrayList<>(rgvo.getGoods())) {
            if (g.getId().equals(goodsVO.getId())) {
                rgvo.getGoods().remove(g);
            }
        }

        // delete itself
        dataService.delete(goodsVO.getId());

        goodsClassificationblService.update(rgvo);

        return ResultMessage.SUCCESS;
    }

    /**
     * 根据id删除商品
     *
     * @param goodId
     * @return
     * @throws RemoteException
     */
//    public ResultMessage deleteGoods(String goodId) throws RemoteException {
//        dataService.delete(goodId);
//
//        return ResultMessage.SUCCESS;
//    }

    /**
     * 更新商品
     *
     * @param goodsVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateGoods(GoodsVO goodsVO) throws RemoteException {
        GoodsPO po = changer.oneToPO(goodsVO);
        dataService.update(po);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoods(GoodsPO goodsPO) throws RemoteException {
        dataService.update(goodsPO);
        return ResultMessage.SUCCESS;
    }

    public List<GoodsVO> searchGoods(String info) throws RemoteException {
        /* 虽然前面使用了策略模式实现模糊查询及查询方式的可拓展性，但实际上他们调的是Controller的同一个方法，再调这个方法，所以是假模糊查询，待讨论*/
        List<GoodsPO> POList = dataService.selectInEffect(info);
        List<GoodsVO> VOList = changer.allToVO(POList);
        return VOList;
    }

    /**
     * 这里将商品编号设置为 分类ID + 添加次序的形式
     *
     * @param upID
     * @param order
     * @return
     */
    public String getID(String upID, int order) throws RemoteException {
        String id = upID + "-" + order;
        return id;
    }


    /**
     * 通过商品id查找商品
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public GoodsVO showDetail(String id) throws RemoteException {
        GoodsPO goodsPO = dataService.selectById(id);
        return changer.oneToVO(goodsPO);
    }
}
