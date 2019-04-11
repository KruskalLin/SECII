package businesslogic.blServiceFactory;

import java.rmi.RemoteException;

public class FactoryController {
    public static void clearAllSavedService() {
        MyblServiceFactory.clearAllSavedService();
        MyServiceFactory.clearAllSavedService();
        MessageObjectFactory.clearAllSavedService();
    }

    public static void initiateService() throws RemoteException {
        MessageObjectFactory.initiateServerInterface();
    }
}
