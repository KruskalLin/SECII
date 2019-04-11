package util;

import po.LogPO;
import po.MemberPO;
import po.MessagePO;
import po.UserPO;
import po.generic.ReceipishPO;
import po.receiptPO.ReceiptPO;
import po.receiptPO.SalesReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import po.receiptPO.StockReceiptPO;

import java.lang.reflect.Field;

public class NameCreator {


    public static String changeName(String arg){
        if(arg.equals("[B")){
            return "";
        }else if(arg.equals("java.time.LocalDate")||arg.equals("java.time.LocalDateTime")){
            return "bigint";
        }else if(arg.equals("java.lang.String")){
            return "varchar(100)";
        }else if(arg.equals("double")){
            return "double";
        }else if(arg.equals("int")){
            return "integer";
        }
        return "integer";
    }


    public static void getClassString(Class clazz){
        System.out.println("create table "+clazz.getName().split("\\.")[1]+"(");
        for(Field field: ReceipishPO.class.getDeclaredFields())
            System.out.println(field.getName()+" "+changeName(field.getType().getName())+",");
        for(Field field: ReceiptPO.class.getDeclaredFields())
            System.out.println(field.getName()+" "+changeName(field.getType().getName())+",");
        for(Field field:clazz.getDeclaredFields()){
            System.out.println(field.getName()+" "+changeName(field.getType().getName())+",");
        }
        System.out.println(");");
    }

    public static void getSomeString(Class clazz){
        for(Field field:clazz.getDeclaredFields()){
            System.out.print(field.getName()+",");
        }
        System.out.println();
        for(Field field:clazz.getDeclaredFields()){
            System.out.print(field.getName()+"=#{"+field.getName()+"},");
        }
    }


    public static void main(String args[]){
        getSomeString(LogPO.class);
    }

}
