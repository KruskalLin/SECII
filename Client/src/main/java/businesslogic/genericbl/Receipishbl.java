package businesslogic.genericbl;

import dataService.generic.ReceipishDataService;
import po.generic.ReceipishPO;
import po.promotionPO.MemberPromotionPO;
import po.promotionPO.PromotionPO;
import util.ResultMessage;
import vo.abstractVO.ReceipishVO;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionVO;
import vo.promotionVO.TotalPromotionVO;

import java.lang.reflect.Constructor;
import java.rmi.Remote;
import java.rmi.RemoteException;

// TODO 这个generic怎么这么坑啊！！！我也不懂什么意思…
public abstract class Receipishbl<TV extends ReceipishVO<? extends ReceipishVO>, TP extends ReceipishPO> {
    protected abstract Class<TV> getVOClass();

    protected abstract Class<TP> getPOClass();

    protected abstract ReceipishDataService<TP> getDataService();


    public TV getNew() throws RemoteException {
        return convertToVO(getDataService().getNew());
    }

    public ResultMessage insert(TV receipishVO) throws RemoteException {
        return getDataService().insert(receipishVO.toPO());
    }

    public ResultMessage update(TV promotionVO) throws RemoteException {
        return getDataService().update(promotionVO.toPO());
    }

    public ResultMessage delete(TV promotionVO) throws RemoteException {
        return getDataService().delete(promotionVO.toPO());
    }

    public TV selectByMold(TV receipishVO) throws RemoteException {
        TP x = getDataService().selectByMold(receipishVO.toPO());
        return convertToVO(x);
    }

    protected TV convertToVO(TP receipishPO) {
        Constructor<TV> cstr;
        try {
            cstr = getVOClass().getConstructor(getPOClass());
            return cstr.newInstance(receipishPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("feawpui");
    }
}
