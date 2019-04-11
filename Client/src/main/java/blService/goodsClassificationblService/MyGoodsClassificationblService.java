package blService.goodsClassificationblService;

import vo.inventoryVO.RecursiveGoodsClassificationVO;

import java.rmi.RemoteException;

public interface MyGoodsClassificationblService {
    RecursiveGoodsClassificationVO fuzzySearchRoot(String keyword) throws RemoteException;

    RecursiveGoodsClassificationVO selectRoot() throws RemoteException;

    RecursiveGoodsClassificationVO selectById(String id) throws RemoteException;

    void add(RecursiveGoodsClassificationVO vo) throws RemoteException;

    void delete(RecursiveGoodsClassificationVO vo) throws RemoteException;

    void update(RecursiveGoodsClassificationVO vo) throws RemoteException;

}
