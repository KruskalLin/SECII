package businesslogic.initialbl;

import blService.initialblservice.InitialblService;
import dataService.initialDataService.InitialDataService;
import po.AccountInitialPO.InitialAccountPO;
import po.AccountInitialPO.InitialGoodsPO;
import po.AccountInitialPO.InitialMemberPO;
import po.AccountPO;
import po.GoodsPO;
import po.MemberPO;
import vo.AccountVO;
import vo.MemberVO;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Initialbl implements InitialblService{

    InitialDataService initialDataService;

    public  Initialbl()throws RemoteException,NotBoundException,MalformedURLException{
        initialDataService = (InitialDataService) Naming.lookup("rmi://localhost:1099/InitialData");
    }

    @Override
    public void initial(String year)throws RemoteException{
        initialDataService.initial(year);
    }

    @Override
    public ArrayList<AccountPO> showAccount(String year)throws RemoteException{

        ArrayList<InitialAccountPO> temp = initialDataService.showAccount(year);
        ArrayList<AccountPO> result = new ArrayList<>();
        for(InitialAccountPO po:temp){
            result.add(new AccountPO(po.getID(),po.getName(),po.getBalance()));
        }
        return result;

    }

    @Override
    public ArrayList<MemberPO> showMember(String year)throws RemoteException{
        ArrayList<InitialMemberPO> temp = initialDataService.showMember(year);
        ArrayList<MemberPO> result = new ArrayList<>();
        for(InitialMemberPO po:temp){
            //int memberId, MemberCategory memberCategory,byte[] image,int VIPgrade, String memberName, String clerkName, String phoneNumber, String address, String zipCode, String emailAddress, double debtCeiling, double debt, double credit, String comment
            result.add(new MemberPO(po.getMemberId(),po.getMemberCatogory(),po.getImage(),po.getVIPgrade(),po.getMemberName(),po.getClerkName(),po.getPhoneNumber(),po.getAddress(),po.getZipCode(),po.getEmailAddress(),po.getDebtCeiling(),po.getDebt(),po.getCredit(),""));
        }
        return result;
    }


    @Override
    public ArrayList<GoodsPO> showGoods(String year)throws RemoteException{
        ArrayList<InitialGoodsPO> temp = initialDataService.showGoods(year);
        ArrayList<GoodsPO> result = new ArrayList<>();
        for(InitialGoodsPO po:temp){
            result.add(new GoodsPO(po.getId(),po.getGoodName(),po.getGoodType(),po.getClassifyId(),po.getInventoryNum(),po.getPurPrice(),po.getSalePrice(),po.getRecentPurPrice(),po.getRecentSalePrice(),po.getAlarmNumber(),0));
        }
        return result;
    }







}
