package it.tony.pagopa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@RunWith(MockitoJUnitRunner.class)
public class TestProcessCommand {

    @Mock
    ServerThread serverThread;
    @Mock
    UserInfo userInfo;
    @Mock
    BufferedReader reader;
    @Mock
    PrintWriter printWriter;
    @Mock
    Socket socket;

    @Before
    public void init() {
        Mockito.when(serverThread.getUser()).thenReturn(userInfo);
        Mockito.when(serverThread.getPrintWriter()).thenReturn(printWriter);
        Mockito.when(serverThread.getClient()).thenReturn(socket);
    }

    @Test
    public void testBaseFlow() throws IOException {
        ProcessCommand processCommand = new ProcessCommand(serverThread);
        processCommand.processSocket(reader);
    }

    @Test
    public void testLoginThenMessageThenBye() throws IOException {
        ProcessCommand processCommand = new ProcessCommand(serverThread);
        Mockito.when(reader.readLine()).thenReturn("antonio").thenReturn("good morning").thenReturn("bye");
        Mockito.when(userInfo.getName()).thenReturn("antonio");
        processCommand.processSocket(reader);
    }


}
