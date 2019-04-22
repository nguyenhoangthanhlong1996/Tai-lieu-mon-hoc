package client.socket;

import client.stages.Authenticate;
import client.stages.Chat;
import javafx.application.Application;
import share.Config;
import share.protocol.Request;
import share.protocol.Response;

import java.io.*;
import java.net.Socket;

public class SingletonConnect {
    private static SingletonConnect instance;

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    boolean logged;
    Thread threadReceiverResponse;

    //Constructor khởi tạo kết nối socket và chỉ gọi được bên trong nội bộ
    private SingletonConnect() {
        initConnect();
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
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Tạo luồng nhận response từ server
        threadReceiverResponse = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Response response = null;
                        try {
                            if ((response = (Response) objectInputStream.readObject()) != null) {
                                getAuthenticateStage().showAlert("đăng nhập thành công", "");
                            } else { //Mất kết nối tới server
                                closeConnection();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) { //Mất kết nối tới server
                        e.printStackTrace();
                        closeConnection();
                    }
                }
            }
        });
        threadReceiverResponse.setName("Thread receive response");
        threadReceiverResponse.start();
    }

    public Authenticate getAuthenticateStage() {
        return Authenticate.getInstance();
    }

    public Chat getChatStage() {
        return null;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void start() {
        //Nếu chưa đăng nhập thì hiện giao diện đăng nhập
        if (isLogged() == false) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Application.launch(Authenticate.class);
                }
            }).start();
        } else { //Hiện giao diện đăng ký

        }
    }

    //Hàm này sẽ được gọi khi bị mất kết nối tới Server
    private void closeConnection() {
        try {
            //Đòng kết nối socket
            socket.close();
            if (isLogged() == false && getAuthenticateStage() != null) {
                getAuthenticateStage().disconnect();
            }
            //Dừng thread lắng nghe phản hồi từ Server
            threadReceiverResponse.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(Request request) {
        try {
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
