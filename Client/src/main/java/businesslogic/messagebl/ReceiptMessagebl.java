package businesslogic.messagebl;

import blService.messageblService.ReceiptMessageblService;
import dataService.messagedataService.ReceiptMessageDataService;
import javafx.beans.property.IntegerProperty;
import po.ReceiptMessagePO;
import ui.util.UserInfomation;
import util.UserCategory;
import vo.ReceiptMessageVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReceiptMessagebl implements ReceiptMessageblService {
    private IntegerProperty messageNumber;
    private ReceiptMessageDataService dataService;

    public ReceiptMessagebl(IntegerProperty messageNumber) throws RemoteException {
        this.messageNumber = messageNumber;
        try {
            dataService = (ReceiptMessageDataService) Naming.lookup("rmi://localhost:1099/ReceiptMessageData");
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ReceiptMessagePO messagePO) throws RemoteException {
        dataService.insert(messagePO);
    }

    @Override
    // TODO 而且这里有副作用…而其他方法也利用这个副作用…感觉还是不怎么好…
    public ArrayList<ReceiptMessageVO> selectByUser() throws RemoteException {
       ArrayList<ReceiptMessageVO> l1 = selectByUserId(UserInfomation.userid);
        ArrayList<ReceiptMessageVO> l2 = selectByUserCategory(UserInfomation.usertype);
        ArrayList<ReceiptMessageVO> result =  Stream.concat(l1.stream(), l2.stream()).distinct().collect(Collectors.toCollection(ArrayList::new));
        messageNumber.set(result.size());
        return result;
    }

    @Override
    public void delete(ReceiptMessagePO messagePO) throws RemoteException {
        dataService.delete(messagePO);
        selectByUser();
    }

    @Override
    public void deleteByReceipt(ReceiptVO receiptVO) throws RemoteException {
        ArrayList<ReceiptMessageVO> candidates = selectByUser();
        ArrayList<ReceiptMessageVO> toBeDelete = candidates.stream()
                .filter(rm -> rm.getReceiptVO().getId().equals(receiptVO.getId())
                        && (rm.getToUser() != null && rm.getToUser().getId() == UserInfomation.userid || rm.getToUserCategory() != null && rm.getToUserCategory() == UserInfomation.usertype))
                .collect(Collectors.toCollection(ArrayList::new));
        // 都是因为有Exception，所以才写的这么丑的
        for (ReceiptMessageVO messageVO : toBeDelete) {
            delete(messageVO.toPO());
//            System.out.println("delete Message By Receipt");
        }
        selectByUser();
    }

    /**
     * private methods
     * */


    private ArrayList<ReceiptMessageVO> posToVos(ArrayList<ReceiptMessagePO> pos) throws RemoteException {
        ArrayList<ReceiptMessageVO> result = new ArrayList<>();
        for (ReceiptMessagePO po : pos) {
            result.add(new ReceiptMessageVO(po));
        }
        return result;
    }

    private ArrayList<ReceiptMessageVO> selectByUserId(int userId) throws RemoteException {
        ArrayList<ReceiptMessagePO> pos = dataService.selectByUser(userId);
        return posToVos(pos);
    }

    private ArrayList<ReceiptMessageVO> selectByUserCategory(UserCategory userCategory) throws RemoteException {
        ArrayList<ReceiptMessagePO> pos = dataService.selectByUserCategory(userCategory);
        return posToVos(pos);
    }
}
