package businesslogic.promotionbl;

import blService.promotionblService.PromotionblService;
import businesslogic.genericbl.Receipishbl;
import dataService.generic.ReceipishDataService;
import dataService.promotiondataService.PromotionDataService;
import po.promotionPO.PromotionPO;
import util.PromotionSearchCondition;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Promotionbl<TV extends PromotionVO, TP extends PromotionPO> extends Receipishbl<TV, TP> implements PromotionblService<TV> {
    private Class<TP> promotionPOClass;
    private Class<TV> promotionVOClass;

    private PromotionDataService<TP> promotionDataService;

    public Promotionbl(Class<TV> promotionVOClass, Class<TP> promotionPOClass) throws RemoteException, NotBoundException, MalformedURLException {
        this.promotionVOClass = promotionVOClass;
        this.promotionPOClass = promotionPOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        String poName = promotionPOClass.getName();
        String promotionDataName = poName.substring(poName.lastIndexOf(".") + 1, poName.length() - 2) + "Data";
        promotionDataService = (PromotionDataService<TP>) Naming.lookup(registrationpre + "/" + promotionDataName);
    }


    // TODO 其实也可以选择让每一个子类分别来写这两个方法
    @Override
    public Class<TV> getVOClass() {
        return promotionVOClass;
    }

    @Override
    public Class<TP> getPOClass() {
        return promotionPOClass;
    }

    @Override
    protected ReceipishDataService<TP> getDataService() {
        return promotionDataService;
    }

    /**
     * implements promotionService
     */

    @Override
    public ArrayList<TV> selectInEffect() throws RemoteException {
        PromotionSearchCondition promotionSearchCondition = new PromotionSearchCondition();
        promotionSearchCondition.setBeginCeil(LocalDateTime.now());
        promotionSearchCondition.setEndFloor(LocalDateTime.now());
//        return promotionDataService.selectInEffect().stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
        return search(promotionSearchCondition);
    }

    @Override
    public ArrayList<TV> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException {
        return promotionDataService.search(promotionSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }
}
