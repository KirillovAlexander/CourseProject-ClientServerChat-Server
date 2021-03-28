package ru.netology.server;

public class Setting {
    private String address;
    private int port;

    public Setting(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
