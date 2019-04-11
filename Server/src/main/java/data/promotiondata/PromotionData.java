package data.promotiondata;

import data.generic.ReceipishData;
import dataService.promotiondataService.PromotionDataService;
import exceptions.ItemNotFoundException;
import exceptions.ReachUpperLimitException;
import mapper.generic.PromotionPOMapper;
import mapper.generic.ReceipishPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.promotionPO.PromotionPO;
import util.PromotionSearchCondition;
import util.PromotionState;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PromotionData<T extends PromotionPO> extends ReceipishData<T> implements PromotionDataService<T> {
    // because we cannot use T.class
    private Class<? extends PromotionPOMapper<T>> mapperClass = null;
    private Class<T> promotionClass = null;

    public PromotionData(Class<? extends PromotionPOMapper<T>> mapperClass, Class<T> promotionClass) throws RemoteException {
        this.mapperClass = mapperClass;
        this.promotionClass = promotionClass;
    }

    @Override
    protected void setInitialValue(T receipishPO) {
        super.setInitialValue(receipishPO);
        receipishPO.setPromotionState(PromotionState.DRAFT);
        receipishPO.setBeginTime(LocalDateTime.now());
        receipishPO.setEndTime(LocalDateTime.now());
    }

    @Override
    protected Class<? extends ReceipishPOMapper<T>> getMapperClass() {
        return mapperClass;
    }

    @Override
    protected Class<T> getPOClass() {
        return promotionClass;
    }

    @Override
    public ArrayList<T> selectInEffect() throws RemoteException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDayZero = LocalDateTime.of(now.plusDays(1).toLocalDate(), LocalTime.MIN);

        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectInEffect(now, nextDayZero, PromotionState.SAVED);
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<T> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException {
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.search(promotionSearchCondition);
            session.commit();
        }
        return resultList;
    }


}
