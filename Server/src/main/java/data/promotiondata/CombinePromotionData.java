package data.promotiondata;

import annotations.RMIRemote;
import mapper.CombinePromotionPOMapper;
import mapper.generic.PromotionPOMapper;
import po.promotionPO.CombinePromotionPO;

import java.rmi.RemoteException;

@RMIRemote
public class CombinePromotionData extends PromotionData<CombinePromotionPO> {
    public CombinePromotionData() throws RemoteException {
        super(CombinePromotionPOMapper.class, CombinePromotionPO.class);
    }
}
