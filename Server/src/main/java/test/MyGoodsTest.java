package test;

import data.goodsdata.GoodsClassificationData;
import data.goodsdata.GoodsData;
import po.GoodsClassificationPO;
import po.GoodsPO;

import java.rmi.RemoteException;


public class MyGoodsTest {
    /**
     * 实际上，如果严格按照id来的话，那么不需要保存fatherId，Goods和Children只需要做两个int表示数量就可以
     * 但是这样的话删除就有问题了，所以还要再想想。不过这个是个假删除…
     */
    public static void main(String[] args) throws RemoteException {
        GoodsData goodsData = new GoodsData();
        GoodsClassificationData goodsClassificationData = new GoodsClassificationData();
//        System.out.println(goodsData.show().toString());
      /*  GoodsPO goodsPO = new GoodsPO("121","花灯1","美国灯","12212",0,1,1,1,1,23);

        goodsData.update(goodsPO);*/
        // goodsData.insert(new GoodsPO("121","花灯1","中国灯","12212",12,1,1,1,1,23));
//        goodsData.insert(new GoodsPO("122", "花灯2", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("123", "花灯3", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("124", "花灯4", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("125", "花灯5", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("126", "花灯6", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("127", "花灯7", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("128", "花灯8", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("129", "花灯9", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
//        goodsData.insert(new GoodsPO("130", "花灯10", "中国灯", "12212", 12, 1, 1, 1, 1, 23));
        //goodsData.delete("12433");
        // goodsData.update(new GoodsPO("12433","彩灯","中国灯","12212",1,1,1,1,1,1));

//        goodsClassificationData.update(new GoodsClassificationPO("141", "light", "131", null, new String[]{"sss", "aaa"}));
//        goodsClassificationData.insert(new GoodsClassificationPO("root", null, null, new String[]{"123", "124", "125", "126", "127"}, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("123", "345", "root", new String[]{"131", "132", "133"}, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("124", "346", "root", new String[]{"134"}, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("125", "347", "root", null, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("126", "348", "root", null, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("127", "349", "root", null, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("131", "light", "123", new String[]{"141"}, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("132", "light", "123", null, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("133", "light", "123", null, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("134", "light", "124", null, null));
//        goodsClassificationData.insert(new GoodsClassificationPO("141", "light", "131", null, null));


        // 自己写的这一套，每次出bug了就全部delete from xx，然后过来insert一下
        // 每次用完最好都注释一下，防止之后不小心点到运行，重复插入
        //已插入
//        GoodsPO iPhone6 = new GoodsPO();
//        iPhone6.setId("root/0/0/0");
//        iPhone6.setGoodName("iPhone 6");
//        iPhone6.setGoodType("normal");
//        iPhone6.setClassifyId("root/0/0");
//        iPhone6.setInventoryNum(100);
//        iPhone6.setPurPrice(3000);
//        iPhone6.setSalePrice(4000);
//        iPhone6.setAlarmNumber(10);
//
//        goodsData.insert(iPhone6);
//
//        //已插入
//        GoodsPO iPhone6Plus = new GoodsPO();
//        iPhone6Plus.setId("root/0/0/1");
//        iPhone6Plus.setGoodName("iPhone 6Plus");
//        iPhone6Plus.setGoodType("Plus");
//        iPhone6Plus.setClassifyId("root/0/0");
//        iPhone6Plus.setInventoryNum(80);
//        iPhone6Plus.setPurPrice(4000);
//        iPhone6Plus.setSalePrice(5000);
//        iPhone6Plus.setAlarmNumber(10);
//
//        goodsData.insert(iPhone6Plus);
//
//        //已插入
//        GoodsPO iPhoneX = new GoodsPO();
//        iPhoneX.setId("root/0/2/0");
//        iPhoneX.setGoodName("iPhone X");
//        iPhoneX.setGoodType("Special");
//        iPhoneX.setClassifyId("root/0/2");
//        iPhoneX.setInventoryNum(50);
//        iPhoneX.setPurPrice(7000);
//        iPhoneX.setSalePrice(8000);
//        iPhoneX.setAlarmNumber(30);
//
//        goodsData.insert(iPhoneX);
//
//        // goodsClassification
//        // 这里的string[]是null还是空，多种情况都要尝试一下…
//
//        GoodsClassificationPO root = new GoodsClassificationPO();
//        root.set_name("全部商品分类");
//        root.setFatherId(null);
//        root.setId("root");
//        root.setChildrenId(new String[]{"root/0", "root/1", "root/2"});
//        root.setGoodsId(new String[]{});
//
//        goodsClassificationData.insert(root);
//
//        GoodsClassificationPO iPhone = new GoodsClassificationPO();
//        iPhone.set_name("iPhone");
//        iPhone.setFatherId("root");
//        iPhone.setId("root/0");
//        iPhone.setChildrenId(new String[]{"root/0/0", "root/0/1", "root/0/2"});
//        iPhone.setGoodsId(null);
//
//        goodsClassificationData.insert(iPhone);
//
//        GoodsClassificationPO iPhone6Category = new GoodsClassificationPO();
//        iPhone6Category.set_name("iPhone6");
//        iPhone6Category.setFatherId("root/0");
//        iPhone6Category.setId("root/0/0");
//        iPhone6Category.setChildrenId(new String[]{});
//        iPhone6Category.setGoodsId(new String[]{"root/0/0/0", "root/0/0/1"});
//
//        goodsClassificationData.insert(iPhone6Category);
//
//        GoodsClassificationPO iPhone7Category = new GoodsClassificationPO();
//        iPhone7Category.set_name("iPhone7");
//        iPhone7Category.setFatherId("root/0");
//        iPhone7Category.setId("root/0/1");
//        iPhone7Category.setChildrenId(null);
//        iPhone7Category.setGoodsId(null);
//
//        goodsClassificationData.insert(iPhone7Category);
//
//        GoodsClassificationPO iPhoneXCategory = new GoodsClassificationPO();
//        iPhoneXCategory.set_name("iPhoneX");
//        iPhoneXCategory.setFatherId("root/0");
//        iPhoneXCategory.setId("root/0/2");
//        iPhoneXCategory.setChildrenId(new String[]{});
//        iPhoneXCategory.setGoodsId(new String[]{"root/0/2/0"});
//
//        goodsClassificationData.insert(iPhoneXCategory);
//
//        GoodsClassificationPO macCategory = new GoodsClassificationPO();
//        macCategory.set_name("mac");
//        macCategory.setFatherId("root");
//        macCategory.setId("root/1");
//        macCategory.setChildrenId(new String[]{});
//        macCategory.setGoodsId(new String[]{});
//
//        goodsClassificationData.insert(macCategory);
//
//        GoodsClassificationPO iPadCategory = new GoodsClassificationPO();
//        iPadCategory.set_name("iPad");
//        iPadCategory.setFatherId("root");
//        iPadCategory.setId("root/2");
//        iPadCategory.setChildrenId(new String[]{});
//        iPadCategory.setGoodsId(new String[]{});
//
//        goodsClassificationData.insert(iPadCategory);


        System.out.println("finish");
    }
}
