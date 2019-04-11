package businesslogic.promotionbl;

import po.promotionPO.PromotionPO;
import po.promotionPO.TotalPromotionPO;
import vo.promotionVO.PromotionVO;
import vo.promotionVO.TotalPromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TotalPromotionbl extends Promotionbl<TotalPromotionVO, TotalPromotionPO>{
    public TotalPromotionbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(TotalPromotionVO.class, TotalPromotionPO.class);
    }
}
