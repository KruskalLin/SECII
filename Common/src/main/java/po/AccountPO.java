package po;

import java.io.Serializable;

public class AccountPO implements Serializable{
    private Integer ID;
    private String name;
    private double balance;




    public AccountPO(){
        super();
    }

    public AccountPO(Integer ID, String name, double banlance) {
        super();
        this.ID = ID;
        this.name = name;
        this.balance = banlance;
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
