package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;

import java.util.function.Function;

public class SearchableStringColumn<T> extends JFXTreeTableColumn<T, String> {
    public SearchableStringColumn(String text, double width, StringProperty keyWordProperty, Function<T, String> getter) {
        super(text);
        setPrefWidth(width);
        setCellValueFactory(param -> {
//            System.out.println(getter.apply(param.getValue().getValue()));
            return new ReadOnlyStringWrapper(getter.apply(param.getValue().getValue()));
        });
        setCellFactory(param -> new SearchableStringCell<>(keyWordProperty));
    }
}
