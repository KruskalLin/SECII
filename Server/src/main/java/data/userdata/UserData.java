package data.userdata;

import annotations.RMIRemote;
import dataService.userdataService.UserDataService;
import javafx.scene.image.Image;
import mapper.UserDataPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.UserPO;
import test.Userdata;
import util.ImageConvertor;
import util.ResultMessage;
import util.UserCategory;
import util.UserSearchCondition;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@RMIRemote
public class UserData extends UnicastRemoteObject implements UserDataService {

    public UserData() throws RemoteException {
    }

    @Override
    public UserPO getNew() throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            int id = mapper.getId();
            UserPO userPO = new UserPO();
            userPO.setUserId(id);
            userPO.setCreateTime(LocalDateTime.now());
            userPO.setIsDeleted(0);
            userPO.setUsertype(UserCategory.UserManager);
            try {
                userPO.setImage(ImageConvertor.getByte(ImageIO.read(getClass().getResource("/default/timg.jpg"))));
            }catch (Exception e){
                e.printStackTrace();
            }

            insert(userPO);
            session.commit();
            return userPO;
        }
    }


    private ResultMessage insert(UserPO userPO) throws RemoteException{
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            mapper.insert(userPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }


    @Override
    public ResultMessage delete(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            UserPO userPO = showDetail(id);
            userPO.setIsDeleted(1);
            update(userPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage update(UserPO userPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            mapper.update(userPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public UserPO showDetail(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            UserPO userPO = mapper.showDetail(id);
            if(userPO!=null&&userPO.getImage()==null){
                try {
                    userPO.setImage(ImageConvertor.getByte(ImageIO.read(getClass().getResource("/default/timg.jpg"))));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            session.commit();

            return userPO;
        }
    }

    @Override
    public ArrayList<UserPO> search(UserSearchCondition userSearchCondition) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            ArrayList<UserPO> userPOs = mapper.search(userSearchCondition);
            userPOs = userPOs.stream().filter(userPO -> {
                    if(userPO.getImage()==null){
                        try {
                            userPO.setImage(ImageConvertor.getByte(ImageIO.read(getClass().getResource("/default/timg.jpg"))));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            return userPO.getIsDeleted()==0;}).collect(Collectors.toCollection(ArrayList::new));
            session.commit();
            return userPOs;
        }
    }


}
