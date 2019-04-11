package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class TextFieldPane extends AnchorPane {

    @FXML
    JFXTextField textfield;

    @FXML
    JFXButton save;

    @FXML
    JFXButton cancel;

    private SimpleStringProperty text = new SimpleStringProperty("");

    public TextFieldPane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/textfieldpane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            text.bind(textfield.textProperty());
            textfield.setLabelFloat(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void save(Runnable runnable){
        this.save.setOnMouseClicked(t->{
            Platform.runLater(runnable);
        });
    }

    public void cencel(Runnable runnable){
        this.cancel.setOnMouseClicked(t->{
            Platform.runLater(runnable);
        });
    }

    public void setPrompt(String s){
        textfield.setPromptText(s);
    }

    public void setText(String s){
        textfield.setText(s);
    }

    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }
}
