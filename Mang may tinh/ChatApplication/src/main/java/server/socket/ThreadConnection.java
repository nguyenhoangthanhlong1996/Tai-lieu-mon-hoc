package server.socket;

import server.controllers.LogLevel;
import server.controllers.ServerController;
import share.dao.ConversationDAO;
import share.dao.MessageDAO;
import share.dao.UserDAO;
import share.data.ConversationData;
import share.data.SignInData;
import share.data.SignUpData;
import share.entity.Conversation;
import share.entity.Message;
import share.entity.User;
import share.protocol.Request;
import share.protocol.RequestType;
import share.protocol.Response;
import share.protocol.ResponseType;
import share.util.HibernateUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadConnection extends Thread {
    ServerController serverController;
    HandleConnect handleConnect;
    Socket socket;
    //Lưu user đăng nhập hiện tại
    User user;
    //Đối tượng giao tiếp với CSDL
    UserDAO userDAO;
    ConversationDAO conversationDAO;
    MessageDAO messageDAO;
    //    PrintWriter printWriter;
//    BufferedReader bufferedReader;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Thread threadReceiverRequest;

    public ThreadConnection(HandleConnect handleConnect, Socket socket) {
        this.handleConnect = handleConnect;
        this.serverController = handleConnect.serverController;
        userDAO = new UserDAO();
        conversationDAO = new ConversationDAO();
        messageDAO = new MessageDAO();
        user = null;
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //Tạo luồng nhận reqquest từ client
        threadReceiverRequest = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Request request = null;
                        try {
                            if ((request = (Request) objectInputStream.readObject()) != null) {
                                handleRequest(request);
                            } else {
                                disconnect();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        //e.printStackTrace();
                        if (handleConnect.connectionList.get(socket.getPort())!= null) {
                            disconnect();
                        }
                    }
                }
            }
        });
        threadReceiverRequest.start();
    }

    //Xử lý các request từ client
    private void handleRequest(Request request) {
        RequestType requestType = request.getRequestType();
        Response response;
        switch (requestType) {
            case LOGIN:
                //region LOGIN
                //Lấy ra dữ liệu đăng nhập từ request của client
                SignInData signInData = (SignInData) request.getData();
                handleConnect.appendSysLog("Client port " + socket.getPort() + " gửi yêu cầu đăng nhập | " + request.toString(), LogLevel.INFO);
                user = userDAO.checkLogin(signInData.getUsername(), signInData.getPassword());
                if (user != null) {//Thông tin đăng nhập đúng
                    //Kiểm tra xem có đang online không
                    if (user.isOnline()) { //Đang trạng thái đăng nhập
                        response = new Response(ResponseType.LOGIN, false, "Tài khoản này đã được đăng nhập, không thể đăng nhập đồng thời vào lúc này");
                        handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng nhập thất bại | " + response.toString(), LogLevel.INFO);
                        sendResponse(response);
                    } else { //Được phép đăng nhập
                        //Cập nhật trạng thái user
                        if (userDAO.updateStatusOnline(user.getUsername(), true)) {
                            //Gán lại trạng thái online
                            user.setOnline(true);
                            //Cập nhật danh sách thông tin kết nối
                            serverController.updateInfoConnect(socket.getPort(), user.getUsername());
                            response = new Response(ResponseType.LOGIN, true, user);
                            handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng nhập thành công với tên tài khoản " + user.getUsername() + " | " + response.toString(), LogLevel.INFO);
                            sendResponse(response);
                            //thông báo có sự thay đổi trong list user
                            handleConnect.broadcastListUser();
                        } else { //không cập nhật được trạng thái
                            response = new Response(ResponseType.LOGIN, false, "Có lỗi xác thực trạng thái đăng nhập, hãy thử lại sau");
                            handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng nhập thất bại | " + response.toString(), LogLevel.INFO);
                            sendResponse(response);
                        }
                    }
                } else {//Sai thông tin đăng nhập
                    response = new Response(ResponseType.LOGIN, false, "Tên tài khoản hoặc mật khẩu không đúng");
                    handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng nhập thất bại | " + response.toString(), LogLevel.INFO);
                    sendResponse(response);
                }
                break;
            //endregion
            case LOGOUT:
                //region LOGOUT
                //Cập nhật lại trạng thái trực tuyến của user này
                if (userDAO.updateStatusOnline(user.getUsername(), false)) { //cập nhật thành công
                    response = new Response(ResponseType.LOGOUT, true, null);
                    handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng xuất thành công | " + response.toString(), LogLevel.INFO);
                    sendResponse(response);
                    //User lúc này sẽ không tồn tại
                    user = null;
                    //Cập nhật lại danh sách các kết nối
                    serverController.updateInfoConnect(socket.getPort(), null);
                    //Thông báo về tất cả các user đang đăng nhập
                    handleConnect.broadcastListUser();
                } else { //Lỗi đăng xuất
                    response = new Response(ResponseType.LOGOUT, false, "Có lỗi trong quá trình đăng xuất");
                    handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng xuất thất bại | " + response.toString(), LogLevel.INFO);
                    sendResponse(response);
                }
                break;
            //endregion
            case REGISTER:
                //region REGISTER
                //Lấy ra dữ liệu đăng ký từ request của client
                SignUpData signUpData = (SignUpData) request.getData();
                handleConnect.appendSysLog("Client port " + socket.getPort() + " gửi yêu cầu đăng ký | " + request.toString(), LogLevel.INFO);
                //Kiểm tra hợp lệ thông tin đăng ký
                if (!signUpData.isValid()) { //không hợp lệ
                    String errs = "";
                    for (String s : signUpData.getErrors())
                        errs += s + "\n";
                    response = new Response(ResponseType.REGISTER, false, errs);
                    handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng ký thất bại | " + response.toString(), LogLevel.INFO);
                    sendResponse(response);
                } else if (userDAO.existUsername(signUpData.getUsername())) { //Kiểm tra username tồn tại hay chưa
                    response = new Response(ResponseType.REGISTER, false, "Tên tài khoản này đã tồn tại, vui lòng dùng tên khác");
                    handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng ký thất bại | " + response.toString(), LogLevel.INFO);
                    sendResponse(response);
                } else { //Tiến hành tạo tài khoản
                    user = new User(signUpData.getUsername(), signUpData.getPassword(), signUpData.getName(), signUpData.isGender(), signUpData.getAvatar());
                    if (userDAO.createUser(user)) {//Tạo tài khoản thành công
                        response = new Response(ResponseType.REGISTER, true, null);
                        handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng ký thành công | " + response.toString(), LogLevel.INFO);
                        sendResponse(response);
                        //thông báo có sự thay đổi trong list user
                        handleConnect.broadcastListUser();
                    } else { //Tạo tài khoản thất bại
                        response = new Response(ResponseType.REGISTER, false, "Đã có lỗi xảy ra, vui lòng thử lại sau");
                        handleConnect.appendSysLog("Client port " + socket.getPort() + " đăng ký thất bại | " + response.toString(), LogLevel.WARNING);
                        sendResponse(response);
                    }
                }
                break;
            //endregion
            case GET_LIST_USER:
                //region GET_LIST_USER
                //Lấy ra danh sách tất cả user ngoại trừ user gửi request này lên
                if (user != null) {
                    List<User> list = null;
                    try {
                        list = userDAO.getAllUserExceptUsername(user.getUsername());
                        response = new Response(ResponseType.GET_LIST_USER, true, list);
                        sendResponse(response);
                    } catch (Exception e) {
                        response = new Response(ResponseType.GET_LIST_USER, false, "Không thể lấy dữ liệu của bạn");
                        sendResponse(response);
                        e.printStackTrace();
                    }
                } else {
                    response = new Response(ResponseType.GET_LIST_USER, false, "Không thể lấy dữ liệu của bạn");
                    sendResponse(response);
                }
                break;
            //endregion
            case CREATE_CONVERSATION:
                //region CREATE_CONVERSATION

                break;
            //endregion
            case GET_LIST_CONVERSATION:
                //region GET_LIST_CONVERSATION

                break;
                //endregion
            case GET_LIST_MESSAGE:
                //region GET_LIST_MESSAGE

                break;
                //endregion
        }
    }

    //Hàm gửi response về client
    public void sendResponse(Response response) {
        try {
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Hàm xử lý khi bị mất kết nối với client
    public void disconnect() {
        //Hiển thị syslog thông báo
        handleConnect.appendSysLog("Mất kết nối với client ở port " + socket.getPort(), LogLevel.ERROR);
        //Xóa thông tin kết nối này (Danh sách của ServerController
        serverController.removeInfoConnect(socket.getPort());
        //Xóa thông tin kết nối này (Danh sách của HandleConnect)
        handleConnect.connectionList.remove(socket.getPort());
        //Nếu đã đăng nhập thì phải đăng xuất thông tin tài khoản
        if (user != null) {
            //Cập nhật lại trạng thái trực tuyến
            userDAO.updateStatusOnline(user.getUsername(), false);
        }
        //Thông báo user này đã đăng xuất đến tất cả các user
        handleConnect.broadcastListUser();
        //Đóng kết socket và dừng luồng nhận request
        try {
            socket.close();
            threadReceiverRequest.stop();
            stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
