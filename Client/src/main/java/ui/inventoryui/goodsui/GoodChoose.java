package ui.inventoryui.goodsui;

        import businesslogic.goodsbl.Goodsbl;
        import com.jfoenix.controls.JFXButton;
        import com.jfoenix.controls.JFXDialog;
        import com.jfoenix.controls.JFXDialogLayout;
        import com.jfoenix.controls.JFXTextField;
        import javafx.beans.property.IntegerProperty;
        import javafx.collections.ObservableList;
        import javafx.scene.control.Label;
        import ui.inventoryui.GoodsChooseInfo;
        import ui.util.PaneFactory;
        import vo.inventoryVO.GoodsVO;
        import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

        import java.net.MalformedURLException;
        import java.rmi.NotBoundException;
        import java.rmi.RemoteException;
        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

public class GoodChoose{
    List<String> goodsId;

    Goodsbl goodsbl;

    public GoodChoose() throws RemoteException, NotBoundException, MalformedURLException {
        this.goodsbl = new Goodsbl();
    }


    public List<String> choose(ObservableList<ReceiptGoodsItemVO> observableList, IntegerProperty integerProperty) {
        ChoosePane choosePane = new ChoosePane();
        try {
            goodsbl = new Goodsbl();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(220.0);
        jfxDialogLayout.setBody(choosePane);
        JFXButton save = new JFXButton("Save");
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("确定");

        choosePane.setDialog(dialog);

        //  GoodChooseui_stub stub = new GoodChooseui_stub();
        Set<GoodsVO> goodsList = null;
        try {
            goodsList = goodsbl.show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Set<String> sourceList = new HashSet<>();

        for (GoodsVO vo:goodsList) {
            sourceList.add(vo.getId()+" "+vo.getGoodName());
        }

        choosePane.setObservableList(sourceList);

        button.setOnAction(e -> {
            goodsId = choosePane.getChooseList();

            for (String str:goodsId) {
                String id = str.split(" ")[0];

                GoodsVO goodsVO = null;
                try {
                    goodsVO = goodsbl.showDetail(id);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

                ReceiptGoodsItemVO vo = new ReceiptGoodsItemVO();
                vo.setGoodsName(goodsVO.getGoodName());
                vo.setGoodsId(id);
                vo.setInventoryNum(goodsVO.getInventoryNum());
                vo.setPrice(goodsVO.getRecentSalePrice());

                observableList.add(vo);
            }
            integerProperty.setValue(1);
            dialog.close();
        });
        jfxDialogLayout.setActions(button);


        dialog.show();

        return goodsId;
    }

    public void setGoodsId(List<String> goodsId) {
        this.goodsId = goodsId;
    }
}
