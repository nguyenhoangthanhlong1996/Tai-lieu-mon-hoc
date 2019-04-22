package server;

import javafx.application.Application;
import server.stages.Server;
import share.dao.UserDAO;
import share.entity.User;

public class Main {
    public static void main(String[] args) {
        Application.launch(Server.class, args);
    }
}
