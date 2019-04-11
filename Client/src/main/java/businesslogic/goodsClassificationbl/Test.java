package businesslogic.goodsClassificationbl;

import po.GoodsClassificationPO;
import vo.inventoryVO.GoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        GoodsClassificationbl goodsClassificationbl = new GoodsClassificationbl();

        //List<GoodsClassificationPO> list = new ArrayList<>();


        //List<GoodsClassificationVO> voList = goodsClassificationbl.getTree(list);
       /* for (GoodsClassificationVO vo:voList) {
            System.out.println(vo.getID());
        }*/
        List<GoodsClassificationVO> list = goodsClassificationbl.show();

       /* for (GoodsClassificationVO vo:list) {
            System.out.println(vo.getID());
        }*/
        //System.out.println(list);*/
    }
}
