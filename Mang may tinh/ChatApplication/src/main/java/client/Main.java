package client;


import client.socket.SingletonConnect;
import client.stages.Authenticate;
import client.stages.Chat;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        //Khởi tạo kết nối tới server
        SingletonConnect singletonConnect = SingletonConnect.getInstance();
        //Start
        singletonConnect.start();
    }
}
