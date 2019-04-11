package serverLauncher;

import annotations.RMIRemote;
import org.reflections.Reflections;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.Set;

public class ServerMain {
    public static void main(String[] args) {
        try {
            String registry = "localhost";
            int port = 1099;
            String registrationpre = "rmi://" + registry + ":" + port;

            LocateRegistry.createRegistry(port);

            Reflections reflections = new Reflections("data", "network");
            Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(RMIRemote.class);
            classesList.forEach(c -> {
                try {
                    Remote toBeRegistered = (Remote) c.newInstance();
                    String classFullName = c.getName();
                    System.out.println("bind " + classFullName.substring(classFullName.lastIndexOf(".") + 1));
                    Naming.rebind(registrationpre + "/" + classFullName.substring(classFullName.lastIndexOf(".") + 1)
                            , toBeRegistered);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            System.out.println();
            System.out.println("server starts");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
