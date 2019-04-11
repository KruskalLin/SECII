package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.GoodsClassificationblService;
import com.sun.org.apache.regexp.internal.RE;
import dataService.goodsdataService.GoodsClassificationDataService;
import po.GoodsClassificationPO;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class GoodsClassificationbl implements GoodsClassificationblService{
    private GoodsClassificationDataService goodsClassificationData;

    private GoodsClassficatinPOVOChanger changer;

    public GoodsClassificationbl() throws RemoteException, NotBoundException, MalformedURLException {
        changer = new GoodsClassficatinPOVOChanger();
        goodsClassificationData = (GoodsClassificationDataService) Naming.lookup("rmi://localhost:1099/GoodsClassificationData");
    }

    /**
     * 得到所有的商品分类
     * @return List
     */
    public List<GoodsClassificationVO> show() throws RemoteException{
        List<GoodsClassificationPO> POList = goodsClassificationData.show();

        List<GoodsClassificationVO> VOList = getTree(POList);


        return VOList;
    }

    /**
     * 得到id
     * */
    public String getID(String fatherId, int order) throws RemoteException{
        String id = fatherId + "/" + order;
        return id;
    }

    /**
     * 添加商品分类
     */
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws RemoteException {
        //如果商品分类下有商品，则不能添加分类
        String fatherId = vo.getFatherID();
       // if(!hasLeaf(fatherId)){
            GoodsClassificationPO po = changer.oneToPO(vo);

            goodsClassificationData.insert(po);

            return ResultMessage.SUCCESS;
     /*   }else{
            //商品（id）已存在，添加失败
            return ResultMessage.FAIL;
        }*/
    }

    /**
     * 对商品分类进行删除，共有三种情况
     *
     */
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) throws RemoteException{
     /*   boolean hasGoods = vo.getGoodsID().isEmpty();
        boolean isLeaf = vo.getChildrenId().isEmpty();

        String id = vo.getID();
        //是叶节点且无商品的时候直接删除
        if(!hasGoods && isLeaf){
            goodsClassificationData.delete(id);
        }else if(hasGoods && isLeaf){
            //是叶节点且有商品，这个时候要把其下面的商品也删除，需要提供的接口
            goodsClassificationData.delete(id);
            *//**
             * .....
             *//*
        }else if (!hasGoods && !isLeaf){
            //中间节点，删除该分类后，用递归一一删除其子分类
            goodsClassificationData.delete(id);
            GoodsClassificationPO po = goodsClassificationData.getById(id);
            String[] childrenId = po.getChildrenId();

            for (int i = 0; i < childrenId.length; i++) {
                //进行递归
                GoodsClassificationPO tmpPO = goodsClassificationData.getById(childrenId[i]);
                GoodsClassificationVO tmpVO = changer.oneToVO(tmpPO);
                deleteGoodsClassification(tmpVO);
            }
        }
        return ResultMessage.SUCCESS;*/
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) throws RemoteException{
        GoodsClassificationPO po = changer.oneToPO(vo);
        goodsClassificationData.update(po);
        return ResultMessage.SUCCESS;
    }

    private boolean hasLeaf(String fatherId) throws RemoteException{
        GoodsClassificationPO po = goodsClassificationData.getById(fatherId);
        String[] goodsID = po.getGoodsId();
        return !(goodsID.length == 0);
    }


    public GoodsClassificationPO getById(String id) throws RemoteException
    {
        return goodsClassificationData.getById(id);
    }

    //经过初步处理的商品分类树，每一层的商品分类是在一起的，root后面跟着他的所有孩子leve1，level后面跟着他们的孩子level2，建树
    private List<GoodsClassificationVO> getTree(List<GoodsClassificationPO> POList) throws RemoteException{
        List<GoodsClassificationVO> VOList = new ArrayList<>(POList.size());


        GoodsClassificationVO head = changer.oneToVO(POList.get(0));

        GoodsClassificationVO vo = head;

        Queue<GoodsClassificationVO> que = new LinkedList<>();
        que.offer(vo);

        while (!que.isEmpty()){
            vo = que.poll();
            VOList.add(vo);
            List<String> childrenId = vo.getChildrenId();

            if(childrenId.size() == 0)
                continue;

            //顺着整个商品分类找
            for (int i = 1; i < POList.size(); i++) {
                GoodsClassificationPO po = POList.get(i);
                String tmpId = po.getId();

                //如果该商品分类是其子分类，就把该商品分类放到其后面
                if(childrenId.indexOf(tmpId) != -1){
                    GoodsClassificationVO tmpVO = changer.oneToVO(po);
                    //VOList.add(tmpVO);
                    que.offer(tmpVO);
                }
            }
        }

        return VOList;
    }
}
