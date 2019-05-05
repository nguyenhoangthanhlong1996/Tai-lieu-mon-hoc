package client.stages;

import client.Config;
import client.controllers.Chat;
import client.controllers.SignIn;
import client.controllers.SignUp;
import client.socket.SingletonConnect;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ClientApp extends Application {
    //Instance của đối tượng ClientApp
    private static ClientApp instance;

    public static ClientApp getInstance() {
        return instance;
    }

    //Đối tượng giữ kết nối với Server
    private SingletonConnect connect;
    //Stage chính của ứng dụng
    Stage stage;
    //FXML loader
    private FXMLLoader loaderSignIn;
    private FXMLLoader loaderSignUp;
    private FXMLLoader loaderChat;
    //3 màn hình đăng nhập, đăng ký, chat
    Scene sceneSignIn, sceneSignUp, sceneChat;
    //Controller cho màn hình đăng nhập
    private SignIn ctrSignIn;
    //Controller cho màn hình đăng ký
    private SignUp ctrSignUp;
    //Controller cho màn hình chat
    public Chat ctrChat;
    //Hộp thoại thông báo
    Alert alert;

    //Hàm khởi tạo
    @Override
    public void init() throws Exception {
        //Gán instance tĩnh là chính nó
        instance = this;
        //Tạo kết nối tới Server
        connect = SingletonConnect.getInstance();
        //Lấy ra file giao diện
        loaderSignIn = new FXMLLoader(Config.getPathViewSignIn());
        loaderSignUp = new FXMLLoader(Config.getPathViewSignUp());
        loaderChat = new FXMLLoader(Config.getPathViewChat());
    }

    //Tạo giao diện
    public void initUI() throws IOException {
        //Tạo controller
        ctrSignIn = new SignIn(this, stage);
        ctrSignUp = new SignUp(this, stage);
        ctrChat = new Chat(this, stage);
        //Gán controller
        loaderSignIn.setController(ctrSignIn);
        loaderSignUp.setController(ctrSignUp);
        loaderChat.setController(ctrChat);
        //Tạo scence
        sceneSignIn = new Scene(loaderSignIn.load());
        sceneSignUp = new Scene(loaderSignUp.load());
        sceneChat = new Scene(loaderChat.load());
        //Thiết lập cửa sổ window
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Config.getPathIcon().toString()));
        //Khởi tạo Alert Dialog
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        initUI();
        //Chạy kết nối
        connect.start();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }

    //Hàm chọn màn hình sẽ hiển thị
    public void setDisplayScence(ScenceOption scenceOption) {
        switch (scenceOption) {
            case SIGN_IN:
                Platform.runLater(() -> {
                    stage.setScene(sceneSignIn);
                    ctrSignIn.clearForm();
                });
                break;
            case SIGN_UP:
                Platform.runLater(() -> {
                    stage.setScene(sceneSignUp);
                    ctrSignUp.clearForm();
                });
                break;
            case CHAT:
                Platform.runLater(() -> {
                    stage.setScene(sceneChat);
                    ctrChat.clearInfo();
                    ctrChat.showMyInfo();
                    ctrChat.initDataChat();
                });
                break;
        }
    }

    //Hàm xử lý khi đăng xuất thành công
    public void logout() {
        setDisplayScence(ScenceOption.SIGN_IN);
    }

    //Hàm xuất hiện alert dialog
    public void showAlert(String headerText, String contentText) {
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Platform.runLater(() -> alert.showAndWait());
    }

    //Hàm hiển thị thông báo khi mất kết nối tới Server
    public void showAlertDisconnect() {
        alert.setHeaderText("Lỗi");
        alert.setContentText("Mất kết nối tới Server");
        Platform.runLater(() -> {
            alert.showAndWait();
            Platform.exit();
        });
    }
}
