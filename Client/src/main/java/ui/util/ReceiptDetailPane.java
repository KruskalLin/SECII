package ui.util;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;


import static ui.util.ValidatorDecorator.RequireValid;

public abstract class ReceiptDetailPane<T> extends RefreshablePane {


    protected T vo;

    protected BoardController boardController;

    protected SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    protected StackPane mainpane;

    @FXML
    protected ModifyButton modify;
    @FXML
    protected MaterialDesignIconView pen;
    @FXML
    protected JFXButton save;

    @FXML
    protected JFXButton reject;

    @FXML
    protected JFXButton delete;

    private boolean check;
    protected SimpleBooleanProperty updateState = new SimpleBooleanProperty();

    public ReceiptDetailPane(String url,boolean check) {
        super();
        this.check = check;
        boardController = BoardController.getBoardController();
        mainpane = PaneFactory.getMainPane();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateState.set(false);
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b,o,n)->{if(!n){
                    if(valid()) {
                        modify.modifyProperty().set(false);
                    }else{
                        modify.modifyProperty().set(true);
                    }
                }
            });
        if(!check) {
            save.visibleProperty().bind(modifyState);
        }else{
            save.setText("Accept");
            save.setStyle("-fx-background-color: rgb(56,174,103)");
            reject.setVisible(true);
            delete.setVisible(false);
        }
    }

    public ReceiptDetailPane(String url){
        super();
        boardController = BoardController.getBoardController();
        mainpane = PaneFactory.getMainPane();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        save.setText("Add");
        updateState.set(true);
        modify.setVisible(false);
        delete.setVisible(false);
    }






    @FXML
    public abstract void delete();


    public void switchPane(boolean toSwtich) {
//        if (toSwtich == true) {
            boardController.switchTo(this);
//        } else {
//            boardController.setAll(this);
//        }
    }

    @FXML
    public void save(){
        if(!check){
            saveReceipt();
        }else{
            accept();
        }
    }
    public void accept(){

    }

    public void saveReceipt(){
        modify.modifyProperty().set(false);
        if(valid()){
            savePendingReceipt();
        }else{
            saveDraftReceipt();
        }
    }

    public abstract void savePendingReceipt();
    public abstract void saveDraftReceipt();

    public abstract boolean valid();

//    public void setBack(){ // TODO 这个setBack可能会有点用
//        boardController.setRightAnimation();
//        boardController.historicalSwitchTo((RefreshablePane) HistoricalRecord.pop());
//        boardController.refresh();
//        HistoricalRecord.removeAndPop();
//    }

}
