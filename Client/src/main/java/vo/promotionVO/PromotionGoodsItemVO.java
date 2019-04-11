package vo.promotionVO;

import businesslogic.blServiceFactory.MyServiceFactory;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import po.promotionPO.PromotionGoodsItemPO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PromotionGoodsItemVO extends RecursiveTreeObject<PromotionGoodsItemVO> {
    private String id;
    private String name;
    private double unitPrice;
    private IntegerProperty num = new SimpleIntegerProperty();

    public PromotionGoodsItemVO() {
    }

    public PromotionGoodsItemVO(PromotionGoodsItemPO promotionGoodsItemPO) {
        id = promotionGoodsItemPO.getId();
        num = new SimpleIntegerProperty(promotionGoodsItemPO.getNum());
        try {
            GoodsVO goodsVO = MyServiceFactory.getGoodsSearchInfo().getGoodById(id);
            name = goodsVO.getGoodName();
            unitPrice = goodsVO.getSalePrice();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            // 不管了，就这么写了…
            e.printStackTrace();
            name = "连接错误";
            unitPrice = -99999;
        }
    }

    public PromotionGoodsItemVO(GoodsVO goodsVO) {
        id = goodsVO.getId();
        name = goodsVO.getGoodName();
        unitPrice = goodsVO.getSalePrice();
    }

    public PromotionGoodsItemVO(String id, String name, int unitPrice, IntegerProperty num) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.num = num;
    }

    /**
     * toPO and toOtherVO
     * */

    public PromotionGoodsItemPO toPO() {
        return new PromotionGoodsItemPO(id, num.get());
    }

    public ReceiptGoodsItemVO toReceiptGoodsItemVO() {
        ReceiptGoodsItemVO result = new ReceiptGoodsItemVO();
        result.setGoodsId(id);
        result.setGoodsName(name);
        result.setPrice(unitPrice);
        result.setSendNum(num.get());
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }
}
