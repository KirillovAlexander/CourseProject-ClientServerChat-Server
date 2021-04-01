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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Setting object = (Setting) obj;
        return (address.equals(object.getAddress())
                && port == object.getPort());

    }
}
