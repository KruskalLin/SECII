package businesslogic.businessbl;

import blService.businessblservice.BusinessConditionInfo;
import blService.businessblservice.BusinessConditionblService;
import dataService.businessdataService.BusinessConditionDataService;
import po.BusinessConditionPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class BusinessConditionbl implements BusinessConditionblService, BusinessConditionInfo{
    private BusinessConditionDataService businessConditionDataService;

    public BusinessConditionbl() throws RemoteException, NotBoundException, MalformedURLException {
        businessConditionDataService = (BusinessConditionDataService) Naming.lookup("rmi://localhost:1099/BusinessConditionData");
    }

    @Override
    public void insert(BusinessConditionPO businessConditionPO) throws RemoteException {
        businessConditionDataService.insert(businessConditionPO);
    }

    @Override
    public BusinessConditionPO search(LocalDateTime begin, LocalDateTime end) throws RemoteException {
        return businessConditionDataService.select(begin, end).stream().reduce(new BusinessConditionPO(), BusinessConditionPO::add);
    }

//    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
//        BusinessConditionbl bbl = new BusinessConditionbl();
//        BusinessConditionPO bpo = bbl.search(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(5));
//        System.out.println(bpo.getTotalProfit());
//        System.out.println(bpo.getTotalIncome());
//        System.out.println(bpo.getTotalCost());
//    }
}
