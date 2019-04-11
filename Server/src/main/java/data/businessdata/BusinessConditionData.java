package data.businessdata;

import annotations.RMIRemote;
import dataService.businessdataService.BusinessConditionDataService;
import mapper.BusinessConditionPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.BusinessConditionPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RMIRemote
public class BusinessConditionData extends UnicastRemoteObject implements BusinessConditionDataService {
    public BusinessConditionData() throws RemoteException {
    }

    @Override
    public void insert(BusinessConditionPO businessConditionPO) throws RemoteException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BusinessConditionPOMapper mapper = sqlSession.getMapper(BusinessConditionPOMapper.class);
            mapper.insert(businessConditionPO);
            sqlSession.commit();
        }
    }

    @Override
    public ArrayList<BusinessConditionPO> select(LocalDateTime begin, LocalDateTime end) {
        ArrayList<BusinessConditionPO> list = null;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BusinessConditionPOMapper mapper = sqlSession.getMapper(BusinessConditionPOMapper.class);
            list = mapper.select(begin, end);
            sqlSession.commit();
        }
        return list;
    }
}
