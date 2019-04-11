package businesslogic.blServiceFactory;

import network.ClientInterface;
import network.ClientObject;
import network.ServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MessageObjectFactory {
    private static ServerInterface serverInterface;
    private static ClientInterface clientObject;

    public synchronized static ServerInterface getServerInterface() throws RemoteException {
        if (serverInterface == null) {
            initiateServerInterface();
        }
        return serverInterface;
    }

    static void initiateServerInterface() throws RemoteException {
        if (serverInterface != null) { // TODO 这里的=null != null写的好像不是很好…
            clearAllSavedService();
        }

        try {
            serverInterface = (ServerInterface) Naming.lookup("rmi://localhost:1099/ServerObject");
            clientObject = new ClientObject();
            serverInterface.addClient(clientObject);
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static void clearAllSavedService() {
        if (serverInterface == null) {
            return;
        }
        try {
            serverInterface.removeClient(clientObject);
        } catch (RemoteException e) { // TODO 这个异常感觉没法处理啊？
            e.printStackTrace();
        }
        serverInterface = null;

    }

}
