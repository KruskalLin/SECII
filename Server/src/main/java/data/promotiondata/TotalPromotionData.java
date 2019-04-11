package data.promotiondata;

import annotations.RMIRemote;
import mapper.TotalPromotionPOMapper;
import po.promotionPO.TotalPromotionPO;

import java.rmi.RemoteException;

@RMIRemote
public class TotalPromotionData extends PromotionData<TotalPromotionPO> {
    public TotalPromotionData() throws RemoteException {
        super(TotalPromotionPOMapper.class, TotalPromotionPO.class);
    }
}
