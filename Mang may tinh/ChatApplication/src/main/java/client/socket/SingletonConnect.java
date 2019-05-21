package client.socket;

import client.stages.ClientApp;
import client.stages.ScenceOption;
import javafx.stage.FileChooser;
import share.Config;
import share.data.ConversationData;
import share.data.FileInfoData;
import share.data.ListMessageData;
import share.entity.Conversation;
import share.entity.Message;
import share.entity.User;
import share.protocol.Request;
import share.protocol.Response;
import share.protocol.ResponseType;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class SingletonConnect {
    //Instance SingletonConnect tĩnh
    private static SingletonConnect instance;
    ClientApp app;
    //socket ở client
    Socket socket;
    //User hiện tại đang đăng nhập
    public User user;
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
        user = null;
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
    private void disconnected() {
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
                    user = (User) response.getData();
                    app.setDisplayScence(ScenceOption.CHAT);
                    app.ctrChat.showMyInfo();
                } else {//Đăng nhập thất bại
                    //Sẽ lấy ra được lỗi
                    app.showAlert("Đăng nhập thất bại", (String) response.getData());
                }
                break;
            //endregion
            case LOGOUT:
                //region LOGOUT
                if (response.isSuccess()) {
                    app.logout();
                } else {
                    //Sẽ lấy ra được lỗi
                    app.showAlert("Đăng xuất thất bại", (String) response.getData());
                }
                break;
            //endregion
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
            case GET_LIST_USER:
                //region GET_LIST_USER
                if (response.isSuccess()) {
                    List<User> list = (List<User>) response.getData();
                    app.ctrChat.refreshUI_ListUser(list);
                }
                break;
            //endregion
            case NOTIFY_LIST_USER:
                //region BROADCAST_LIST_USER
                app.ctrChat.requestListUser();
                break;
            //endregion
            case CREATE_CONVERSATION_PRIVATE:
                //region CREATE_CONVERSATION
                if (response.isSuccess()) {
                    app.showAlert("Cuộc hội thoại đã được tạo, hãy qua mục tin nhắn để bắt đầu nhắn tin", "");
                    //Gửi yêu cầu lấy danh sách tất cả cuộc hội thoại
                    app.ctrChat.requestListConversation();
                } else {
                    app.showAlert("Lỗi", (String) response.getData());
                }
                break;
            //endregion
            case GET_LIST_CONVERSATION:
                //region GET_LIST_CONVERSATION
                if (response.isSuccess()) {
                    List<ConversationData> list = (List<ConversationData>) response.getData();
                    app.ctrChat.refreshUI_ListConversation(list);
                } else {
                    app.showAlert("Lỗi", (String) response.getData());
                }
                break;
            //endregion
            case NOTIFY_LIST_CONVERSATION:
                //region NOTIFY_LIST_CONVERSATION
                //Gửi yêu cầu lấy danh sách các cuộc hội thoại
                app.ctrChat.requestListConversation();
                break;
            //endregion
            case GET_LIST_MESSAGE:
                //region GET_LIST_MESSAGE
                if (response.isSuccess()) {
                    ListMessageData listMessageData = (ListMessageData) response.getData();
                    app.ctrChat.refreshUI_ListMessage(listMessageData);
                } else {
                    app.showAlert("Lỗi", (String) response.getData());
                }
                break;
            //endregion
            case SEND_MESSAGE:
                //region SEND_MESSAGE
                if (!response.isSuccess()) {
                    app.showAlert("Lỗi", (String) response.getData());
                }
                break;
            //endregion
            case NOTIFY_LIST_MESSAGE:
                //region NOTIFY_LIST_MESSAGE
                if (response.isSuccess()) {
                    int conversationId = (int) response.getData();
                    app.ctrChat.notifyListMessage(conversationId);
                }
                break;
            //endregion
            case CREATE_CONVERSATION_GROUP:
                //region CREATE_CONVERSATION_GROUP
                if (response.isSuccess()) {
                    app.showAlert("Tạo nhóm thành công", "");
                } else {
                    app.showAlert("Lỗi", (String) response.getData());
                }
                break;
                //endregion
            case GET_ATTACHMENT:
                //region GET_ATTACHMENT
                if (response.isSuccess()) {
                    FileInfoData fileInfoData = (FileInfoData) response.getData();
                    app.ctrChat.saveAttachment(fileInfoData);
                }
                break;
                //endregion
        }
    }
}

