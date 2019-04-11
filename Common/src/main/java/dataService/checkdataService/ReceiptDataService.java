package dataService.checkdataService;

import dataService.generic.ReceipishDataService;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import util.ReceiptSearchCondition;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReceiptDataService<T extends ReceiptPO> extends ReceipishDataService<T> {
    ArrayList<T> selectBetween(LocalDateTime begin, LocalDateTime end) throws RemoteException;
    ArrayList<T> selectByState(ReceiptState receiptState) throws RemoteException;
    ArrayList<T> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
    ArrayList<T> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException;
}
