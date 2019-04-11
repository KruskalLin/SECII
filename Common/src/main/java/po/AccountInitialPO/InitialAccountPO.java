package po.AccountInitialPO;

import java.io.Serializable;

public class InitialAccountPO implements Serializable{
    private String year;
    private Integer ID;
    private String name;
    private double balance;

    public InitialAccountPO(){

    }

    public InitialAccountPO(String year, Integer ID, String name, double balance) {
        this.year = year;
        this.ID = ID;
        this.name = name;
        this.balance = balance;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
