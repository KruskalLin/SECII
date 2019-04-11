package ui.common;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.util.*;

public class BoardController {
    /**
     * inner class
     */
    private static class HistoryRecord { // 希望所有的和HistoryRecord有关的都做到BoardController里面
        // 吐糟：哪里observe了…
        private static ObservableList<RefreshablePane> record = FXCollections.observableArrayList();
        private static int index = -1;
        private static SimpleBooleanProperty canBack = new SimpleBooleanProperty(false);
        private static SimpleBooleanProperty canForward = new SimpleBooleanProperty(false);

        // TODO 我觉得把index改成IntegerProperty，然后把这个方法改成static{index.addListener}比较好。但是…再说吧
        private static void setBackAndForwardProperty() {
            canBack.setValue(index > 0);
            canForward.setValue(index != record.size() - 1);
        }


        static boolean addRecord(RefreshablePane pane) {
            if (index == -1) {
                record.add(pane);
                index++;
                return false; // 返回值只是为了是否有动画，因此false符合反回值原意
            }
            if (record.get(index).getId().equals(pane.getId())) {
                return false;
            }

            if (index != (record.size() - 1)) {
                record.remove(index + 1, record.size());
            }

            record.add(pane);
            index++;

            setBackAndForwardProperty();
            return true;
        }


        static RefreshablePane pop() {
            index--;
            setBackAndForwardProperty();
            return record.get(index);
        }

        // 这个叫push真的好吗？又不是stack
        static RefreshablePane push() {
            index++;
            setBackAndForwardProperty();
            return record.get(index);
        }

        static void removeAllNodesAfterIndex() {
            if (index == record.size() - 1) {
                return;
            }
            record = FXCollections.observableArrayList(record.subList(0, index + 1));
            setBackAndForwardProperty();
        }
    }

    /**
     * From here
     */
    @FXML
    private StackPane board;

    private PaneSwitchAnimation paneSwitchAnimation;

    private static BoardController myBoardController;

    private AnchorPane savePane;

    public BoardController() {
        BoardController.myBoardController = this;
    }

    public void switchTo(RefreshablePane pane) {
        if (paneSwitchAnimation == null) { // 这样写还是很恶心，因为controller在构造方法里不能用board，怪不得宇超那样写
            paneSwitchAnimation = new PaneSwitchAnimation(Duration.millis(250), board);
            paneSwitchAnimation.setAnimation(ContainerAnimations.FADE);
        }
        Platform.runLater(() -> {
            if (HistoryRecord.addRecord(pane)) {
                paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_LEFT);
                paneSwitchAnimation.setNode(pane);
            } else {
                board.getChildren().setAll(pane);
            }
        });
    }

    // 这两个还好暂时不override，refresh也还行，现在只是为了不要忘了所以放这。
    // 但是可以封装吗？
    public void Loading() {
        if (!board.getChildren().isEmpty() && (board.getChildren().get(0) instanceof Loading)) {
            return;
        }
        if (!board.getChildren().isEmpty()) {
            savePane = (AnchorPane) board.getChildren().get(0);
        }
        Loading loading = new Loading();
        board.getChildren().setAll(loading);
        loading.setTranslateX(280);
    }

    // TODO 我觉得return几乎可以去掉了。
    public void Ret() {
        if (savePane != null) {
            board.getChildren().setAll(savePane);
        }
    }

    public void refresh() {
        if (board.getChildren().get(0) instanceof RefreshablePane) {
            ((RefreshablePane) board.getChildren().get(0)).refresh(false);
        } else {
            ((RefreshablePane) savePane).refresh(false);
        }

    }

    public void goBack() {
        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_RIGHT);
        paneSwitchAnimation.setNode(HistoryRecord.pop());
        refresh();
    }

    public void goBackAndNeverReturn() {
        RefreshablePane destination = HistoryRecord.pop();
        HistoryRecord.removeAllNodesAfterIndex();

        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_RIGHT);
        paneSwitchAnimation.setNode(destination);
        refresh();
    }

    public void goForward() {
        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_LEFT);
        paneSwitchAnimation.setNode(HistoryRecord.push());
        refresh();
    }

    // 像这种是专供给MyTopBar用的。
    // 好像上面两个也是…
    public BooleanProperty canBackProperty() {
        return HistoryRecord.canBack;
    }

    public BooleanProperty canForwardProperty() {
        return HistoryRecord.canForward;
    }


    /**
     * static methods
     */
    public static BoardController getBoardController() {
        return myBoardController;
    }


}
