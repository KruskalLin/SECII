package blService.initialblservice;

import po.AccountPO;
import po.GoodsPO;
import po.MemberPO;
import vo.AccountVO;
import vo.MemberVO;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.GoodsVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InitialblService {

    public void initial(String year)throws RemoteException;

    public ArrayList<AccountPO> showAccount(String year)throws RemoteException;

    public ArrayList<MemberPO> showMember(String year)throws RemoteException;

    public ArrayList<GoodsPO> showGoods(String year)throws RemoteException;



}
