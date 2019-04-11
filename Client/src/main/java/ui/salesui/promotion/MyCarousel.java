package ui.salesui.promotion;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.util.Duration;
import ui.common.bigPane.FXMLAnchorPane;
import ui.mainui.*;
import vo.promotionVO.PromotionVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.util.ArrayList;

public class MyCarousel extends FXMLAnchorPane {
    private CardHolder[] nodes;
    private ArrayList<PromotionCard> arrayList = new ArrayList<>();
    private int index = 0;
    private Duration duration = new Duration(500);
    private int size;

    // TODO 这个也设计得极丑
    public MyCarousel(ArrayList<PromotionVO> promotionVOS, JFXDialog dialog, SalesSellReceiptVO receiptVO, Runnable saveTask) {
        super();
        this.setPrefSize(600, 400);
        for (PromotionVO promotionVO : promotionVOS) {
            PromotionCard card = promotionVO.getPromotionCard();
            card.choose.setOnAction(e -> {
                card.getPromotionVO().modifySalesSell(receiptVO);
                dialog.close();
                saveTask.run();

            });
            arrayList.add(card);
        }

        init();
    }

    private void init() {
        size = arrayList.size();
        nodes = new CardHolder[size];
        if (arrayList.size() == 1) {
            nodes[0] = new CardHolder(duration, arrayList.get(0), this, CardPlace.Middle);
        } else if (arrayList.size() > 1) {
            for (int i = arrayList.size() - 1; i >= 1; i--) {
                nodes[i] = new CardHolder(duration, arrayList.get(i), this, CardPlace.Right);
            }
            nodes[0] = new CardHolder(duration, arrayList.get(0), this, CardPlace.Middle);
        }
    }


    @Override
    protected String getURL() {
        return "/salesui/carousel.fxml";
    }

    @FXML
    public void left() {
        if (index + 1 < size) {
            nodes[index].MTL();
            index++;
            nodes[index].RTM();
        }
    }


    @FXML
    public void right() {
        if (index - 1 >= 0) {
            nodes[index].MTR();
            index--;
            nodes[index].LTM();
        }
    }
}
