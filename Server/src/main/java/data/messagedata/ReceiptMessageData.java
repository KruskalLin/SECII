package data.messagedata;

import annotations.RMIRemote;
import dataService.messagedataService.ReceiptMessageDataService;
import mapper.ReceiptMessagePOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.ReceiptMessagePO;
import util.UserCategory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RMIRemote
public class ReceiptMessageData extends UnicastRemoteObject implements ReceiptMessageDataService{
    public ReceiptMessageData() throws RemoteException {
    }

    @Override
    public void insert(ReceiptMessagePO messagePO) throws RemoteException {
        messagePO.setCreateTime(LocalDateTime.now());
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptMessagePOMapper mapper = session.getMapper(ReceiptMessagePOMapper.class);
            mapper.insert(messagePO);
            session.commit();
        }
    }

    @Override
    public ArrayList<ReceiptMessagePO> selectByUser(int userId) throws RemoteException {
        ArrayList<ReceiptMessagePO> result;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptMessagePOMapper mapper = session.getMapper(ReceiptMessagePOMapper.class);
            result = mapper.selectByUser(userId);
            session.commit();
        }
        return result;
    }

    @Override
    public ArrayList<ReceiptMessagePO> selectByUserCategory(UserCategory userCategory) throws RemoteException {
        ArrayList<ReceiptMessagePO> result;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptMessagePOMapper mapper = session.getMapper(ReceiptMessagePOMapper.class);
            result = mapper.selectByUserCategory(userCategory);
            session.commit();
        }
        return result;
    }

    @Override
    public ReceiptMessagePO selectByMold(ReceiptMessagePO messagePO) throws RemoteException {
        ReceiptMessagePO result;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptMessagePOMapper mapper = session.getMapper(ReceiptMessagePOMapper.class);
            result = mapper.selectByMold(messagePO);
            session.commit();
        }
        return result;
    }

    @Override
    public void delete(ReceiptMessagePO messagePO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptMessagePOMapper mapper = session.getMapper(ReceiptMessagePOMapper.class);
            mapper.delete(messagePO);
            session.commit();
        }
    }
}
