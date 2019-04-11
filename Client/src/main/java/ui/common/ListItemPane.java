package ui.common;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import ui.common.dialog.MyOneButtonDialog;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import vo.ListGoodsItemVO;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.NumberValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class ListItemPane extends MyListItemPane<ListGoodsItemVO> {
    @FXML
    Label goodId;
    @FXML
    Label goodType;
    @FXML
    Label goodName;
    @FXML
    Label sum;
    @FXML
    JFXTextField num, price;
    @FXML
    Label current;
    @FXML
    JFXTextArea comment;


    public ListItemPane(ListGoodsItemVO listGoodsItemVO, MyListItemTablePane myListItmeTablePane) {
        super(getURL(), listGoodsItemVO, myListItmeTablePane);
        goodId.setText("" + listGoodsItemVO.getGoodsId());
        goodName.setText(listGoodsItemVO.getGoodsName());
        goodType.setText(listGoodsItemVO.getType());
        current.setText(listGoodsItemVO.getInventoryNum() + "");
        listGoodsItemVO.inventoryNumProperty().addListener((observable, oldValue, newValue) -> {
            current.setText(newValue.intValue() + "");
        });


        NumberValid(num);
        DoubleValid(price);

        num.setText("" + listGoodsItemVO.getGoodsNum());
        price.setText("" + listGoodsItemVO.getPrice());

        sum.setText("" + listGoodsItemVO.getSum());
        comment.setText(listGoodsItemVO.getComment());
        comment.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.ENTER) {
                vo.setComment(comment.getText());
            }
        });

    }

    public static String getURL() {
        return "/stockui/listitempane.fxml";
    }


    @Override
    public void saveItem() {
        vo.setGoodsNum(Integer.parseInt(num.getText()));
        vo.setPrice(Double.parseDouble(price.getText()));

        System.out.println(vo.getGoodsNum() + "   /.." + vo.getPrice());
        sum.setText("" + vo.getSum());
        vo.setComment(comment.getText());
        myListItmeTablePane.refresh();
    }

    @Override
    public void deleteItem() {
        myListItmeTablePane.remove(vo);
    }

    @Override
    public boolean validate() {
        if (num.getText() == null || num.getText().equals("") || !num.validate()) {
            Platform.runLater(() -> {
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "错误", "输入错误", "接受");
                oneButtonDialog.show();
                oneButtonDialog.setButtonOne(() -> {
                });
            });
            return false;
        }
        if (Integer.parseInt(num.getText()) <= 0) {
            Platform.runLater(() -> {
                new MyOneButtonDialog("商品数量必须为正数").show();
            });
            return false;
        }
//        if (Integer.parseInt(num.getText()) > Integer.parseInt(current.getText())) {
//            Platform.runLater(() -> {
//                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(), "错误", "输入超过库存", "接受");
//                oneButtonDialog.show();
//                oneButtonDialog.setButtonOne(() -> {
//                });
//            });
//            return false;
//        }
        // by 连。这个是所有共用的，但是销售退货单和进货单不需要查库存。所以不在这里检查，而在各自的ReceiptDetailPane里面。
        if (! price.validate() || Double.parseDouble(price.getText()) < 0) {
            Platform.runLater(() -> {
                new MyOneButtonDialog("价格必须为正数").show();
            });
            return false;
        }
        return true;
    }
}
