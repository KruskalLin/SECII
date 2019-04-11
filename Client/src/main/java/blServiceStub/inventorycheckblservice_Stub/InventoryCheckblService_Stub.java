package blServiceStub.inventorycheckblservice_Stub;

import blService.inventoryblService.InventoryCheckblService;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;

import java.util.HashSet;

public class InventoryCheckblService_Stub implements InventoryCheckblService{
    HashSet<InventoryCheckItemVO> set = new HashSet<>();

    public InventoryCheckblService_Stub() {
        set.add(new InventoryCheckItemVO("123","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("124","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("125","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("126","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("127","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("128","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("129","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("130","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("131","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("132","111",100,50.0,"2011-11-1","2","323"));
        set.add(new InventoryCheckItemVO("133","111",100,50.0,"2011-11-1","2","323"));
    }

    @Override
    public InventoryCheckVO inventoryCheck() {
        return new InventoryCheckVO(set);
    }
}
