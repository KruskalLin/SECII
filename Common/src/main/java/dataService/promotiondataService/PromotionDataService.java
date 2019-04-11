package dataService.promotiondataService;

import dataService.generic.ReceipishDataService;
import po.promotionPO.PromotionPO;
import util.PromotionSearchCondition;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionDataService<T extends PromotionPO> extends ReceipishDataService<T> {
    ArrayList<T> selectInEffect() throws RemoteException;
    ArrayList<T> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException;
}
