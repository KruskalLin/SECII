package blServiceStub.inventoryviewblservice_Stub;

import blService.inventoryblService.InventoryViewblService;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;

import java.time.LocalDate;
import java.util.HashSet;

public class InventoryViewblService_Stub implements InventoryViewblService {
    HashSet<InventoryViewItemVO> set = new HashSet<>();

    public InventoryViewblService_Stub() {
        set.add(new InventoryViewItemVO("123","111",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("124","112",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("125","113",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("126","114",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("127","115",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("128","116",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("129","117",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("130","118",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("131","119",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("132","120",30,40.0,50,50.0,60,60.0,70,70.0));
        set.add(new InventoryViewItemVO("133","121",30,40.0,50,50.0,60,60.0,70,70.0));
    }

    public InventoryViewVO inventoryView(String beginDate, String endDate) {
        return new InventoryViewVO(beginDate,endDate,100,set);
    }

    @Override
    public InventoryViewVO inventoryView(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
