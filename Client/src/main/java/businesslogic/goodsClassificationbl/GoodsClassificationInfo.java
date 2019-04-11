package businesslogic.goodsClassificationbl;

import blService.goodsblService.GoodsClassification_Goods;
import dataService.goodsdataService.GoodsClassificationDataService;
import po.GoodsClassificationPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class GoodsClassificationInfo implements GoodsClassification_Goods {
    private static final String DELETE = "-1";
    private GoodsClassificationDataService dataService;

    public GoodsClassificationInfo() throws RemoteException, NotBoundException, MalformedURLException {
        dataService = (GoodsClassificationDataService) Naming.lookup("rmi://localhost:1099/GoodsClassificationData");
    }

    /**
     * 当添加商品的时候也需要更新商品分类的属性
     * @param classifyId
     * @param goodId
     * @throws RemoteException
     */
    @Override
    public void addGoods(String classifyId ,String goodId) throws RemoteException {
        GoodsClassificationPO po = dataService.getById(classifyId);
        String[] oldGoodsId = po.getGoodsId();

        String[] newGoodsId;
        if(oldGoodsId == null)
            newGoodsId = new String[]{goodId};
        else {
            newGoodsId = Arrays.copyOf(oldGoodsId, oldGoodsId.length + 1);
            newGoodsId[oldGoodsId.length] = goodId;
        }

        po.setGoodsId(newGoodsId);

        dataService.update(po);
    }

    /**
     * 当删除商品的时候需要更新商品分类的goodsId属性
     * @param classifyId
     * @param goodsId
     * @throws RemoteException
     */
    @Override
    public void deleteGoods(String classifyId ,String goodsId) throws RemoteException{
        GoodsClassificationPO po = dataService.getById(classifyId);

        String[] oldGoodsId = po.getGoodsId();
        String[] newGoodsId = new String[oldGoodsId.length-1];

        for (int i = 0,j = 0; i < oldGoodsId.length; i++) {
            if(goodsId.equals(oldGoodsId[i])){
                continue;
            }
            newGoodsId[j++] = oldGoodsId[i];

            /*if(goodsId.equals(oldGoodsId[i])) {
                oldGoodsId[i] = "";
                break;
            }*/
        }

        po.setGoodsId(newGoodsId);
        dataService.update(po);
    }
}
