package blService.checkblService;

import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CheckblService {
    ArrayList<ReceiptVO> initCheck() throws RemoteException;
//    public receiptVO showDetail(int id);
    ResultMessage approve(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException;
    ResultMessage reject(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException;
    ResultMessage update(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException;
//    public ArrayList<receiptVO> search(CheckSearchVO checkSearchVO);
}
