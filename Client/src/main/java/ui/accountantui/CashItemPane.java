package ui.accountantui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import vo.billReceiptVO.CashItemVO;

import static ui.util.ValidatorDecorator.isDouble;


public class CashItemPane extends AnchorPane {

    CashItemVO cashItemVO;

    StackPane mainpane;

    ObservableList<CashItemVO> observableList;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField price;

    @FXML
    JFXTextField comment;

    JFXDialog jfxDialog;

    CashItemTreeTable cashItemTreeTable;

    public CashItemPane(CashItemVO cashItemVO, ObservableList<CashItemVO> observableList,CashItemTreeTable cashItemTreeTable) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/CashItemPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cashItemVO = cashItemVO;
        this.cashItemTreeTable = cashItemTreeTable;

        name.setText(cashItemVO.getName());
        price.setText(""+cashItemVO.getPrice());
        comment.setText(cashItemVO.getComment());


        name.setOnKeyPressed(t->{
            if(t.getCode()== KeyCode.ENTER){
                cashItemVO.setName(cashItemVO.getName());
                name.setText(""+cashItemVO.getName());
            }
        });

        price.setOnKeyPressed(t->{
            if(t.getCode()==KeyCode.ENTER){
                cashItemVO.setPrice(Double.parseDouble(price.getText()));
                price.setText(""+cashItemVO.getPrice());
            }
        });

        comment.setOnKeyPressed(t->{
            if(t.getCode()==KeyCode.ENTER){
                cashItemVO.setComment(comment.getText());
                comment.setText(cashItemVO.getComment());
            }
        });

        this.mainpane = PaneFactory.getMainPane();
        this.observableList=observableList;

    }
    public void setDialog(JFXDialog dialog) {
        this.jfxDialog = dialog;
    }

    @FXML
    public void save() {
        if(!isDouble(price.getText())||Double.parseDouble(price.getText())<0){
            OneButtonDialog oneButtonDialog  = new OneButtonDialog(PaneFactory.getMainPane(),"","金额错误","继续");
            oneButtonDialog.setButtonOne(()->{});
            oneButtonDialog.show();
        }else{
                cashItemVO.setName(name.getText());
                cashItemVO.setPrice(Double.parseDouble(price.getText()));
                cashItemVO.setComment(comment.getText());
                CashItemVO temp  =new CashItemVO("1",1,"1");
                cashItemTreeTable.add(temp);
                cashItemTreeTable.remove(temp);
                jfxDialog.close();
        }
    }

    @FXML
    public void delete() {
        observableList.remove(this.cashItemVO);
        jfxDialog.close();
    }

}