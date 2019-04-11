package ui.messageui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.util.Callback;

public class MessageListView extends JFXListView {

    ObservableList observableList = FXCollections.observableArrayList();

    //cell
    public MessageListView() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/messageui/message.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setCellFactory(new Callback<JFXListView, JFXListCell>() {
            public JFXListCell call(JFXListView param) {
                return new MessageCell();
            }
        });
        this.setMaxWidth(Control.USE_PREF_SIZE);
        observableList.add(new Object());
        observableList.add(new Object());

        observableList.add(new Object());

        this.setItems(observableList);

    }


}
