package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class OneButtonDialog extends JFXDialog {
    private JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
    private JFXButton buttonOne = new JFXButton();
    public OneButtonDialog(StackPane mainpane, String head, String body, String button){
        jfxDialogLayout.setHeading(new Label(head));
        jfxDialogLayout.setBody(new Label(body));
        buttonOne.setText(button);
        jfxDialogLayout.setActions(buttonOne);
        this.setDialogContainer(mainpane);
        this.setContent(jfxDialogLayout);
        this.setTransitionType(DialogTransition.CENTER);
    }

    public void setButtonOne(Runnable runnable){
        buttonOne.setOnMouseClicked(t->{
            this.close();
            Platform.runLater(runnable);
        });
    }

}
