package server.controllers.objectUI;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InfoConnect {
    private SimpleIntegerProperty port;
    private SimpleStringProperty username;

    public InfoConnect(int port, String username) {
        this.port = new SimpleIntegerProperty(port);
        this.username = new SimpleStringProperty(username);
    }

    public int getPort() {
        return port.get();
    }


    public String getUsername() {
        return username.get();
    }

    public void setPort(int port) {
        this.port.set(port);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
}
