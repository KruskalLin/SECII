package po;

import java.io.Serializable;

public class TransferItemPO implements Serializable {

    private int accountID;
    private double sum;
    private String comment;

    public TransferItemPO() {
    }

    public TransferItemPO(int accountID, double sum, String comment) {
        this.accountID = accountID;
        this.sum = sum;
        this.comment = comment;
    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
