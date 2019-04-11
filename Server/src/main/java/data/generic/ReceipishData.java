package data.generic;

import exceptions.ItemNotFoundException;
import exceptions.ReachUpperLimitException;
import javafx.scene.control.Pagination;
import mapper.generic.PromotionPOMapper;
import mapper.generic.ReceipishPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.generic.ReceipishPO;
import util.ResultMessage;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class ReceipishData<T extends ReceipishPO> extends CrudData<T> {
    protected abstract Class<? extends ReceipishPOMapper<T>> getMapperClass();

    protected abstract Class<T> getPOClass();

    private boolean isFirstInsert = false;

    protected ReceipishData() throws RemoteException {
    }

    // 因为防崩溃所以没有abstract。但是最好abstract，强制implement
    protected void setInitialValue(T receipishPO) {
        receipishPO.setCreateTime(LocalDateTime.now());
        receipishPO.setLastModifiedTime(LocalDateTime.now());
    }

    public T getNew() throws RemoteException {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        int resultID;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceipishPOMapper<T> mapper = session.getMapper(getMapperClass());
            resultID = mapper.getDayId(today);
            session.commit();
        } catch (BindingException e) {
            // 这个exception一定是因为这是一天中的第一条吗？
            resultID = 0;
        }
        if (resultID >= 100000) {
            throw new ReachUpperLimitException();
        }

        T result = null;
        try {
            result = getPOClass().newInstance();
            result.setDayId(resultID);

            setInitialValue(result);

            isFirstInsert = true;
            insert(result);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            isFirstInsert = false;
        }

        return result;
    }

    public ResultMessage insert(T receipishPO) throws RemoteException {
        // TODO 将来上面的insert肯定要干掉的，这里只作为private。因为上面不能用insert了，只能从getNew拿，有点像一个超大工厂
        // TODO 顺便，返回值换成void拉倒，resultMessage能赤exception来传了

        if (!isFirstInsert) {
            return update(receipishPO);
        }

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceipishPOMapper<T> mapper = session.getMapper(getMapperClass());
            mapper.insert(receipishPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(T receipishPO) throws RemoteException {
        if (selectByMold(receipishPO) != null) {
            receipishPO.setLastModifiedTime(LocalDateTime.now());
            try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                ReceipishPOMapper<T> mapper = session.getMapper(getMapperClass());
                mapper.update(receipishPO);
                session.commit();
            }
            return ResultMessage.SUCCESS;
        } else {
            throw new ItemNotFoundException();
        }

    }

    public ResultMessage delete(T receipishPO) throws RemoteException {
        if (selectByMold(receipishPO) != null) {
            try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                ReceipishPOMapper<T> mapper = session.getMapper(getMapperClass());
                mapper.delete(receipishPO);
                session.commit();
            }
            return ResultMessage.SUCCESS;
        } else {
            throw new ItemNotFoundException();
        }
    }

    public T selectByMold(T receipishPO) {
        T result = null;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceipishPOMapper<T> mapper = session.getMapper(getMapperClass());
            result = mapper.selectByMold(receipishPO);
            session.commit();
        }
        return result;
    }
}
