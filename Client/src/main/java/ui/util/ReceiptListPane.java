package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import vo.abstractVO.SelectableVO;

import java.util.Set;

public abstract class ReceiptListPane<T extends SelectableVO<T>> extends RefreshablePane {

    protected ReceiptTreeTable<T> receiptTreeTable;

    @FXML
    protected BorderPane borderpane;

    protected Set<T> set;

    protected BoardController boardController;

    protected Pagination pagination;

    protected StackPane mainpane;

    public boolean historyAdd = false;

    protected PopOver filterPopOver = new PopOver();

    @FXML
    protected JFXButton filter;

    @FXML
    protected JFXRippler search;

    @FXML
    protected JFXTextField searchField;

    public ReceiptListPane(String url) throws Exception{
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.boardController = BoardController.getBoardController();
        this.mainpane = PaneFactory.getMainPane();
        pagination = new Pagination();
        pagination.currentPageIndexProperty().addListener((b,o,n)->{
            receiptTreeTable.createPage(n.intValue());
        });
        borderpane.setBottom(pagination);


    }


//    public void setFilter(Node filter){
//        filterPopOver.setDetachable(false);
//        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
//        filterPopOver.setContentNode(filter);
//        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
//    }


    @FXML
    public abstract void deleteList();

    @FXML
    public abstract void search();

    @FXML
    public abstract void add();


    public void switchPane(boolean toSwtich){
//        if(toSwtich==true){
            boardController.switchTo(this);
//        }else{
//            if(historyAdd){
//                System.out.println("nishidasabi");
//                HistoricalRecord.addPane(this);
//                historyAdd=false;
//            }
//            boardController.setAll(this);
//        }
    }


}
