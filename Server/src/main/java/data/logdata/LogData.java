package data.logdata;

import annotations.RMIRemote;
import dataService.logdataService.LogdataService;
import mapper.LogDataPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.LogPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class LogData extends UnicastRemoteObject implements LogdataService{
    public LogData() throws RemoteException {
    }

    @Override
    public ResultMessage insert(LogPO logPO) throws RemoteException{
        if(logPO.getLogId()==0){
        int id = getId();
        logPO.setLogId(id);}
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            LogDataPOMapper mapper = session.getMapper(LogDataPOMapper.class);
            mapper.insert(logPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<LogPO> getAll() throws RemoteException{
        ArrayList<LogPO> logPOs =new ArrayList<>();
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            LogDataPOMapper mapper = session.getMapper(LogDataPOMapper.class);
            logPOs = mapper.search();
            session.commit();
        }
        return logPOs;
    }

    private int getId() throws RemoteException{
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            try {
                LogDataPOMapper mapper = session.getMapper(LogDataPOMapper.class);
                int id = mapper.getId();
                session.commit();
                return id;
            } catch (BindingException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }
}
