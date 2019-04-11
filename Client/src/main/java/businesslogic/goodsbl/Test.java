package businesslogic.goodsbl;

import dataService.goodsdataService.GoodsDataService;
import po.GoodsPO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Goodsbl goodsbl = new Goodsbl();
       // System.out.println(goodsbl.showDetail("null0"));
//        goodsbl.deleteGoods("root10-1");
        //goodsbl.deleteGoods(new GoodsVO("null/101","花灯1","中国灯","null/10",12,1,1,1,1,23));
       // Set<GoodsVO> list = goodsbl.show();
      //  GoodsVO goodsVO = new GoodsVO("191","花灯1","中国灯","12212",12,1,1,1,1,23);
       // for (GoodsVO vo:list) {
        //    System.out.println(vo.toString());
       // }
        //goodsbl.addGoods(goodsVO);
        //goodsbl.deleteGoods("null/81");
    }
}
