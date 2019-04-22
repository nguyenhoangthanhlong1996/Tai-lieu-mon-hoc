package server.socket;

import javafx.application.Platform;
import server.controllers.LogLevel;
import server.controllers.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HandleConnect {

    private ServerController serverController;
    private ServerSocket serverSocket;
    private Thread threadListenConnect;
    private Map<Integer,ThreadConnection> connectionList; //Danh sách chứa tất cả các kết nối tời client;

    public HandleConnect(ServerController serverController) {
        this.serverController = serverController;
        this.serverSocket = serverController.serverSocket;
        connectionList = new HashMap<Integer, ThreadConnection>();
        threadListenConnect = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        int port = socket.getPort();
                        appendSysLog("Có kết nối với client qua port "+socket.getPort(), LogLevel.INFO);
                        ThreadConnection connection = new ThreadConnection(HandleConnect.this, socket);
                        connectionList.put(port, connection);
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
            serverController.appendSysLog(content, logLevel);
        });
    }

    //Đóng tất cả các socket đang kết nối với client
    public void closeAllConnection() {
        Set<Integer> keys = connectionList.keySet();
        for (Integer key: keys) {
            ThreadConnection connection = connectionList.get(key);
            connection.closeConnection();
        }
    }
}
