package client.socket;

import client.stages.Authenticate;
import client.stages.Chat;
import javafx.application.Application;
import server.controllers.LogLevel;
import share.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SingletonConnect {
    private static SingletonConnect instance;

    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Authenticate authenticateStage;
    Chat chatStage;
    boolean logged;
    Thread threadReceiverResponse;

    //Constructor khởi tạo kết nối socket và chỉ gọi được bên trong nội bộ
    private SingletonConnect() {
        initConnect();
        showStage();
    }

    //Hàm trả về instance hiện tại
    public static SingletonConnect getInstance() {
        if (instance == null) {
            instance = new SingletonConnect();
        }
        return instance;
    }

    //Khởi tạo kết nối
    private void initConnect() {
        //Tạo socket
        try {
            socket = new Socket(Config.HOST_SEVER, Config.PORT_LISTEN_SERVER);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Tạo luồng nhận response từ server
        threadReceiverResponse = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String result = "";
                        if ((result = bufferedReader.readLine()) != null) {

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Mất kết nối tới Server");
                    }
                }
            }
        });
        threadReceiverResponse.setName("Thread receive response");
        threadReceiverResponse.start();
    }

    public void setAuthenticateStage(Authenticate authenticateStage) {
        this.authenticateStage = authenticateStage;
    }

    public void setChatStage(Chat chatStage) {
        this.chatStage = chatStage;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    private void showStage() {
        //Nếu chưa đăng nhập thì hiện giao diện đăng nhập
        if (isLogged() == false) {
            Application.launch(Authenticate.class);
        }
    }
}
