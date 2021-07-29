package it.tony.pagopa;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerSocketFactory {
    ServerSocket buildServerSocket(int port) throws IOException;
}