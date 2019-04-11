package po;

import java.io.Serializable;

public class CashItemPO implements Serializable {
    private String name;
    private double sum;
    private String comment;

    public CashItemPO(String name, double sum, String comment) {
        this.name = name;
        this.sum = sum;
        this.comment = comment;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
