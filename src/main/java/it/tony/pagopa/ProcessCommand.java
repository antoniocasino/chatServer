package it.tony.pagopa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ProcessCommand {

    private final ServerThread serverThread;

    public ProcessCommand(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public void processSocket(BufferedReader reader) throws IOException {
        PrintWriter printWriter = serverThread.getPrintWriter();
        UserInfo user = serverThread.getUser();
        printWriter.println("Welcome to the chat room. Please enter your username: \r\n");
        String userName = reader.readLine();
        user.login(userName);

        printWriter.println("Logged successfully as " + user.getName() + ". Start chatting with friends!! Enter 'bye' to close");

        MessageQueue.addClient(this.serverThread);
        String input = reader.readLine();
        while (input != null && !"bye".equals(input)) {
            System.out.println("message by " + user.getName() + ": " + input);
            //Send to other clients
            MessageQueue.sendToAll(user, input);
            input = reader.readLine();
        }
        System.out.println("User " + user.getName() + " is offline.");
        this.closeMe();
    }

    public void closeMe() {
        try {
            MessageQueue.remove(this.serverThread);
            this.serverThread.getClient().close();
            this.serverThread.getUser().logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
