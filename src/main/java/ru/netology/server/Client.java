package ru.netology.server;

import ru.netology.logger.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Client {
    private final String id;
    private String name;
    private final SocketChannel socketChannel;

    public Client(SocketChannel socketChannel) {
        this.id = UUID.randomUUID().toString();
        this.socketChannel = socketChannel;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean sendMessage(String message, Client client) {
        try {
            socketChannel.write(ByteBuffer.wrap((client.getName() + ": " + message).getBytes(StandardCharsets.UTF_8)));
            return true;
        } catch (IOException e) {
            Logger.getInstance().log(e.getMessage());
            return false;
        }
    }
}
