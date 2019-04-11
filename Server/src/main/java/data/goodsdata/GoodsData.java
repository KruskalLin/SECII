package data.goodsdata;

import annotations.RMIRemote;
import dataService.goodsdataService.GoodsDataService;
import mapper.GoodsPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.GoodsPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class GoodsData extends UnicastRemoteObject implements GoodsDataService {
    public GoodsData() throws RemoteException {
    }

    @Override
    public ResultMessage insert(GoodsPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            mapper.insert(po);
            session.commit();
        } finally {
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            mapper.delete(id);
        } finally {
            if (session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
//        SqlSession session = null;
//        try {
//            session = MyBatisUtil.getSqlSessionFactory().openSession();
//            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
//            GoodsPO goodsPO = mapper.selectById(id);
//            goodsPO.setIsDelete(1);
//            mapper.update(goodsPO);
//            System.out.println(goodsPO.toString());
//        }finally {
//            session.commit();
//            session.close();
//        }
//        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(GoodsPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            mapper.update(po);
        } finally {
            if (session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<GoodsPO> show() {
        SqlSession session = null;
        ArrayList<GoodsPO> list;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            list = mapper.show();
        } finally {
            if (session != null)
                session.commit();
            session.close();
        }

        //这里的show未删除的进行暂时的改动
        return list;
    }

    @Override
    public ArrayList<GoodsPO> selectInEffect(String keywords) throws RemoteException {
        SqlSession session = null;
        ArrayList<GoodsPO> list;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            list = mapper.selectInEffect(keywords);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public GoodsPO selectById(String id) throws RemoteException {
        SqlSession session = null;
        GoodsPO goodsPO;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            goodsPO = mapper.selectById(id);
        } finally {
            session.close();
        }
        return goodsPO;
    }
}
