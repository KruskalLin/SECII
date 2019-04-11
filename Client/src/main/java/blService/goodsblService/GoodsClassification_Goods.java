package blService.goodsblService;

import java.rmi.RemoteException;

public interface GoodsClassification_Goods {

    public void addGoods(String classifyId, String goodId) throws RemoteException;

    public void deleteGoods(String classifyId, String goodsId) throws RemoteException;

}
