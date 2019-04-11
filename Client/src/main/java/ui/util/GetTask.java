package ui.util;

import com.jfoenix.controls.JFXDialog;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class GetTask extends Task<Boolean> {

    private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);

    private Runnable business;

    private Predicate<Integer> p;

    /**
     * @Author: Lin Yuchao
     * @Attention
     * @Param: runnable 具体的业务；  dialog 用来调show和close ；predicate传入方法
     * @Return:
    **/
    public GetTask(Runnable runnable, JFXDialog dialog,Predicate<Integer> p) {
        this.business = business;
        this.p = p;
        this.valueProperty().addListener((b, o, n) -> {
            if (integerProperty.get() == 1) {
                Platform.runLater(runnable);
            } else if (integerProperty.get() == 0) {
                dialog.show();
            }
        });
    }

    @Override
    protected Boolean call() throws Exception {
        if(p.test(0)){
            Thread.sleep(1000);
            integerProperty.setValue(1);
//            System.out.println("hahaha");
            return true;
        } else {
            Thread.sleep(1000);
            integerProperty.set(0);
            return false;
        }
    }

}
