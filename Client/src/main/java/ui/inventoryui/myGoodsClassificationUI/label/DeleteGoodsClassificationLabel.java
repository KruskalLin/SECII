package ui.inventoryui.myGoodsClassificationUI.label;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.FactoryController;
import businesslogic.blServiceFactory.MyblServiceFactory;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.common.popupList.PopUpListLabel;
import ui.util.GetTask;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.rmi.RemoteException;

public class DeleteGoodsClassificationLabel extends PopUpListLabel implements FXMLLoadableMixer {
    private RecursiveGoodsClassificationVO goodsClassificationVO;

    public DeleteGoodsClassificationLabel(RecursiveGoodsClassificationVO goodsClassificationVO) {
        this.goodsClassificationVO = goodsClassificationVO;
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myDeleteGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
        if (goodsClassificationVO.getId().equals("root")) {
            new MyOneButtonDialog("不可删除商品总分类").show(); // 不然的话如果总分类下面没有商品就把总分类也删除了
        } else if (!goodsClassificationVO.getChildren().isEmpty()) {
            new MyOneButtonDialog("不可删除，当前分类下有子分类").show();
        } else if (!goodsClassificationVO.getGoods().isEmpty()) {
            new MyOneButtonDialog("不可删除，当前分类下有商品").show();
        } else {
            MyTwoButtonDialog confirmDialog = new MyTwoButtonDialog("请确认删除");
            confirmDialog.setButtonOneAction(() -> {
                // update father
                goodsClassificationVO.getFather().getChildren().remove(goodsClassificationVO);
                deleteTask(confirmDialog);
            });
            confirmDialog.show();
        }
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
                // delete this vo;
                MyGoodsClassificationblService goodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
                goodsClassificationblService.delete(goodsClassificationVO);

                // update father
                goodsClassificationblService.update(goodsClassificationVO.getFather());
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                FactoryController.clearAllSavedService();
                return false;
            }
        })).start();
    }
}
