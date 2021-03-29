package ru.netology.server;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatGroup {
    private final Map<String, Client> clientMap = new ConcurrentHashMap<>();

    public void add(Client client) {
        clientMap.put(client.getId(), client);
    }

    public void remove(String id) {
        clientMap.remove(id);
    }

    public void sendMessageToClients(String message, Client client) throws IOException {
        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
            entry.getValue().sendMessage(message, client);
        }
    }
}
