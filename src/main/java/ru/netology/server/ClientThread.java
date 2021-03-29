package ru.netology.server;

import ru.netology.logger.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientThread extends Thread {
    private final SocketChannel socketChannel;
    private final ByteBuffer inputBuffer;
    private final ChatGroup chatGroup;
    private final Client client;

    public ClientThread(SocketChannel socketChannel, ChatGroup chatGroup) {
        this.socketChannel = socketChannel;
        this.inputBuffer = ByteBuffer.allocate(2 << 10);
        this.chatGroup = chatGroup;
        this.client = new Client(socketChannel);
        chatGroup.add(client);
        start();
    }

    @Override
    public synchronized void run() {
        int bytesCount;
        while (socketChannel.isConnected()) {
            try {
                bytesCount = socketChannel.read(inputBuffer);
                if (bytesCount == -1) break;
                final String msg = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                inputBuffer.clear();
                Logger.getInstance().log("Получено сообщение от клиента :" + msg);
                processMessage(msg);
            } catch (IOException ex) {
                Logger.getInstance().log(ex.getMessage());
            }
        }
        chatGroup.remove(client.getId());
        Logger.getInstance().log("Завершение работы с клиентом: " + client.getName());
    }

    private void processMessage(String message) throws IOException {
        if (message.contains("<SetName>")) {
            String name = getName(message);
            client.setName(name);
            Logger.getInstance().log("Имя: '" + name + "' установлено для клиента.");
        } else if (message.contains("<Message>")) {
            String msg = getMessage(message);
            chatGroup.sendMessageToClients(msg, client);
        }
    }

    private String getName(String message) {
        return message.replace("<SetName>", "");
    }

    private String getMessage(String message) {
        return message.replace("<Message>", "");
    }
}
