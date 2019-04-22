package server.socket;

import server.controllers.LogLevel;

import java.io.*;
import java.net.Socket;

public class ThreadConnection extends Thread {
    HandleConnect handleConnect;
    Socket socket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    Thread threadReceiverRequest;

    public ThreadConnection(HandleConnect handleConnect, Socket socket) {
        this.handleConnect = handleConnect;
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //Tạo luồng nhận reqques từ client
        threadReceiverRequest = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String result = "";
                        if ((result = bufferedReader.readLine()) != null) {
                            handleConnect.appendSysLog("Client gửi: "+result, LogLevel.INFO);
                            printWriter.println(result.toUpperCase());
                            printWriter.flush();
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
