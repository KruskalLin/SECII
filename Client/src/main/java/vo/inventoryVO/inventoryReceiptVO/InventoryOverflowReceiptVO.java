package vo.inventoryVO.inventoryReceiptVO;

import blService.checkblService.CheckInfo;
import businesslogic.blServiceFactory.MyServiceFactory;
import javafx.scene.Node;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import ui.inventoryui.inventoryReceiptui.InventoryOverflowDetailPane;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryOverflowReceiptVO extends ReceiptVO {
    private List<ReceiptGoodsItemVO> items;
    private String comment;

    public InventoryOverflowReceiptVO() {
    }

    public InventoryOverflowReceiptVO(InventoryOverflowReceiptPO InventoryOverflowReceiptPO) {
        super(InventoryOverflowReceiptPO);
        this.comment = InventoryOverflowReceiptPO.getComment();

        items = new ArrayList<>();
        if(InventoryOverflowReceiptPO.getGoodsList() != null){
            InventoryReceiptGoodsItemPO[] goods = InventoryOverflowReceiptPO.getGoodsList();
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

    public InventoryOverflowReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, List<ReceiptGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.items = items;
        this.comment = comment;
    }

    @Override
    public  CheckInfo<InventoryOverflowReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getInventoryOverflowReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new InventoryOverflowDetailPane(this);
    }

    @Override
    public InventoryOverflowListVO toListVO() {
        return new InventoryOverflowListVO(this);
    }

    @Override
    protected String getCodeName() {
        return "BYD";
    }

    @Override
    public InventoryOverflowReceiptPO toPO() {
        InventoryOverflowReceiptPO result = toReceiptPO(InventoryOverflowReceiptPO.class);
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
            itemPO.setInventoryNum(item.getInventoryNum());
            itemPO.setFactNumber(item.getFactNum());
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
