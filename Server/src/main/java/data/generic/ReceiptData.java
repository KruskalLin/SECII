package data.generic;

import data.generic.ReceipishData;
import dataService.checkdataService.ReceiptDataService;
import mapper.generic.ReceipishPOMapper;
import mapper.generic.ReceiptPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import util.ReceiptSearchCondition;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReceiptData<T extends ReceiptPO> extends ReceipishData<T> implements ReceiptDataService<T> {
    private Class<? extends ReceiptPOMapper<T>> mapperClass = null;
    private Class<T> receiptClass = null;

    public ReceiptData(Class<? extends ReceiptPOMapper<T>> mapperClass, Class<T> receiptClass) throws RemoteException{
        this.mapperClass = mapperClass;
        this.receiptClass = receiptClass;
    }

    @Override
    protected Class<? extends ReceipishPOMapper<T>> getMapperClass() {
        return mapperClass;
    }

    @Override
    protected Class<T> getPOClass() {
        return receiptClass;
    }

    @Override
    protected void setInitialValue(T receipishPO) {
        super.setInitialValue(receipishPO);
        receipishPO.setReceiptState(ReceiptState.DRAFT);
    }

    @Override
    public ArrayList<T> selectBetween(LocalDateTime begin, LocalDateTime end) throws RemoteException{
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectBetween(begin, end);
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<T> selectByState(ReceiptState receiptState) throws RemoteException {
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectByState(receiptState);
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<T> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.searchForBusiness(receiptSearchCondition);
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<T> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.search(respectiveReceiptSearchCondition);
            session.commit();
        }
        return resultList;
    }
}
