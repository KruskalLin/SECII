package ui.salesui;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.table.TableRowExpanderColumn;
import vo.promotionVO.PromotionVO;

import java.util.ArrayList;

public class PromotionList extends TableView<PromotionVO> {


    private ObservableList<PromotionVO> observableList = FXCollections.observableArrayList();

    private SimpleIntegerProperty type = new SimpleIntegerProperty(0);

    private PromotionVO promotionVO;

    public PromotionList(){
        super();
        this.setPrefSize(450,400);

        TableColumn<PromotionVO,String> name = new TableColumn<>("Name");
        name.setPrefWidth(100);
        name.setCellValueFactory(t->new ReadOnlyObjectWrapper<>(t.getValue().getClass().getName()));


        TableColumn<PromotionVO,String> timeFrom = new TableColumn<>("From");
        timeFrom.setPrefWidth(100);
        timeFrom.setCellValueFactory(t->new ReadOnlyObjectWrapper<>(t.getValue().getBeginTime().toString()));

        TableColumn<PromotionVO,String> timeTo = new TableColumn<>("To");
        timeTo.setPrefWidth(100);
        timeTo.setCellValueFactory(t->new ReadOnlyObjectWrapper<>(t.getValue().getBeginTime().toString()));

        TableColumn<PromotionVO,String> comment = new TableColumn<>("Coment");
        comment.setPrefWidth(130);
        comment.setCellValueFactory(t->new ReadOnlyObjectWrapper<>(t.getValue().getComment()));


        this.getColumns().addAll(name, timeFrom, timeTo,comment);
        this.setItems(observableList);
        this.setRowFactory(tableView->{
            TableRow<PromotionVO> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                PromotionVO temp = row.getItem();
                if(temp!=null&&event.getClickCount()==1) {
                    promotionVO = temp;
                    type.set(type.get()+1);
                }else if(temp!=null&&event.getClickCount()==2){

                }
            });
            return row;
        });


    }

    public void setList(ArrayList<PromotionVO> arrayList){
        observableList.setAll(arrayList);
    }

    public int getType() {
        return type.get();
    }

    public SimpleIntegerProperty typeProperty() {
        return type;
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public PromotionVO getPromotionVO() {
        return promotionVO;
    }

    public void setPromotionVO(PromotionVO promotionVO) {
        this.promotionVO = promotionVO;
    }


}
