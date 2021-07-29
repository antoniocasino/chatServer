package it.tony.pagopa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestMessageQueue {

    @Mock
    ServerThread serverThread;
    @Mock
    UserInfo userInfo;

    @Before
    public void init(){
        Mockito.when(serverThread.getUser()).thenReturn(userInfo);
    }

    @Test
    public void testAdd(){
        int currentSize = MessageQueue.getQueue().size();
        MessageQueue.addClient(serverThread);
        assert MessageQueue.getQueue().size()==currentSize+1;
    }

    @Test
    public void testAddAndRemove(){
        MessageQueue.addClient(serverThread);
        int currentSize = MessageQueue.getQueue().size();
        MessageQueue.remove(serverThread);
        assert MessageQueue.getQueue().size()==currentSize-1;
    }



}
