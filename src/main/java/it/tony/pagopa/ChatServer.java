package it.tony.pagopa;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private ServerSocket serverSocket;

    public ChatServer(ServerSocketFactory serverSocketFactory, Class<? extends ServerThread> clazz, int port) {
        try {
            System.out.println("Create server success! Port:" + port);
            serverSocket = serverSocketFactory.buildServerSocket(port);
            while (true) {
                Socket conn = serverSocket.accept();//let the server enter the wait state
                ServerThread handler = clazz.getDeclaredConstructor(conn.getClass()).newInstance(conn);
                System.out.println("Login a user, ip address is: " + conn.getRemoteSocketAddress().toString());
                new Thread(handler).start();//start thread
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] argv) {
        new ChatServer(new NetServerSocketFactory(), NetServerThread.class, 10000);
    }
}

