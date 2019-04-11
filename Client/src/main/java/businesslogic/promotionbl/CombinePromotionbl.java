package businesslogic.promotionbl;

import po.promotionPO.CombinePromotionPO;
import po.promotionPO.PromotionPO;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CombinePromotionbl extends Promotionbl<CombinePromotionVO, CombinePromotionPO> {
    public CombinePromotionbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(CombinePromotionVO.class, CombinePromotionPO.class);
    }
}
