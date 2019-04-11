package ui.mainui;

import javafx.scene.control.Label;
import ui.salesui.GoodsList;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.ArrayList;

public class TotalPromotionCard extends PromotionCard {
    Label label = new Label("赠品");
    GiveGoodsList giveGoodsList;

    public TotalPromotionCard() {
        super();
        this.setStyle("-fx-background-color: linear-gradient(to right bottom,rgb(253,139,166),rgb(199,146,245))");
        title.setText("总价降价");
        num.setText("3");
        this.getStylesheets().add("/css/memberPromotion.css");
        label.setLayoutY(70);
        label.setLayoutX(20);
        label.getStyleClass().add("myLabel");
        label.setStyle("-fx-font-weight: bold;");
        this.getChildren().add(label);
        giveGoodsList = new GiveGoodsList();
        giveGoodsList.setPrefWidth(190);
        giveGoodsList.setPrefHeight(150);
        giveGoodsList.setLayoutY(90);
        this.getChildren().add(giveGoodsList);


        ArrayList<ReceiptGoodsItemVO> arrayList = new ArrayList<ReceiptGoodsItemVO>();
        ReceiptGoodsItemVO receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setSendNum(124);
        receiptGoodsItemVO.setGoodsId("???");
        receiptGoodsItemVO.setGoodsName("hahaha");
        arrayList.add(receiptGoodsItemVO);
        receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setSendNum(124);
        receiptGoodsItemVO.setGoodsId("???");
        receiptGoodsItemVO.setGoodsName("hahaha");
        arrayList.add(receiptGoodsItemVO);
        receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setSendNum(124);
        receiptGoodsItemVO.setGoodsId("???");
        receiptGoodsItemVO.setGoodsName("hahaha");
        arrayList.add(receiptGoodsItemVO);
        receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setSendNum(124);
        receiptGoodsItemVO.setGoodsId("???");
        receiptGoodsItemVO.setGoodsName("hahaha");
        arrayList.add(receiptGoodsItemVO);
        receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setSendNum(124);
        receiptGoodsItemVO.setGoodsId("???");
        receiptGoodsItemVO.setGoodsName("hahaha");
        arrayList.add(receiptGoodsItemVO);
        receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setSendNum(124);
        receiptGoodsItemVO.setGoodsId("???");
        receiptGoodsItemVO.setGoodsName("hahaha");
        arrayList.add(receiptGoodsItemVO);
        this.setList(arrayList);
    }

    public void setList(ArrayList<ReceiptGoodsItemVO> arrayList) {
        giveGoodsList.setList(arrayList);
    }

}
