package businesslogic.goodsbl.goodsSearch;

import blService.goodsblService.goodsSearchInfo;
import businesslogic.goodsbl.Goodsbl;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class goodsSearch implements goodsSearchInfo {
    Goodsbl goodsbl;

    public goodsSearch() throws RemoteException, NotBoundException, MalformedURLException {
        goodsbl = new Goodsbl();
    }

    @Override
    public GoodsVO getGoodById(String Id) throws RemoteException {
        return goodsbl.showDetail(Id);

    }

    @Override
    public List<GoodsVO> getGoods(String keywords) throws RemoteException {
        return goodsbl.searchGoods(keywords);
    }
}
