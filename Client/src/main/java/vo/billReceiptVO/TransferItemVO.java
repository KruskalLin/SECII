package vo.billReceiptVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import po.TransferItemPO;

import java.io.Serializable;

public class TransferItemVO extends RecursiveTreeObject<TransferItemVO> implements Serializable{

    private IntegerProperty accountID;
    private DoubleProperty sum;
    private StringProperty comment;

    public TransferItemVO(int accountID, double sum, String comment) {
        this.accountID = new SimpleIntegerProperty(accountID);
        this.sum = new SimpleDoubleProperty(sum);
        this.comment = new SimpleStringProperty(comment);
    }

    public int getAccountID() {
        return accountID.get();
    }

    public IntegerProperty accountIDProperty() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID.set(accountID);
    }

    public double getSum() {
        return sum.get();
    }

    public DoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
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

    public TransferItemPO toPO(){
        TransferItemPO result = new TransferItemPO();
        try {
            result.setAccountID(accountID.get());
        } catch (NumberFormatException e) {
            result.setAccountID(-1);
        }
        try {
            result.setSum(sum.get());
        } catch (NumberFormatException e) {
            result.setSum(-1);
        }
        result.setComment(comment.get());

        return result;
    }

}
