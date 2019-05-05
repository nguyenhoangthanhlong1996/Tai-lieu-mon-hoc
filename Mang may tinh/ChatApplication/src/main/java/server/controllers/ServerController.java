package server.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import server.controllers.objectUI.InfoConnect;
import server.socket.HandleConnect;
import share.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerController {

    private static ServerController instance;

    public static ServerController getInstance() {
        return instance;
    }

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
    private TableView<InfoConnect> tableConnect;
    @FXML
    private TableColumn<InfoConnect, Integer> colPortID;
    @FXML
    private TableColumn<InfoConnect, String> colByUser;

    //Danh sách chứa tất cả các kết nối hiện có
    public ObservableList<InfoConnect> listConnect;

    @FXML
    public void initialize() {
        instance = this;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        scrollPaneViewSysLog.vvalueProperty().bind(vboxViewSysLog.heightProperty());
        listConnect = FXCollections.observableArrayList();
        colPortID.setCellValueFactory(new PropertyValueFactory<>("port"));
        colByUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableConnect.setItems(listConnect);
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
                handleConnect = new HandleConnect();
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
        HBox hBox = new HBox();
        Label lblTime = new Label();
        Label lblLog = new Label();
        lblTime.setText("[" + dateFormat.format(Calendar.getInstance().getTime()) + "]: ");
        lblTime.setFont(new Font(14));
        lblLog.setWrapText(false);
        lblTime.setTextFill(Color.web("#00f"));
        lblTime.setMinWidth(150);
        lblLog.setText(content);
        lblLog.setFont(new Font(14));
        lblLog.setWrapText(true);
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
        hBox.getChildren().addAll(lblTime,lblLog);
        vboxViewSysLog.getChildren().add(hBox);
    }

    public void addInfoConnect(int port, String username) {
        listConnect.add(new InfoConnect(port, username));
    }

    public void removeInfoConnect(int port) {
        listConnect.removeIf(ic -> ic.getPort() == port);
    }

    public void updateInfoConnect(int port, String username) {
        listConnect.filtered(ic -> ic.getPort() == port).get(0).setUsername(username);
        tableConnect.refresh();
    }

}
