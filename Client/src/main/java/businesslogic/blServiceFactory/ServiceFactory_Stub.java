//package businesslogic.blServiceFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ServiceFactory_Stub {
//    private static ServiceFactory_Stub serviceFactory_stub;
//    private static Map<String, Object> serviceMap = new HashMap<>();
//    public synchronized static <TF> TF getService(String serviceName) {
//        String className = serviceName.split("\\.")[2];
//        if (serviceMap.containsKey(serviceName)) {
//            return (TF) serviceMap.get(serviceName);
//        }
//        String searchName="blServiceStub."+className.toLowerCase()+"_Stub."+className+"_Stub";
//        System.out.println(searchName);
//        try {
//            Class<?> implClass = Class.forName(searchName);
//            Object result = implClass.newInstance();
//            serviceMap.put(serviceName, result);
//            return (TF) result;
//        } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public synchronized static ServiceFactory_Stub getServiceFactory_stub() {
//        if(serviceFactory_stub==null)
//            serviceFactory_stub = new ServiceFactory_Stub();
//        return serviceFactory_stub;
//    }
//
//}
