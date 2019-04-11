package blService.promotionblService;

import util.PromotionSearchCondition;
import util.ResultMessage;
import vo.promotionVO.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionblService<T extends PromotionVO> {
    T getNew() throws RemoteException;
    ResultMessage insert(T promotionVO) throws RemoteException;
    ResultMessage update(T promotionVO) throws RemoteException;
    ResultMessage delete(T promotionVO) throws RemoteException;
    T selectByMold(T promotionVO) throws RemoteException;
    ArrayList<T> selectInEffect() throws RemoteException;
    ArrayList<T> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException;
}
