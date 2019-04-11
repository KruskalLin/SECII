package ui.messageui;

import com.jfoenix.controls.JFXListCell;

public class MessageCell extends JFXListCell {
    private MessageHBox messageHBox;
    @Override
    public synchronized void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
        {
            messageHBox = new MessageHBox();

            setGraphic(messageHBox);
        }else{
            setGraphic(null);
        }

    }

}
