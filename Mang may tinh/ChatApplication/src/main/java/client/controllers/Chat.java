package client.controllers;

import client.Config;
import client.objectUI.Conversation;
import client.objectUI.Message;
import client.objectUI.Timeline;
import client.socket.SingletonConnect;
import client.stages.ClientApp;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import share.entity.User;
import share.util.Base64Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Chat {
    //region Biến
    ClientApp app;
    SingletonConnect connect;
    Stage stage;
    double x = 0, y = 0;
    boolean isMaximize = false;
    //endregion

    //region FXML Đối tượng UI
    @FXML
    private ImageView ivMyAvatar;
    @FXML
    private Circle circleMyStatus;
    @FXML
    private Label lblMyName;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private VBox vboxConversation;
    @FXML
    private VBox vboxListConversation;
    @FXML
    private ImageView ivAvatar;
    @FXML
    private Circle circleStatus;
    @FXML
    private Label lblName;
    @FXML
    private Label lblLastVisit;
    @FXML
    private ScrollPane scrollViewChat;
    @FXML
    private VBox vboxViewChat;
    @FXML
    private JFXTextArea txtInputMessage;
    //endregion

    //region Phương thức khởi tạo
    public Chat(ClientApp app, Stage stage) {
        this.app = app;
        this.stage = stage;
        connect = SingletonConnect.getInstance();
    }
    //endregion

    //region @FXML init
    //Hàm khởi chạy khi giao diện được load lên
    @FXML
    void initialize() {
        vboxConversation.setVisible(false);
        vboxViewChat.prefWidthProperty().bind(scrollViewChat.widthProperty());
        vboxListConversation.getChildren().add(new Conversation("","thanhlong", "Nguyễn Hoàng Thanh Long","xin chào bạn","2 ngày", 50));
        vboxListConversation.getChildren().add(new Conversation("","thihoa", "Nguyễn Thị hoa","Alo alo à á ằ","15 phút", 3));
        vboxListConversation.getChildren().add(new Conversation("","group", "abc def","123 5465 465awea2","1 tuần", 2));

        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));

        vboxViewChat.getChildren().add(new Message("", "sdj fmsdfhe4rer43  545 45","20:15","", true));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", true));
        vboxViewChat.getChildren().add(new Message(Config.getAvatarWoman().toString(), "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Message(Config.getAvatarGroup().toString(), "ewefwefwef wefwef","12:12","Đã xem", true));

        scrollViewChat.setVvalue(scrollViewChat.getVmax());
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác với cửa sổ ứng dụng
    @FXML
    void titleBarDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void titleBarPresssed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

    @FXML
    void maximize(MouseEvent event) {
        stage.setMaximized(isMaximize = !isMaximize);
    }

    @FXML
    void close(MouseEvent event) {
        Platform.exit();
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác chính
    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Bạn vừa nhấn enter");
        }
    }
    //endregion

    //region Các hàm xử lý update UI
    public void  showMyInfo(User user) {
        lblMyName.setText(user.getName());
        try {
            ivMyAvatar.setImage(Base64Utils.getImageFromBase64String(user.getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
}

