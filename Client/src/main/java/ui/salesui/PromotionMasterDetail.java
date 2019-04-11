package ui.salesui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.MasterDetailPane;
import ui.common.bigPane.FXMLAnchorPane;
import util.PromotionState;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.PromotionVO;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PromotionMasterDetail extends FXMLAnchorPane {


    @FXML
    MasterDetailPane pane;

    PromotionList promotionList = new PromotionList();

    PromotionDetail promotionDetail = new PromotionDetail();

    @Override
    protected String getURL() {
        return "/salesui/choosePromotion.fxml";
    }

    public PromotionMasterDetail(){
        super();
        pane.setMasterNode(promotionList);
        pane.setDetailNode(promotionDetail);

        MemberPromotionVO promotionVO = new MemberPromotionVO();
        promotionVO.setTokenAmount(111);
        promotionVO.setDiscountFraction(123);
        promotionVO.setRequiredLevel(1);
        promotionVO.setPromotionState(PromotionState.SAVED);
        promotionVO.setLastModifiedTime(LocalDateTime.now());
        promotionVO.setBeginTime(LocalDateTime.now());
        promotionVO.setComment("");
        promotionVO.setId("123123");
        ArrayList<PromotionVO> arrayList = new ArrayList<>();

        ArrayList<PromotionGoodsItemVO> arrayList1 = new ArrayList<>();
        PromotionGoodsItemVO promotionGoodsItemVO = new PromotionGoodsItemVO();
        promotionGoodsItemVO.setId("123");
        promotionGoodsItemVO.setName("123123");
        promotionGoodsItemVO.setNum(123);
        promotionGoodsItemVO.setUnitPrice(13);
        arrayList1.add(promotionGoodsItemVO);
        promotionVO.setGifts(arrayList1);

        arrayList.add(promotionVO);
        arrayList.add(promotionVO);
        arrayList.add(promotionVO);
        arrayList.add(promotionVO);
        arrayList.add(promotionVO);
        arrayList.add(promotionVO);
        arrayList.add(promotionVO);


        promotionList.typeProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                promotionDetail.setPromotion(promotionList.getPromotionVO());
            }
        });

        promotionList.setList(arrayList);
    }


}
