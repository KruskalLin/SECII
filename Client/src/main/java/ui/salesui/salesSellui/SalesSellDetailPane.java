package ui.salesui.salesSellui;

import blService.genericblService.ReceiptblService;
import blService.promotionblService.PromotionInfo;
import blService.salesblService.SalesSellblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXDialog;
import exceptions.ItemNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.mainui.Carousel;
import ui.salesui.SalesReceiptPane;
import ui.salesui.promotion.MyCarousel;
import ui.util.GetTask;
import ui.util.PaneFactory;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.promotionVO.PromotionVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SalesSellDetailPane extends SalesReceiptPane<SalesSellReceiptVO> {

    public SalesSellDetailPane() {
    }

    public SalesSellDetailPane(SalesSellReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    public void initiate() {
        super.initiate();
    }

    @Override
    protected Class<? extends ReceiptblService<SalesSellReceiptVO>> getServiceClass() {
        return SalesSellblService.class;
    }

    @Override
    protected boolean validate() {
        if (super.validate()) {
            try {
                for (ListGoodsItemVO listGoodsItemVO : itemTreeTable.getList()) {
                    GoodsVO goodsVO = MyServiceFactory.getGoodsblService().showDetail(listGoodsItemVO.getGoodsId());
                    if (goodsVO.getInventoryNum() < listGoodsItemVO.getGoodsNum()) {
                        new MyOneButtonDialog(listGoodsItemVO.getGoodsName() + "销售数量大于库存数量").show();
                        return false;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                new MyOneButtonDialog("连接错误").show();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void updateReceiptVO() { // TODO 这里一个membertVO可以的东西用这么多…写的真烂…
        super.updateReceiptVO();
        receiptVO.setMemberLevel(receiptVO.getMemberVO().getDegree());
    }

    @Override
    protected void save() {
//        if (validate()) {
//            receiptVO.setReceiptState(ReceiptState.PENDING);
//            try { // 这样的try catch不行
//                ArrayList<PromotionVO> promotions = promotionInfo.getMatch(receiptVO);
//                // 这个目前只可能是0个元素或者1个元素
//
//                if (!promotions.isEmpty()) {
//                    PromotionVO promotionVO = promotions.get(0);
//
//                    Label label = promotionVO.getInfoLabel();
//                    String content = label.getText();
//
//                    new MyOneButtonDialog(content, () -> {
//                        promotionVO.modifySalesSell(receiptVO);
//                        saveTask();
//                    }).show();
//                }
//
//                saveTask();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            saveTask();
//        }
        if (validate()) {
            updateReceiptVO();

            try {
                ArrayList<PromotionVO> promotionVOS = MyServiceFactory.getPromotionInfo().getMatch(receiptVO);
                JFXDialog dialog1 = new JFXDialog();
                dialog1.setTransitionType(JFXDialog.DialogTransition.CENTER);
                dialog1.setContent(new MyCarousel(promotionVOS, dialog1, receiptVO, this::saveTask));
                dialog1.setDialogContainer(PaneFactory.getMainPane());
                dialog1.show();
            } catch (RemoteException e) {
                e.printStackTrace();
                new MyOneButtonDialog("连接错误").show();
            }
        }
    }

    private void saveTask() {
        BoardController.getBoardController().Loading();

        StringProperty prompt = new SimpleStringProperty();
        new Thread(new GetTask(() -> {
            new MyOneButtonDialog("保存成功", () -> BoardController.getBoardController().goBack()).show();
        }, new MyTwoButtonDialog(prompt.get(), () -> BoardController.getBoardController().goBack()), woid -> {
            try {
                ((SalesSellblService) MyblServiceFactory.getService(SalesSellblService.class)).submit(receiptVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("单据不存在，是否返回单据列表"); // 这是不可能的吧…除了自己没人会删，不过要是考虑多端登陆的情况…
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("连接错误");
                return false;
            }
        })).start();
    }
}
