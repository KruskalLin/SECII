package util;

import java.util.HashMap;

public enum EventCategory {
    CreateUser,DeleteUser,UpdateUser,CreateReceipt,UpdateReceipt,DeleteReceipt,RejectReceipt,ApproveReceipt,
    CreateMember,UpdateMember,DeleteMember,CreateAccount,
    DeleteAccount,UpdateAccount,InitialAccount,InventoryView,InventoryCheck,ExportExcel;

    public static HashMap<EventCategory,String> map = new HashMap<>();
    public static HashMap<String,String> event = new HashMap<>();
    static{
        map.put(EventCategory.CreateUser,"新建了用户");
        map.put(EventCategory.DeleteUser,"删除了用户");
        map.put(EventCategory.UpdateUser,"更新了用户");
        map.put(EventCategory.CreateReceipt,"新建了单据");
        map.put(EventCategory.UpdateReceipt,"更新了单据");
        map.put(EventCategory.DeleteReceipt,"删除了单据");
        map.put(EventCategory.RejectReceipt,"拒绝了单据");
        map.put(EventCategory.ApproveReceipt,"通过了单据");
        map.put(EventCategory.CreateMember,"新建了客户");
        map.put(EventCategory.UpdateMember,"更新了客户");
        map.put(EventCategory.DeleteMember,"删除了客户");
        map.put(EventCategory.CreateAccount,"新建了账户");
        map.put(EventCategory.DeleteAccount,"删除了账户");
        map.put(EventCategory.UpdateAccount,"更新了账户");
        map.put(EventCategory.InitialAccount,"期初建账");
        map.put(EventCategory.InventoryView,"库存查看");
        map.put(EventCategory.InventoryCheck,"库存盘点");
        map.put(EventCategory.ExportExcel,"导出Excel");
    }


    private final int value;

    private EventCategory() {
        this.value = ordinal();
    }



}
