package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.awt.event.MouseEvent;

public class DoubleButtonDialog extends JFXDialog {
    private JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();

    private JFXButton buttonOne = new JFXButton();
    private JFXButton buttonTwo = new JFXButton();
    public DoubleButtonDialog(StackPane mainpane, String head, String body, String button1, String button2){
        jfxDialogLayout.setHeading(new Label(head));
        jfxDialogLayout.setBody(new Label(body));

        buttonOne.setText(button1);
        buttonTwo.setText(button2);

        jfxDialogLayout.setActions(buttonOne,buttonTwo);

        this.setDialogContainer(mainpane);
        this.setContent(jfxDialogLayout);
        this.setTransitionType(DialogTransition.CENTER);
    }

    public void setButtonOne(Runnable runnable){
        buttonOne.setOnMouseClicked(t->{
            close();
            Platform.runLater(runnable);
        });
    }
    public void setButtonTwo(Runnable runnable){
        buttonTwo.setOnMouseClicked(t->{
            close();
            Platform.runLater(runnable);
        });
    }




}
