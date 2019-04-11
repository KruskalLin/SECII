package ui.util;

import javafx.scene.layout.StackPane;

public class PaneFactory {
    private static StackPane loginPane;
    private static StackPane mainPane;
    public synchronized static StackPane getLoginPane() {
        if(loginPane==null) {
            loginPane = new StackPane();
            loginPane.setPrefSize(294,444);
            loginPane.setStyle("-fx-background-color: transparent ; -fx-border-color: transparent");
        }
        return loginPane;
    }

    public synchronized static StackPane getMainPane() {
        if(mainPane==null) {
            mainPane = new StackPane();
            mainPane.setPrefSize(800,600);
            mainPane.setStyle("-fx-background-color: transparent ; -fx-border-color: transparent");
        }
        return mainPane;
    }

    public synchronized static void setMainPane(StackPane mainpane) {
        System.out.println("not support!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        PaneFactory.mainPane = mainpane;
    }

    public synchronized static void setLoginPane(StackPane loginPane) {
        PaneFactory.loginPane = loginPane;
    }
}
