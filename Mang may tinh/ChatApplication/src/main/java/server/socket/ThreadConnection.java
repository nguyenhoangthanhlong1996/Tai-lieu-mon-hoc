package server.socket;

import server.controllers.LogLevel;
import share.protocol.Request;

import java.io.*;
import java.net.Socket;

public class ThreadConnection extends Thread {
    HandleConnect handleConnect;
    Socket socket;
//    PrintWriter printWriter;
//    BufferedReader bufferedReader;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Thread threadReceiverRequest;

    public ThreadConnection(HandleConnect handleConnect, Socket socket) {
        this.handleConnect = handleConnect;
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
                                handleConnect.appendSysLog("client gửi: "+request.toString(), LogLevel.INFO);
                            } else {
                                handleConnect.appendSysLog("Mất kết nối với client ở port "+socket.getPort(), LogLevel.ERROR);
                                closeConnection();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        //e.printStackTrace();
                        handleConnect.appendSysLog("Mất kết nối với client ở port "+socket.getPort(), LogLevel.ERROR);
                        closeConnection();
                    }
                }
            }
        });
        threadReceiverRequest.start();
    }

    public void closeConnection() {
        try {
            socket.close();
            threadReceiverRequest.stop();
            stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
