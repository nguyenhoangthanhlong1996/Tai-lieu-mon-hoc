package server.socket;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import server.controllers.LogLevel;
import server.controllers.ServerController;
import server.objectUI.InfoConnect;
import share.protocol.Response;
import share.protocol.ResponseType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class HandleConnect {

    public ServerController serverController;
    private ServerSocket serverSocket;
    private Thread threadListenConnect;
    public Map<Integer, ThreadConnection> connectionList; //Danh sách chứa tất cả các kết nối tời client;

    public HandleConnect() {
        this.serverController = ServerController.getInstance();
        this.serverSocket = serverController.serverSocket;
        connectionList = new HashMap<>();
        threadListenConnect = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        int port = socket.getPort();
                        appendSysLog("Có kết nối với client qua port " + socket.getPort(), LogLevel.INFO);
                        ThreadConnection connection = new ThreadConnection(HandleConnect.this, socket);
                        connectionList.put(port, connection);
                        serverController.listConnect.add(new InfoConnect(port, null));
                        connection.start();
                    } catch (IOException e) {
                        closeAllConnection();
                        appendSysLog(e.getMessage(), LogLevel.ERROR);
                        threadListenConnect.stop();
                    }
                }
            }
        });
        threadListenConnect.setName("thread listen connect");
        threadListenConnect.start();
    }

    public void appendSysLog(String content, LogLevel logLevel) {
        Platform.runLater(() -> {
            synchronized (serverController) {
                serverController.appendSysLog(content, logLevel);
            }
        });
    }

    //Đóng tất cả các socket đang kết nối với client
    public void closeAllConnection() {
        Set<Integer> keys = new HashSet<>(connectionList.keySet());
        for (Integer key : keys) {
            ThreadConnection connection = connectionList.get(key);
            connection.disconnect();
            System.out.println(keys.size());
        }

    }

    //Khi hàm này được gọi sẽ thông báo cho tất cả user đang đăng nhập biết có sự thay đổi list user
    public void broadcastListUser() {
        appendSysLog("Broadcast danh sách người dùng", LogLevel.INFO);
        Set<Integer> keys = connectionList.keySet();
        Response response = new Response(ResponseType.BROADCAST_LIST_USER, true, null);
        for (Integer key : keys) {
            ThreadConnection connection = connectionList.get(key);
            if (connection.user != null) {//Có đăng nhập
                connection.sendResponse(response);
            }
        }
    }

    //Khi hàm này được gọi sẽ thông báo cho user này biết có sự thay đổi danh sách các cuộc hội thoại của user này
    public void notifyListConversation(String username) {
        ObservableList<InfoConnect> list = serverController.listConnect;
        ObservableList<InfoConnect> listFilter = list.filtered(ic -> ic.getUsername().equals(username));
        InfoConnect infoConnect = listFilter.size() > 0 ? listFilter.get(0) : null;
        if (infoConnect != null) {
            int port = infoConnect.getPort();
            if (connectionList.get(port) != null) {
                connectionList.get(port).sendResponse(new Response(ResponseType.NOTIFY_LIST_CONVERSATION, true, null));
            }
        }
    }
}
