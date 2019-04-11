package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.FactoryController;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import ui.common.BoardController;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.rmi.RemoteException;
import java.util.List;

public class MyGoodsClassificationAddDialog extends JFXDialog {
    public MyGoodsClassificationAddDialog(RecursiveGoodsClassificationVO vo) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(260.0);

        jfxDialogLayout.setHeading(new Label("新增分类名"));
        JFXTextField nameField = new JFXTextField();

        jfxDialogLayout.setBody(nameField);
        JFXButton button = new JFXButton("保存");
        button.setOnAction(e -> {
            // construct new GoodsClassificationVO
            RecursiveGoodsClassificationVO newGoodsClassificationVO = new RecursiveGoodsClassificationVO();

            String classifyName = nameField.getText();
            newGoodsClassificationVO.setName(classifyName);


            newGoodsClassificationVO.setId(vo.getNextChildrenId());

            newGoodsClassificationVO.setFather(vo);

            // update father
            List<RecursiveGoodsClassificationVO> children = vo.getChildren();
            children.add(newGoodsClassificationVO);


            // save
            saveTask(vo, newGoodsClassificationVO);
        });
        jfxDialogLayout.setActions(button);


        setDialogContainer(PaneFactory.getMainPane());
        setContent(jfxDialogLayout);
    }

    private void saveTask(RecursiveGoodsClassificationVO fatherVO, RecursiveGoodsClassificationVO newVO) {
        BoardController.getBoardController().Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("插入失败，是否重试？", () -> {
            saveTask(fatherVO, newVO);
        }, () -> {
            BoardController.getBoardController().refresh();
        });

        new Thread(new GetTask(() -> {
            BoardController.getBoardController().refresh();
        }, dialog, woid -> {
            this.close();
            try {
                // TODO 其实写这种东西的时候感觉根本就是瞎写，异常根本不会处理，如果是添加以后出现的异常就不应该能重试。
                MyGoodsClassificationblService goodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
                goodsClassificationblService.add(newVO);
                goodsClassificationblService.update(fatherVO);
                return true;
            } catch (RemoteException e1) {
                e1.printStackTrace();
                FactoryController.clearAllSavedService();
                return false;
            }
        })).start();
    }
}
