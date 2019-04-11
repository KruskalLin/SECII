package data.goodsdata;

import po.GoodsClassificationPO;
import po.GoodsPO;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws RemoteException {
        GoodsData goodsData = new GoodsData();

        goodsData.delete("root10-1");

        //goodsData.update(new GoodsPO("122", "花灯2", "小灯1", "root", 122, 29.0, 12.0, 12.0, 54.0,23, 00));
        // GoodsClassificationData goodsClassificationData = new GoodsClassificationData();
        // String[] childrenId = {"123","124","125","126","127"};
        //  GoodsClassificationPO po = new GoodsClassificationPO("root","root","null",childrenId,null);
        // goodsClassificationData.update(po);
        //  goodsData.delete("130");
        //goodsData.insert(new GoodsPO("155","花灯1","美国灯","12212",0,1,1,1,1,23));
      /*  GoodsPO goodsPO = new GoodsPO("121","花灯1","美国灯","12212",0,1,1,1,1,23);

        goodsData.update(goodsPO);*/
//       goodsData.insert(new GoodsPO("121","花灯1","中国灯","12212",12,1,1,1,1,23));
        // goodsData.update(new GoodsPO("12433","彩灯","中国灯","12212",1,1,1,1,1,1));


/*
        GoodsClassificationData goodsClassificationData = new GoodsClassificationData();
        goodsClassificationData.update(new GoodsClassificationPO("141","light","131",null,new String[]{"sss","aaa"}));*/
    /*    goodsClassificationData.insert(new GoodsClassificationPO("root",null,null,new String[]{"123","124","125","126","127"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("123","345","root",new String[]{"131","132","133"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("124","346","root",new String[]{"134"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("125","347","root",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("126","348","root",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("127","349","root",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("131","light","123",new String[]{"141"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("132","light","123",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("133","light","123",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("134","light","124",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("141","light","131",null,null));*/
    }
}
