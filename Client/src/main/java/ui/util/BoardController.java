//package ui.util;
//
//
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.layout.*;
//
//
//public class BoardController {
//
//    @FXML
//    public StackPane board;
//
//    protected PaneSwitchAnimation paneSwitchAnimation;
//
//    AnchorPane anchorPane;
//
//    private static BoardController boardController;
//
//    public void setPaneSwitchAnimation(PaneSwitchAnimation paneSwitchAnimation) {
//        this.paneSwitchAnimation = paneSwitchAnimation;
//    }
//
//    public void refresh(){
//        ((RefreshablePane)board.getChildren().get(0)).refresh(false);
//    }
//
//
//    public void switchTo(RefreshablePane pane) {
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if (HistoricalRecord.addRecord(pane)) {
//                        setLeftAnimation();
//                        paneSwitchAnimation.setNode(pane);
//                    }else{
//                        board.getChildren().setAll(pane);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void Loading() {
//        if(!board.getChildren().isEmpty())
//        anchorPane = (AnchorPane) board.getChildren().get(0);
//        Loading loading = new Loading();
//        board.getChildren().setAll(loading);
//        loading.setTranslateX(280);
//    }
//
//    public void setAll(Node node){ board.getChildren().setAll(node);}
//
//    public void Ret(){
//        if(anchorPane!=null)
//        board.getChildren().setAll(anchorPane);
//    }
//
//    public void setLeftAnimation() {
//        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_LEFT);
//    }
//
//    public void setRightAnimation() {
//        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_RIGHT);
//    }
//
//    public void historicalSwitchTo(AnchorPane pane) {
//        paneSwitchAnimation.setNode(pane);
//    }
//
//
//    public String getId() {
//        return board.getChildren().get(0).getId();
//    }
//
//
//    public static BoardController getBoardController() {
//        return boardController;
//    }
//
//    public static void setBoardController(BoardController boardController) {
//        BoardController.boardController = boardController;
//    }
//}
