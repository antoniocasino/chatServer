package it.tony.pagopa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public interface ServerThread extends Runnable {

    Socket getClient();

    PrintWriter getPrintWriter();

    UserInfo getUser();

    ProcessCommand getProcessCommand();

    default void sendMsgToMe(String msg) {
        getPrintWriter().println(msg);
    }

    @Override
    default void run() {
        try {
            getProcessCommand()
                    .processSocket(new BufferedReader(new InputStreamReader(getClient().getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
