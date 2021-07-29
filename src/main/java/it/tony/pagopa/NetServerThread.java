package it.tony.pagopa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class NetServerThread implements ServerThread {

    private final Socket client;
    private final PrintWriter printWriter;
    private final UserInfo user;
    private final ProcessCommand processCommand;

    public NetServerThread(Socket client) {
        this.client = client;
        this.printWriter = printWriter(this.client);
        this.user = new UserInfo();
        this.processCommand = new ProcessCommand(this);
    }

    private PrintWriter printWriter(Socket socket) {
        try {
            return new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            throw new RuntimeException("cannot create writer");
        }
    }

    @Override
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Override
    public UserInfo getUser() {
        return user;
    }

    @Override
    public Socket getClient() {
        return client;
    }

    @Override
    public ProcessCommand getProcessCommand() {
        return processCommand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetServerThread that = (NetServerThread) o;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}

