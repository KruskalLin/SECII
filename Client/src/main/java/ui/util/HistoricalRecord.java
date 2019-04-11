package ui.util;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public class HistoricalRecord {
    private static ObservableList<RefreshablePane> record= FXCollections.observableArrayList();
    private static int index=0;
    public static SimpleBooleanProperty canBack = new SimpleBooleanProperty(false);
    public static SimpleBooleanProperty canForward = new SimpleBooleanProperty(false);




    static public boolean addRecord(RefreshablePane pane){
        System.out.println(pane);
        if(record.get(index).getId().equals(pane.getId())){
            if(index>0)
                canBack.setValue(true);
            if(index==record.size()-1)
            canForward.setValue(false);
            return false;
        }

        if(index!=(record.size()-1))
            record.remove(index+1,record.size());
            record.add(pane);
            index++;
            if(index>0)
                canBack.setValue(true);
            canForward.setValue(false);
        return true;
      }



    public static RefreshablePane pop(){
        index--;
        if(index==0)
            canBack.setValue(false);
        else
            canBack.setValue(true);
        if(index==record.size()-1)
            canForward.setValue(false);
        else
            canForward.setValue(true);
        return record.get(index);
    }



    public static Pane push(){
        index++;
        if(index==0)
            canBack.setValue(false);
        else
            canBack.setValue(true);
        if(index==record.size()-1)
            canForward.setValue(false);
        else
            canForward.setValue(true);
        return record.get(index);
    }

    public static void addPane(RefreshablePane pane){
        System.out.println("sabi");
        record.add(pane);
    }

    public static void removeAndPop(){
        record.remove(record.size()-1);
        if(index==0)
            canBack.setValue(false);
        else
            canBack.setValue(true);
        if(index==record.size()-1)
            canForward.setValue(false);
        else
            canForward.setValue(true);
    }


}
