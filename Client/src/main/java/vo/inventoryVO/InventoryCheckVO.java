package vo.inventoryVO;

import java.util.Set;

public class InventoryCheckVO {
    /** 商品列表 */
    private Set<InventoryCheckItemVO> checkList;

    public InventoryCheckVO() {
    }

    public InventoryCheckVO(Set<InventoryCheckItemVO> checkList) {
        this.checkList = checkList;
    }

    public Set<InventoryCheckItemVO> getCheckList() {
        return checkList;
    }

    public void setCheckList(Set<InventoryCheckItemVO> checkList) {
        this.checkList = checkList;
    }
}
