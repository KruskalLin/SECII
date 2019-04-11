package ui.accountantui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;
import ui.common.MyTopBar;
import ui.common.ChangePaneLabel;

public class MyAccountantUIController {
    @FXML
    private JFXListView<ChangePaneLabel> navigation;
    @FXML
    private StackPane mainpane;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {
//        PaneFactory.setMainPane(mainpane);

//        BoardController.setBoardController(boardController);
//        boardController = BoardController.getBoardController();
        // 这样再set回去，以后从boardController里面拿的就都是MyBoardController了，但是以后仍然需要强转
//        BoardController.setBoardController(boardController);

        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉
        bar.setBoardController(boardController);


        new MyPaymentReceiptListPane().refresh(false);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            newVal.getPane().refresh(true);
        });
    }
}
