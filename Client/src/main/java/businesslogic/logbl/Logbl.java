package businesslogic.logbl;

import blService.logblService.LogblService;
import dataService.logdataService.LogdataService;
import po.LogPO;
import ui.util.UserInfomation;
import util.EventCategory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static ui.util.UserInfomation.url;

public class Logbl implements LogblService{
    LogdataService logdataService;

    public Logbl() throws RemoteException, MalformedURLException, NotBoundException {
        logdataService = (LogdataService) Naming.lookup( url+"LogData");
    }

    @Override
    public void insert(LogPO logPO) throws RemoteException {
        logdataService.insert(logPO);
    }

    @Override
    public ArrayList<LogPO> getList() throws RemoteException{
        ArrayList<LogPO> logPOs = logdataService.getAll();
        return logPOs;
    }

    public void insertString(EventCategory eventCategory,String event) throws RemoteException{
        LogPO logPO = new LogPO();
        logPO.setCreateTime(LocalDateTime.now());
        logPO.setEvent(event);
        logPO.setUserCategory(UserInfomation.usertype);
        logPO.setUsername(UserInfomation.username);
        logPO.setEventCategory(eventCategory);
        insert(logPO);
    }


}
