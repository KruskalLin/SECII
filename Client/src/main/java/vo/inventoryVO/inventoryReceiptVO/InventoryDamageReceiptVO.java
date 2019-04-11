package vo.inventoryVO.inventoryReceiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.InventoryDamageReceiptPO;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import ui.inventoryui.inventoryReceiptui.InventoryDamageDetailPane;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryDamageReceiptVO extends ReceiptVO {
    private List<ReceiptGoodsItemVO> items;
    private String comment;

    public InventoryDamageReceiptVO() {
    }

    public InventoryDamageReceiptVO(InventoryDamageReceiptPO inventoryDamageReceiptPO) {
        super(inventoryDamageReceiptPO);
        this.comment = inventoryDamageReceiptPO.getComment();

        items = new ArrayList<>();
        if (inventoryDamageReceiptPO.getGoodsList() != null) {
            InventoryReceiptGoodsItemPO[] goods = inventoryDamageReceiptPO.getGoodsList();
            for (InventoryReceiptGoodsItemPO itemPO : goods) {
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


    public InventoryDamageReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, List<ReceiptGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.items = items;
        this.comment = comment;
    }

    @Override
    protected String getCodeName() {
        return "BSD";
    }

    @Override
    public InventoryDamageReceiptPO toPO() {
        InventoryDamageReceiptPO result = toReceiptPO(InventoryDamageReceiptPO.class);
        result.setComment(comment);
        result.setOperatorId(getOperatorId());


        // List<String> childList = vo.getChildrenId();
        InventoryReceiptGoodsItemPO[] itemPOs = new InventoryReceiptGoodsItemPO[items.size()];
      /*  items.toArray(itemPOs);
        result.setGoodsList(itemPOs);*/

        for (int i = 0; i < itemPOs.length; i++) {
            InventoryReceiptGoodsItemPO itemPO = new InventoryReceiptGoodsItemPO();
            ReceiptGoodsItemVO item = items.get(i);
            itemPO.setId(items.get(i).getGoodsId());
            itemPO.setGoodName(items.get(i).getGoodsName());
            itemPO.setGoodType(item.getGoodsType());
            itemPO.setInventoryNum(item.getInventoryNum());
            itemPO.setFactNumber(item.getFactNum());
            itemPO.setAlarmNumber(item.getWarningNum());
            itemPO.setsendNumber(item.getSendNum());
            itemPOs[i] = itemPO;
        }
        result.setGoodsList(itemPOs);

        return result;
    }


    @Override
    public CheckInfo<InventoryDamageReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getInventoryDamageReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new InventoryDamageDetailPane(this);
    }

    @Override
    public InventoryDamageListVO toListVO() {
        return new InventoryDamageListVO(this);
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
}

