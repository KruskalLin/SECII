package data.inventorydata;

import po.receiptPO.*;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryTest{
    public static void main(String[] args) throws RemoteException {
      /*  testGift();
        testDamage();
        testOverflow();
        testWarning();*/
        InventoryReceiptGoodsItemPO[] pos = {new InventoryReceiptGoodsItemPO("121","花灯1","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("122","花灯2","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("123","花灯3","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("124","花灯4","中国灯",12,23,12,23)
        };
      InventoryDamageReceiptData data = new InventoryDamageReceiptData();
      InventoryDamageReceiptPO po = data.getNew();
      po.setOperatorId(123123);
      po.setLastModifiedTime(LocalDateTime.now());
      po.setReceiptState(ReceiptState.PENDING);
      po.setClerkName("李明");
      po.setGoodsList(pos);
      po.setComment("无");
      data.update(po);

       // List<InventoryGiftReceiptPO> list = data.search(new RespectiveReceiptSearchCondition());
       // System.out.println(list.toString());

    }

    public static void testGift() throws RemoteException {
        InventoryReceiptGoodsItemPO[] pos = {new InventoryReceiptGoodsItemPO("121","花灯1","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("122","花灯2","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("123","花灯3","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("124","花灯4","中国灯",12,23,12,23)
        };
        InventoryGiftReceiptData data = new InventoryGiftReceiptData();
        data.insert(new InventoryGiftReceiptPO(2,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryGiftReceiptPO(3,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryGiftReceiptPO(4,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryGiftReceiptPO(5,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryGiftReceiptPO(6,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));

    }

    public static void testDamage() throws RemoteException {
        InventoryReceiptGoodsItemPO[] pos = {new InventoryReceiptGoodsItemPO("121","花灯1","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("122","花灯2","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("123","花灯3","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("124","花灯4","中国灯",12,23,12,23)
        };
        InventoryDamageReceiptData data = new InventoryDamageReceiptData();
        data.insert(new InventoryDamageReceiptPO(2,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryDamageReceiptPO(3,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryDamageReceiptPO(4,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryDamageReceiptPO(5,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryDamageReceiptPO(6,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));

    }

    public static void testOverflow() throws RemoteException {
        InventoryReceiptGoodsItemPO[] pos = {new InventoryReceiptGoodsItemPO("121","花灯1","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("122","花灯2","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("123","花灯3","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("124","花灯4","中国灯",12,23,12,23)
        };
        InventoryOverflowReceiptData data = new InventoryOverflowReceiptData();
        data.insert(new InventoryOverflowReceiptPO(2,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryOverflowReceiptPO(3,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryOverflowReceiptPO(4,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryOverflowReceiptPO(5,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryOverflowReceiptPO(6,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));


    }

    public static void testWarning() throws RemoteException {
        InventoryReceiptGoodsItemPO[] pos = {new InventoryReceiptGoodsItemPO("121","花灯1","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("122","花灯2","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("123","花灯3","中国灯",12,23,12,23),
                new InventoryReceiptGoodsItemPO("124","花灯4","中国灯",12,23,12,23)
        };
        InventoryWarningReceiptData data = new InventoryWarningReceiptData();
        data.insert(new InventoryWarningReceiptPO(2,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryWarningReceiptPO(3,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryWarningReceiptPO(4,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryWarningReceiptPO(5,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));
        data.insert(new InventoryWarningReceiptPO(6,123123, LocalDateTime.now(),LocalDateTime.now(), ReceiptState.APPROVED,
                "李明",pos,"无"));


    }
}
