package businesslogic.promotionbl;

import po.promotionPO.MemberPromotionPO;
import po.promotionPO.PromotionPO;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MemberPromotionbl extends Promotionbl<MemberPromotionVO, MemberPromotionPO>{
    public MemberPromotionbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(MemberPromotionVO.class, MemberPromotionPO.class);
    }
}
