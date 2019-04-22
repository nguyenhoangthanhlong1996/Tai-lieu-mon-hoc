package server.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import server.socket.HandleConnect;
import share.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerController {

    private SimpleDateFormat dateFormat;
    public ServerSocket serverSocket;
    private HandleConnect handleConnect;

    @FXML
    private VBox vboxViewSysLog;
    @FXML
    private ScrollPane scrollPaneViewSysLog;
    @FXML
    private ToggleButton toggleOnOffServer;

    @FXML
    public void initialize() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        scrollPaneViewSysLog.vvalueProperty().bind(vboxViewSysLog.heightProperty());
    }

    @FXML
    void toggleOnOffServerClick(ActionEvent event) {
        if (toggleOnOffServer.isSelected()) {
            toggleOnOffServer.setText("Tắt");
            //Làm mới view syslog
            vboxViewSysLog.getChildren().clear();
            //Khởi chạy server socket
            try {
                serverSocket = new ServerSocket(Config.PORT_LISTEN_SERVER);
                appendSysLog("Server đã được bật", LogLevel.INFO);
                appendSysLog("Đang lắng nghe kết nối từ Client...", LogLevel.INFO);
                handleConnect = new HandleConnect(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            toggleOnOffServer.setText("Bật");
            try {
                //Đóng socket lắng nghe kết nối
                serverSocket.close();
                appendSysLog("Server đã tắt", LogLevel.WARNING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized
    public void appendSysLog(String content, LogLevel logLevel) {
        content = "[" + dateFormat.format(Calendar.getInstance().getTime()) + "]: " + content;
        Label lblLog = new Label();
        lblLog.setFont(new Font(14));
        lblLog.setText(content);
        switch (logLevel) {
            case INFO:
                lblLog.setTextFill(Color.DARKBLUE);
                break;
            case WARNING:
                lblLog.setTextFill(Color.DARKORANGE);
                break;
            case ERROR:
                lblLog.setTextFill(Color.DARKRED);
                break;
        }
        vboxViewSysLog.getChildren().add(lblLog);
    }
}
