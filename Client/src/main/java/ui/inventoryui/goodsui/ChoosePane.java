package ui.inventoryui.goodsui;


        import blService.memberblService.MemberInfo;
        import com.jfoenix.controls.JFXButton;
        import com.jfoenix.controls.JFXDialog;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.layout.AnchorPane;
        import org.controlsfx.control.ListSelectionView;
        import vo.MemberChooseVO;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Observable;
        import java.util.Set;
        import java.util.stream.Collectors;

public class ChoosePane extends AnchorPane {

    ObservableList<String> observableList = FXCollections.observableArrayList();



    @FXML
    ListSelectionView<String> selectView;

    @FXML
    JFXButton cancel;

    private JFXDialog dialog;



    ObservableList<String> goodChooseVOs = FXCollections.observableArrayList();

    public ChoosePane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/choosepane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        selectView.setSourceItems(observableList);
        selectView.setTargetItems(goodChooseVOs);
    }



   /* @FXML
    public void save(){
       *//* goodChooseVOs.clear();
        selectView.getTargetItems().stream().forEach(i->{
            goodChooseVOs.add(new String());
        });*//*
        System.out.println(goodChooseVOs.toString());
       dialog.close();
    }*/

    @FXML
    public void cancel(){
      /*  selectView.getSourceItems().stream().forEach(i->{
            observableList.add(goodChooseVOs.get(Integer.parseInt(i)));
        });*/
        goodChooseVOs.clear();
    }

    public void setObservableList(Set<String> list) {
        observableList.addAll(list);
    }

    public List<String> getChooseList(){
        return goodChooseVOs;
    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
