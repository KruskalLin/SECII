package businesslogic.genericbl;

import blService.businessblservice.BusinessSearchInfo;
import blService.checkblService.CheckInfo;
import blService.genericblService.ReceiptblService;
import businesslogic.blServiceFactory.MessageObjectFactory;
import businesslogic.blServiceFactory.MyServiceFactory;
import dataService.checkdataService.ReceiptDataService;
import dataService.generic.ReceipishDataService;
import po.ReceiptMessagePO;
import po.receiptPO.ReceiptPO;
import ui.util.UserInfomation;
import util.*;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Receiptbl<TV extends ReceiptVO, TP extends ReceiptPO> extends Receipishbl<TV, TP> implements ReceiptblService<TV>, CheckInfo<TV>, BusinessSearchInfo<TV> {
    private Class<TV> receiptVOClass;
    private Class<TP> receiptPOClass;

    protected ReceiptDataService<TP> receiptDataService; // 如果需要的话，这里可以再传一个Service的范型

    public Receiptbl(Class<TV> receiptVOClass, Class<TP> receiptPOClass) throws RemoteException, NotBoundException, MalformedURLException {
        this.receiptVOClass = receiptVOClass;
        this.receiptPOClass = receiptPOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        String poName = receiptPOClass.getName();
        String receiptDataName = poName.substring(poName.lastIndexOf(".") + 1, poName.length() - 2) + "Data";
//        System.out.println(receiptDataName);
        receiptDataService = (ReceiptDataService<TP>) Naming.lookup(registrationpre + "/" + receiptDataName);

    }

    @Override
    protected Class<TV> getVOClass() {
        return receiptVOClass;
    }

    @Override
    protected Class<TP> getPOClass() {
        return receiptPOClass;
    }

    @Override
    protected ReceipishDataService<TP> getDataService() {
        return receiptDataService;
    }


    /**
     * implement receiptblService
     */

    @Override
    public ArrayList<TV> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return receiptDataService.search(receiptSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<TV> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        return receiptDataService.search(respectiveReceiptSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void submit(TV receiptVO) throws RemoteException {
        // update receipt
        receiptVO.setReceiptState(ReceiptState.PENDING);
        receiptDataService.update(receiptVO.toPO());

        // send Message
        ReceiptMessagePO messagePO = getCommonInfomationOfReceiptMessage(receiptVO);
        messagePO.setToUserCategory(UserCategory.GeneralManager); // 就这一行不一样
        messagePO.setMessagePurpose(MessagePurpose.Submit);

        MessageObjectFactory.getServerInterface().send(messagePO);
    }

    /**
     * implement checkInfo
     */

    // 当初这里提一个更新的抽象方法出来多好…
    @Override
    public ResultMessage approve(TV receiptVO) throws RemoteException {
        // update receipt
        receiptVO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptVO.toPO());

        // send message
        ReceiptMessagePO messagePO = getCommonInfomationOfReceiptMessage(receiptVO);
        messagePO.setToUserId(receiptVO.getOperatorId()); // 就这一行不一样
        messagePO.setMessagePurpose(MessagePurpose.Approve);

        MessageObjectFactory.getServerInterface().send(messagePO);

        // deleteByReceipt
        MyServiceFactory.getReceiptMessageblService().deleteByReceipt(receiptVO);

        // other things are handled by subclasses
        updateInfoAfterApproval(receiptVO);

        return ResultMessage.SUCCESS;
    }

    protected abstract void updateInfoAfterApproval(TV receiptVO) throws RemoteException;

    public ResultMessage reject(TV receiptVO) throws RemoteException {
        // update receipt;
        receiptVO.setReceiptState(ReceiptState.REJECTED);
        receiptDataService.update(receiptVO.toPO());

        // send message
        ReceiptMessagePO messagePO = getCommonInfomationOfReceiptMessage(receiptVO);
        messagePO.setToUserId(receiptVO.getOperatorId());
        messagePO.setMessagePurpose(MessagePurpose.Reject);

        MessageObjectFactory.getServerInterface().send(messagePO);

        // deleteByReceipt


        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<TV> selectPending() throws RemoteException {
        return receiptDataService.selectByState(ReceiptState.PENDING).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }


    private ReceiptMessagePO getCommonInfomationOfReceiptMessage(TV receiptVO) {
        ReceiptMessagePO messagePO = new ReceiptMessagePO();
        messagePO.setFromUserId(UserInfomation.userid);

        messagePO.setBillType(BillType.classToType.get(getPOClass()));
        messagePO.setReceiptDayId(((TP) receiptVO.toPO()).getDayId()); // TODO 数据库这个地方的设计绝对有问题……
        messagePO.setReceiptCreateTime(receiptVO.getCreateTime());
        return messagePO;
    }
}
