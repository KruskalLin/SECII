package vo.billReceiptVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import po.CashItemPO;

import java.io.Serializable;

public class CashItemVO extends RecursiveTreeObject<CashItemVO> implements Serializable {

    private StringProperty name;
    private DoubleProperty price;
    private StringProperty comment;

    public CashItemVO(String name, double price, String comment) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.comment = new SimpleStringProperty(comment);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public CashItemPO toPO(){
        return new CashItemPO(name.get(),price.get(),comment.get());

    }

}
