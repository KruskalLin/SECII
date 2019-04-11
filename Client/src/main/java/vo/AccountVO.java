package vo;

public class AccountVO{

    private int ID;
    private String name;
    private double balance;

    public AccountVO(int ID, String name, double balance) {
        this.ID = ID;
        this.name = name;
        this.balance = balance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
