package data.promotiondata;

import annotations.RMIRemote;
import mapper.MemberPromotionPOMapper;
import mapper.generic.PromotionPOMapper;
import po.promotionPO.MemberPromotionPO;

import java.rmi.RemoteException;

@RMIRemote
public class MemberPromotionData extends PromotionData<MemberPromotionPO> {
    public MemberPromotionData() throws RemoteException {
        super(MemberPromotionPOMapper.class, MemberPromotionPO.class);
    }

    @Override
    protected void setInitialValue(MemberPromotionPO receipishPO) {
        super.setInitialValue(receipishPO);
        receipishPO.setDiscountFraction(1);
    }
}
