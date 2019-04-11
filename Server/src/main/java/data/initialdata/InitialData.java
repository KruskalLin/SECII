package data.initialdata;

import annotations.RMIRemote;
import dataService.initialDataService.InitialDataService;
import mapper.AccountInitialPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.AccountInitialPO.InitialAccountPO;
import po.AccountInitialPO.InitialGoodsPO;
import po.AccountInitialPO.InitialMemberPO;
import po.AccountPO;
import po.GoodsClassificationPO;
import po.GoodsPO;
import po.MemberPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

@RMIRemote
public class InitialData extends UnicastRemoteObject implements InitialDataService{

    public InitialData()throws RemoteException{}

    @Override
    public void initial(String year)throws RemoteException{
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            AccountInitialPOMapper mapper = sqlSession.getMapper(AccountInitialPOMapper.class);
            //Calendar a= Calendar.getInstance();
            //String year = String.valueOf(a.get(Calendar.YEAR));
            mapper.initialAccount(year);
            mapper.initialMember(year);
            mapper.initialGoods(year);
            sqlSession.commit();
        }
    }


    @Override
    public ArrayList<InitialAccountPO> showAccount(String year)throws RemoteException{
        ArrayList<InitialAccountPO> result = new ArrayList<>();
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            AccountInitialPOMapper mapper = sqlSession.getMapper(AccountInitialPOMapper.class);
            result = mapper.showAccount(year);
        }
        return result;
    }

    @Override
    public ArrayList<InitialMemberPO> showMember(String year)throws RemoteException{
        ArrayList<InitialMemberPO> result = new ArrayList<>();
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            AccountInitialPOMapper mapper = sqlSession.getMapper(AccountInitialPOMapper.class);
            result = mapper.showMember(year);
        }
        return result;
    }

    @Override
    public ArrayList<InitialGoodsPO> showGoods(String year)throws RemoteException{
        ArrayList<InitialGoodsPO> result = new ArrayList<>();
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            AccountInitialPOMapper mapper = sqlSession.getMapper(AccountInitialPOMapper.class);
            result = mapper.showGoods(year);
        }
        return result;
    }


}
