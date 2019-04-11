package ui.common.mixer;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public interface FXMLLoadableMixer {
    default void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(publicGetURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 这个不应该是public，但是这是因为interface没办法protected，暂时没想到办法
    String publicGetURL();
}
