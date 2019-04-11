//package businesslogic.blServiceFactory;
//
//
//import java.lang.reflect.Field;
//
//public class ServiceFactory {

//
//    public Object getClass(String clazz) throws Exception{
//        Object obj = null;
//        Field field = this.getClass().getDeclaredField(clazz);
//        Class<?> c = null;
//        if(field.get(this.getClass())==null){
//            c = Class.forName("blServiceStub."+field.getName().toLowerCase()+"_Stub."+Character.toUpperCase(field.getName().charAt(0))+field.getName().substring(1)+"_Stub");
//            obj = c.newInstance();
//            field.set(this.getClass(),obj);
//        }else{
//            obj=field.get(this.getClass());
//        }
//        return obj;
//    }
//
//
//
//}
//