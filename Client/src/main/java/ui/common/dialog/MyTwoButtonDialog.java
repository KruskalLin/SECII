package ui.common.dialog;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import ui.util.PaneFactory;

public class MyTwoButtonDialog extends JFXDialog {
    private JFXDialogLayout jfxDialogLayout;
    private JFXButton button;
    private JFXButton re;

    private StringProperty changableContent;

    public MyTwoButtonDialog(String content) {
        this(content, ()->{}, ()->{});
    }

    public MyTwoButtonDialog(String content, Runnable confirmAction) {
        this(content, confirmAction, ()->{});
    }

    public MyTwoButtonDialog(StringProperty changableContent, Runnable confirmAction) {
        this(changableContent, confirmAction, ()->{});
    }

    public MyTwoButtonDialog(String content, Runnable confirmAction, Runnable cancelAction) {
        this(new SimpleStringProperty(content), confirmAction, cancelAction);
    }

    public MyTwoButtonDialog(StringProperty changableContent, Runnable confirmAction, Runnable cancelAction) {
        this.changableContent = changableContent;
        jfxDialogLayout = new JFXDialogLayout();

        setDialogContainer(PaneFactory.getMainPane());
        setContent(jfxDialogLayout);

        button = new JFXButton("确认");
        re = new JFXButton("取消");

        button.setOnAction(e -> {
            close();
            confirmAction.run();
        });
        re.setOnAction(e -> {
            close();
            cancelAction.run();
        });
        jfxDialogLayout.setActions(button, re);
    }

    public void setButtonOneAction(Runnable confirmAction) {
        button.setOnAction(e -> {
            close();
            confirmAction.run();
        });
    }

    public void setButtonTwoAction(Runnable cancelAction) {
        re.setOnAction(e -> {
            close();
            cancelAction.run();
        });
    }

    @Override
    public void show() {
        jfxDialogLayout.setBody(new Label(changableContent.get()));
        super.show();
    }
}
