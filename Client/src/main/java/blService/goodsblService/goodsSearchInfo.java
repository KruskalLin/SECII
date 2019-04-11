package blService.goodsblService;

import po.GoodsPO;
import vo.inventoryVO.GoodsVO;

import java.rmi.RemoteException;
import java.util.List;

public interface goodsSearchInfo {
    public GoodsVO getGoodById(String Id) throws RemoteException;


    public List<GoodsVO> getGoods(String keyword) throws RemoteException;
}
