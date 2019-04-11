package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import ui.accountantui.AccountDetailPane;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;

public class AccountListVO extends SelectableVO<AccountListVO> implements Serializable {

    private int ID;
    private String name;
    private double balance;

    boolean multiple = true;


    public AccountListVO(){

    }

    public AccountListVO(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }


    public AccountListVO(int ID, String name, double balance) {
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

    public String toString(){
        return "AccountListVO{"+ID+" "+name+" "+balance+" }";
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public  AccountDetailPane getDetailPane(){
        return new AccountDetailPane(this);
    }
}
