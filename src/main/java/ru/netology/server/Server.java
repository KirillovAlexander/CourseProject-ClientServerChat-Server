package ru.netology.server;


import ru.netology.logger.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private static final String SETTINGS_FILE_NAME = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Settings.txt";

    public static void main(String[] args) {
        Setting setting = getSetting(SETTINGS_FILE_NAME);
        if (setting == null) return;
        ChatGroup chatGroup = new ChatGroup();
        try {
            final ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(setting.getAddress(), setting.getPort()));
            while (true) {
                SocketChannel socketChannel = serverChannel.accept();
                Logger.getInstance().log("Подключился новый клиент;");
                new ClientThread(socketChannel, chatGroup);
            }
        } catch (IOException ex) {
            Logger.getInstance().log(ex.getMessage());
        }
    }

    static Setting getSetting(String path) {
        StringBuilder settingsAsString = new StringBuilder();
        try (FileReader fileReader = new FileReader(path)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                settingsAsString.append((char) c);
            }
            String[] settingParts = settingsAsString.toString().split(";");
            String address = settingParts[0];
            int port = Integer.parseInt(settingParts[1]);
            Logger.getInstance().log("Прочитаны настройки;");
            return new Setting(address, port);
        } catch (IOException ex) {
            Logger.getInstance().log(ex.getMessage());
        }
        return null;
    }
}
