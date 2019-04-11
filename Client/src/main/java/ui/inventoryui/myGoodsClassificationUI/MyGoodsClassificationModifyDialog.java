package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import ui.common.BoardController;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.rmi.RemoteException;

public class MyGoodsClassificationModifyDialog extends JFXDialog {
    public MyGoodsClassificationModifyDialog(RecursiveGoodsClassificationVO vo) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(260.0);

        jfxDialogLayout.setHeading(new Label("修改分类名称"));
        JFXTextField nameField = new JFXTextField();

        jfxDialogLayout.setBody(nameField);
        JFXButton button = new JFXButton("保存");
        button.setOnAction(e -> {
            // update name
            vo.setName(nameField.getText());

            modifyTask(vo);
        });
        jfxDialogLayout.setActions(button);


        setDialogContainer(PaneFactory.getMainPane());
        setContent(jfxDialogLayout);
    }

    private void modifyTask(RecursiveGoodsClassificationVO vo) {
        BoardController.getBoardController().Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("修改失败，是否重试？", () -> {
            modifyTask(vo);
        }, () -> {
            BoardController.getBoardController().refresh();
        });

        new Thread(new GetTask(() -> {
            BoardController.getBoardController().refresh();
        }, dialog, woid -> {
            this.close();
            try {
                MyGoodsClassificationblService goodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
                goodsClassificationblService.update(vo);
                return true;
            } catch (RemoteException e1) {
                e1.printStackTrace();
                return false;
            }
        })).start();
    }
}
