package ui.common;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public abstract class MyListItemPane<T> extends AnchorPane {
    protected MyListItemTablePane myListItmeTablePane;
    private JFXDialog dialog;
    protected T vo;

    public MyListItemPane(String url, T vo, MyListItemTablePane myListItmeTablePane) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.vo = vo;
        this.myListItmeTablePane = myListItmeTablePane;

    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }


    @FXML
    private void save() {
        if (validate()) {
            saveItem();
            dialog.close();
        }
    }

    @FXML
    private void delete() {
        deleteItem();
        dialog.close();
    }

    public abstract void saveItem();

    public abstract void deleteItem();

    public abstract boolean validate();

}
