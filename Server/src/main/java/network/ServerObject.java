package network;

import annotations.RMIRemote;
import data.messagedata.ReceiptMessageData;
import dataService.messagedataService.ReceiptMessageDataService;
import po.ReceiptMessagePO;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class ServerObject extends UnicastRemoteObject implements ServerInterface {
    private ArrayList<ClientInterface> clients = new ArrayList<>();
//    private ClientInterface client;

    public ServerObject() throws RemoteException {
    }

    @Override
    public void addClient(ClientInterface client) throws RemoteException {
        clients.add(client);
        System.out.println("add client. UserId: " + client.getUserId());
        System.out.println("Server has " + clients.size() + " clients in total");
    }

    @Override
    public void send(ReceiptMessagePO messagePO) throws RemoteException {
        // insert
        ReceiptMessageDataService mdao = new ReceiptMessageData();
        mdao.insert(messagePO);

        // 这里还应该把messagePO更新一下，不然怎么知道id呢。被这个坑了好久
        messagePO = mdao.selectByMold(messagePO);

        // send to online client
        if (messagePO.getToUserCategory() != null) { // 这里是用一个if else来区分两种消息。
            for (ClientInterface client : clients) {
                if (client.getUserCategory() == messagePO.getToUserCategory()) {
                    try {
                        client.receive(messagePO);
                    } catch (ConnectException e) { // TODO 这个exception不知道处理的怎么样……
                        e.printStackTrace();
                        removeClient(client);
                    }
                }
            }
        } else {
            for (ClientInterface client : clients) {
                if (client.getUserId() == messagePO.getToUserId()) {
                    try {
                        client.receive(messagePO);
                    } catch (ConnectException e) {
                        e.printStackTrace();
                        removeClient(client);
                    }
                }
            }
        }

    }

    @Override
    public void removeClient(ClientInterface client) throws RemoteException {
        clients.remove(client);
        System.out.println("remove client. UserId:  " + client.getUserId());
        System.out.println("Server has " + clients.size() + " clients in total");
    }
}
