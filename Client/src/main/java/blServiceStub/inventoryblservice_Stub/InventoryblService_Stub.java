/*
package blServiceStub.inventoryblservice_Stub;

import blService.inventoryblService.InventoryblService;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.*;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InventoryblService_Stub implements InventoryblService {

    HashSet<InventoryReceiptListVO> set = new HashSet<>();
    int i = 111;

    public InventoryblService_Stub() {
        int i=10;

        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.PENDING,"sabe",true,InventoryReceiptType.InventoryGift));
    }

    @Override
    public int getDayId() {
        i++;
        return i;
    }

    @Override
    public Set<InventoryReceiptListVO> search(InventorySearchVO stockSearchVO, InventoryReceiptType receiptType) {
        return set;
    }

    @Override
    public Set<InventoryReceiptListVO> search(String keyword, InventoryReceiptType receiptType) {
        return null;
    }

    @Override
    public InventoryReceiptVO showDetail(String id) {
        ArrayList<ReceiptGoodsItemVO> list = new ArrayList();
        list.add(new ReceiptGoodsItemVO("a",1,70,1,1,69));
        list.add(new ReceiptGoodsItemVO("a",2,70,1,1,69));
        return new InventoryReceiptVO("JHD-20170101-00013",3,LocalDateTime.of(2017,1,1,0,0),LocalDateTime.now(),ReceiptState.REJECTED,"wad",list,"awda",InventoryReceiptType.InventoryGift);

    }

    @Override
    public Set<InventoryReceiptListVO> getALL(InventoryReceiptType receiptType) {
        return set;
    }

    @Override
    public InventoryReceiptVO getNew() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage insert(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage delete(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public InventoryReceiptVO selectByMold(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<InventoryReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<InventoryReceiptVO> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        return null;
    }
}
*/
