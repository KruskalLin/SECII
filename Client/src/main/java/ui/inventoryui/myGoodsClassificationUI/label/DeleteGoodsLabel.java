package ui.inventoryui.myGoodsClassificationUI.label;

import blService.goodsblService.GoodsblService;
import businesslogic.blServiceFactory.FactoryController;
import businesslogic.blServiceFactory.MyblServiceFactory;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.util.GetTask;
import vo.inventoryVO.GoodsVO;

import java.rmi.RemoteException;

public class DeleteGoodsLabel extends PopUpListLabel implements FXMLLoadableMixer {
    private GoodsVO goodsVO;

    public DeleteGoodsLabel(GoodsVO goodsVO) {
        this.goodsVO = goodsVO;
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myDeleteGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        if (goodsVO.getRecentPurPrice() != 0) { // 表明是购买过的，不可删除
            new MyOneButtonDialog("已购买过该高品，不可删除").show();
        } else {
            MyTwoButtonDialog confirmDialog = new MyTwoButtonDialog("请确认删除");
            confirmDialog.setButtonOneAction(() -> {
                deleteTask(confirmDialog);
            });
            confirmDialog.show();
        }
//        System.out.println("Delete Goods Label");
    }

    private void deleteTask(MyTwoButtonDialog confirmDialog) {
        BoardController.getBoardController().Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("删除失败，是否重试？", () -> {
            deleteTask(confirmDialog);
        }, () -> {
            BoardController.getBoardController().Ret();
        });

        new Thread(new GetTask(() -> {
            BoardController.getBoardController().refresh();
        }, dialog, woid -> {
            confirmDialog.close();
            try {
                GoodsblService goodsblService = MyblServiceFactory.getService(GoodsblService.class);
                goodsblService.deleteGoods(goodsVO);

                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                FactoryController.clearAllSavedService();
                return false;
            }
        })).start();
    }
}
