package businesslogic.promotionbl.testprmt;

import businesslogic.promotionbl.TotalPromotionbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestGetNew {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        TotalPromotionbl totalPromotionbl = new TotalPromotionbl();
        System.out.println(totalPromotionbl.getNew().getPromotionState());
    }
}
