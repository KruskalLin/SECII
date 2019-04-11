package ui.messageui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ui.common.mixer.FXMLLoadableMixer;
import ui.util.CircleImageView;
import ui.util.TokenLabel;
import vo.ReceiptMessageVO;
import vo.UserVO;

public class MyMessageHBox extends HBox implements FXMLLoadableMixer{
    @FXML
    private Label userLabel, label1, label2;
    @FXML
    private TokenLabel tokenLabel;
    @FXML
    private CircleImageView civ;

    public MyMessageHBox(ReceiptMessageVO messageVO) {
        load();
        userLabel.setText(messageVO.getFromUser().getUsername());
        tokenLabel.setText(messageVO.getCreateTime().toString());
        label1.setText(messageVO.getPrompt());
        label2.setText("单据编号：" + messageVO.getReceiptVO().getId());
    }

    @Override
    public String publicGetURL() {
        return "/messageui/messagehbox.fxml";
    }

}
