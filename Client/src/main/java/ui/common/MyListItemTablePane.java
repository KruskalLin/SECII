package ui.common;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.util.ListPopup;
import ui.util.PaneFactory;

import java.util.ArrayList;

public abstract class MyListItemTablePane<T extends RecursiveTreeObject<T>> extends JFXTreeTableView<T> {

    protected MyListItemPane<T> myListItemPane;
    private StackPane mainpane;
    protected ObservableList<T> observableList = FXCollections.observableArrayList();


    public MyListItemTablePane() {
        super();

        mainpane = PaneFactory.getMainPane();
        this.setEditable(true);
        this.setShowRoot(false);
        setInitial();
    }


    private void setInitial() {
        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(row.getTreeItem()!=null) {
                    T vo = (T) row.getTreeItem().getValue();
                    if (event.getButton() == MouseButton.SECONDARY) {
//                    ListPopup listPopup = new ListPopup();
//                    JFXPopup popup = new JFXPopup(listPopup);
//                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent event) {
//                            initial(vo);
//                            JFXDialog dialog = new JFXDialog(mainpane, myListItemPane, JFXDialog.DialogTransition.CENTER);
//                            myListItemPane.setDialog(dialog);
//                            dialog.show();
//                            popup.hide();
//                        }
//                    });
//                    listPopup.getListdelete().setOnMouseClicked(new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent event) {
//                            observableList.remove(vo);
//                        }
//                    });
//                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                    }
                    if (event.getClickCount() == 2) {
                        initial(vo);
                        JFXDialog dialog = new JFXDialog(mainpane, myListItemPane, JFXDialog.DialogTransition.CENTER);
                        myListItemPane.setDialog(dialog);
                        dialog.show();
                    }
                }
            });
            row.selectedProperty().addListener(e -> {
                if (row.isSelected()) {
                    row.toFront();
                } else {
                    row.setEffect(null);
                }
            });
            return row;
        });
    }

    public void remove(T t) {
        observableList.remove(t);
    }

    public void setList(ArrayList<T> t) {
        observableList.setAll(t);
    }

    public void add(T t) {
        observableList.add(t);
    }


    public ArrayList<T> getList() {
        ArrayList<T> arrayList = new ArrayList<>(observableList);
        return arrayList;
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<T> observableList) {
        this.observableList = observableList;
    }

    public abstract void initial(T vo);

    public abstract void refresh();

}
