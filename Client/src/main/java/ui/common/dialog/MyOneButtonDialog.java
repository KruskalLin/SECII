package ui.common.dialog;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import ui.util.PaneFactory;

public class MyOneButtonDialog extends JFXDialog {
    public MyOneButtonDialog(String content) {
        this(content, () -> {
        });
    }

    public MyOneButtonDialog(String content, Runnable confirmAction) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(new Label(content));

        setDialogContainer(PaneFactory.getMainPane());
        setContent(jfxDialogLayout);

        JFXButton button = new JFXButton("确认");

        button.setOnAction(e -> {
            close();
            confirmAction.run();
        });
        jfxDialogLayout.setActions(button);
    }
}
