package data.accountdata;


import annotations.RMIRemote;
import dataService.accountDataService.AccountDataService;
import mapper.AccountPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.AccountPO;
import util.ResultMessage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

@RMIRemote
public class AccountData extends UnicastRemoteObject implements AccountDataService{

    public AccountData()throws RemoteException{

    }

    @Override
    public int getID(String name)throws RemoteException{
        int result=0;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.getID(name);
        }
        return result;
    }

    @Override
    public AccountPO getAccountByName(String name)throws RemoteException{
        AccountPO accountPO;
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            AccountPOMapper mapper = sqlSession.getMapper(AccountPOMapper.class);
            accountPO = mapper.getAccountByName(name);
        }
        return accountPO;
    }

    @Override
    public ResultMessage insert(AccountPO accountPO) throws RemoteException{

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.insert(accountPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(AccountPO accountPO) throws RemoteException{

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.update(accountPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(int ID) throws RemoteException{
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.delete(ID);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<AccountPO> selectInEffect(String keyword) throws RemoteException{

        ArrayList<AccountPO> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            resultList = mapper.selectInEffect(keyword);
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<AccountPO> getAll()throws RemoteException{

        ArrayList<AccountPO> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            resultList = mapper.getAll();
            session.commit();
        }
        return resultList;
    }



}
