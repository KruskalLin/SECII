package data.goodsdata;

import annotations.RMIRemote;
import dataService.goodsdataService.GoodsClassificationDataService;
import mapper.GoodsClassificationPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.GoodsClassificationPO;
import util.ResultMessage;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
@RMIRemote
public class GoodsClassificationData  extends UnicastRemoteObject implements GoodsClassificationDataService {
    public GoodsClassificationData() throws RemoteException {
    }

    @Override
    public ResultMessage insert(GoodsClassificationPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassificationPOMapper mapper = session.getMapper(GoodsClassificationPOMapper.class);
            mapper.insert(po);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassificationPOMapper mapper = session.getMapper(GoodsClassificationPOMapper.class);
            mapper.delete(id);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(GoodsClassificationPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassificationPOMapper mapper = session.getMapper(GoodsClassificationPOMapper.class);
            mapper.update(po);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<GoodsClassificationPO> show() {
        SqlSession session = null;
        ArrayList<GoodsClassificationPO> list;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassificationPOMapper mapper = session.getMapper(GoodsClassificationPOMapper.class);
            list =  mapper.show();
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return list;
    }

    @Override
    public GoodsClassificationPO getById(String id) {
        SqlSession session = null;
        GoodsClassificationPO po;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassificationPOMapper mapper = session.getMapper(GoodsClassificationPOMapper.class);
            po = mapper.getById(id);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return po;
    }

}
