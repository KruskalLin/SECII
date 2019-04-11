package util;

import java.util.HashMap;

public enum ReceiptState {
    APPROVED, PENDING, DRAFT, REJECTED;
    public static HashMap<String,String> color = new HashMap<>();
    public static HashMap<String,ReceiptState> map = new HashMap<>();
    public static HashMap<ReceiptState,String> chinese = new HashMap<>();
    static{
        for(ReceiptState receiptState:values()){
            map.put(receiptState.name(),receiptState);
        }

        for(ReceiptState state:values()){
            if(state==ReceiptState.APPROVED){
                color.put(state.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:green;");
            }else if(state==ReceiptState.REJECTED){
                color.put(state.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:red;");
            }else if(state==ReceiptState.PENDING){
                color.put(state.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:#ffa829;");
            }else if(state==ReceiptState.DRAFT){
                color.put(state.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:blue;");
            }
        }
        for(ReceiptState state:values()){
            if(state==ReceiptState.APPROVED){
                chinese.put(state,"通过");
            }else if(state==ReceiptState.REJECTED){
                chinese.put(state,"拒绝");
            }else if(state==ReceiptState.PENDING){
                chinese.put(state,"待审核");
            }else if(state==ReceiptState.DRAFT){
                chinese.put(state,"草稿");
            }
        }



    }
}
