package it.tony.pagopa;

import java.util.ArrayList;
import java.util.List;

public class MessageQueue {

    private static final List<ServerThread> sList = new ArrayList();

    public static List<ServerThread> getQueue() {
        return sList;
    }

    public static void addClient(ServerThread ct) {
        sendToAll(ct.getUser(), " I am online! People in the chat room are: " + sList.size());
        sList.add(ct);
    }

    public static void remove(ServerThread ct) {
        sList.remove(ct);
        sendToAll(ct.getUser(), "user is offline.");
    }

    public static void sendToAll(UserInfo sender, String msg) {
        msg = sender.getName() + " : " + msg;
        for (int i = 0; i < sList.size(); i++) {
            ServerThread st = sList.get(i);
            st.sendMsgToMe(msg);//This is a reflection of the group chat feature, iterating through all the clients, and then sending a message to each client
        }
    }

}

