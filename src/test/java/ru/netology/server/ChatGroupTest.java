package ru.netology.server;

import junit.framework.TestCase;
import org.mockito.Mockito;

import java.io.IOException;

public class ChatGroupTest extends TestCase {

    public void testSendMessageToClients() throws IOException {
        //given:
        Client client = Mockito.mock(Client.class);
        Mockito.when(client.getId()).thenReturn("0");
        Client client1 = Mockito.mock(Client.class);
        Mockito.when(client1.getId()).thenReturn("1");
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.add(client);
        chatGroup.add(client1);

        //when:
        chatGroup.sendMessageToClients("", client);

        //then:
        //Убедимся что у каждого клиента был вызван метод отправки сообщения 1 раз
        Mockito.verify(client, Mockito.times(1)).sendMessage("", client);
        Mockito.verify(client1, Mockito.times(1)).sendMessage("", client);
    }
}