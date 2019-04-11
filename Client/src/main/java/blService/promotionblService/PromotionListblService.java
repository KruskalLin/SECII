package blService.promotionblService;

import util.PromotionSearchCondition;
import util.ResultMessage;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionListblService {
    ArrayList<PromotionVO> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException;
    ResultMessage delete(PromotionVO promotionVO) throws RemoteException, MalformedURLException, NotBoundException;
}
