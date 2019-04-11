package vo.inventoryVO.inventoryReceiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import po.receiptPO.InventoryWarningReceiptPO;
import ui.inventoryui.inventoryReceiptui.InventoryWarningDetailPane;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryWarningReceiptVO extends ReceiptVO {
    private List<ReceiptGoodsItemVO> items;
    private String comment;

    public InventoryWarningReceiptVO() {
    }

    public InventoryWarningReceiptVO(InventoryWarningReceiptPO inventoryWarningReceiptPO) {
        super(inventoryWarningReceiptPO);
        this.comment = inventoryWarningReceiptPO.getComment();

        items = new ArrayList<>();
        if(inventoryWarningReceiptPO.getGoodsList() != null){
            InventoryReceiptGoodsItemPO[] goods = inventoryWarningReceiptPO.getGoodsList();
            for (InventoryReceiptGoodsItemPO itemPO:goods) {
                ReceiptGoodsItemVO vo = new ReceiptGoodsItemVO();
                vo.setGoodsId(itemPO.getId());
                vo.setGoodsName(itemPO.getGoodName());
                vo.setInventoryNum(itemPO.getInventoryNum());
                vo.setWarningNum(itemPO.getAlarmNumber());

                items.add(vo);
            }
        }

    }

    public InventoryWarningReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, List<ReceiptGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.items = items;
        this.comment = comment;
    }

    @Override
    public  CheckInfo<InventoryWarningReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getInventoryWarningReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new InventoryWarningDetailPane(this);
    }

    @Override
    public InventoryWarningListVO toListVO() {
        return new InventoryWarningListVO(this);
    }

    @Override
    protected String getCodeName() {
        return "BJD";
    }

    @Override
    public InventoryWarningReceiptPO toPO() {
        InventoryWarningReceiptPO result = toReceiptPO(InventoryWarningReceiptPO.class);
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
            itemPO.setFactNumber(item.getInventoryNum());
            itemPO.setAlarmNumber(item.getWarningNum());
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
}
