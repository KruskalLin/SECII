package vo.inventoryVO.inventoryReceiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import ui.inventoryui.inventoryReceiptui.InventoryGiftDetailPane;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryGiftReceiptVO extends ReceiptVO {
    private List<ReceiptGoodsItemVO> items;
    private String comment;

    public InventoryGiftReceiptVO() {
    }

    public InventoryGiftReceiptVO(InventoryGiftReceiptPO inventoryGiftReceiptPO) {
        super(inventoryGiftReceiptPO);
        this.comment = inventoryGiftReceiptPO.getComment();

        items = new ArrayList<>();
        if(inventoryGiftReceiptPO.getGoodsList() != null){
            InventoryReceiptGoodsItemPO[] goods = inventoryGiftReceiptPO.getGoodsList();
            for (InventoryReceiptGoodsItemPO itemPO:goods) {
                ReceiptGoodsItemVO vo = new ReceiptGoodsItemVO();
                vo.setGoodsId(itemPO.getId());
                vo.setGoodsName(itemPO.getGoodName());
                vo.setInventoryNum(itemPO.getInventoryNum());
                vo.setFactNum(itemPO.getFactNumber());
                vo.setSendNum(itemPO.getsendNumber());
                vo.setWarningNum(itemPO.getAlarmNumber());

                items.add(vo);
            }
        }

    }

    public InventoryGiftReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, List<ReceiptGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.items = items;
        this.comment = comment;
    }

    @Override
    public  CheckInfo<InventoryGiftReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getInventoryGiftReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new InventoryGiftDetailPane(this);
    }

    @Override
    public InventoryGiftListVO toListVO() {
        return new InventoryGiftListVO(this);
    }

    @Override
    protected String getCodeName() {
        return "ZSD";
    }

    @Override
    public InventoryGiftReceiptPO toPO() {
        InventoryGiftReceiptPO result = toReceiptPO(InventoryGiftReceiptPO.class);
        result.setComment(comment);
        result.setOperatorId(getOperatorId());


      InventoryReceiptGoodsItemPO[] itemPOs = new InventoryReceiptGoodsItemPO[items.size()];

        for (int i = 0; i < itemPOs.length; i++) {
            InventoryReceiptGoodsItemPO itemPO = new InventoryReceiptGoodsItemPO();
            ReceiptGoodsItemVO item = items.get(i);
            itemPO.setId(item.getGoodsId());
            itemPO.setGoodName(item.getGoodsName());
            itemPO.setInventoryNum(item.getInventoryNum());
            itemPO.setsendNumber(item.getSendNum());
            itemPOs[i] = itemPO;
        }
        result.setGoodsList(itemPOs);

        return result;
    }

    public List<ReceiptGoodsItemVO> getItems() {
        return items;
    }

    public void setItems(List<ReceiptGoodsItemVO> items) {
        this.items = items;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "InventoryGiftReceiptVO{" +
                "items=" + items +
                ", comment='" + comment + '\'' +
                '}';
    }
}
