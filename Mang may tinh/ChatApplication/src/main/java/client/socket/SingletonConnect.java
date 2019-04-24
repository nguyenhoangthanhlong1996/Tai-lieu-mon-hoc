package client.socket;

import client.stages.ClientApp;
import client.stages.ScenceOption;
import share.Config;
import share.entity.User;
import share.protocol.Request;
import share.protocol.Response;
import share.protocol.ResponseType;
import java.io.*;
import java.net.Socket;

public class SingletonConnect {
    //Instance SingletonConnect tĩnh
    private static SingletonConnect instance;
    ClientApp app;
    //socket ở client
    Socket socket;
    //Đối tượng nhận dữ liệu
    ObjectInputStream objectInputStream;
    //Đối tượng gửi dữ liệu
    ObjectOutputStream objectOutputStream;
    //kiểm soát trang thái đăng nhập
    boolean logged;
    //Luồng lắng nghe phản hồi từ Server
    Thread threadReceiverResponse;

    //Constructor khởi tạo kết nối socket và chỉ gọi được bên trong nội bộ
    private SingletonConnect() {
        //Lấy ra được instance ClientApp
        app = ClientApp.getInstance();
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
                                hanleResponse(response);
                            } else { //Mất kết nối tới server
                                disconnected();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) { //Mất kết nối tới server
                        e.printStackTrace();
                        disconnected();
                    }
                }
            }
        });
        threadReceiverResponse.setName("Thread receive response");
        threadReceiverResponse.start();
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    //Hàm khởi chạy
    public void start() {
        //Nếu chưa đăng nhập thì hiện giao diện đăng nhập
        if (isLogged() == false) {
            app.setDisplayScence(ScenceOption.SIGN_IN);
        } else { //Hiện giao diện chat
            app.setDisplayScence(ScenceOption.CHAT);
        }
    }

    //Hàm này sẽ được gọi khi bị mất kết nối tới Server
    private void disconnected () {
        try {
            //Đòng kết nối socket
            socket.close();
            if (isLogged() == false && ClientApp.getInstance() != null) {
                app.showAlertDisconnect();
            }
            //Dừng thread lắng nghe phản hồi từ Server
            threadReceiverResponse.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Hàm gửi yêu cầu lên Server
    public void sendRequest(Request request) {
        try {
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Xử lý các phản hồi từ server
    private void hanleResponse(Response response) {
        ResponseType responseType = response.getResponseType();
        switch (responseType) {
            case LOGIN:
                //region LOGIN
                if (response.isSuccess()) {//Đăng nhập thành công
                    //Sẽ lấy ra được thông tin User
                    User user = (User) response.getData();
                    app.setDisplayScence(ScenceOption.CHAT);
                    app.ctrChat.showMyInfo(user);
                } else {//Đăng nhập thất bại
                    //Sẽ lấy ra được lỗi
                    app.showAlert("Đăng nhập thất bại", (String) response.getData());
                }
                break;
            //endregion
            case LOGOUT:
                break;
            case REGISTER:
                //region REGISTER
                if (response.isSuccess()) {//Đăng ký thành công
                    app.showAlert("Đăng ký thành công", "");
                    app.setDisplayScence(ScenceOption.SIGN_IN);
                } else {//Đăng nhập thất bại
                    //Sẽ lấy ra được lỗi
                    app.showAlert("Đăng ký thất bại", (String) response.getData());
                }
                break;
            //endregion
        }
    }
}
