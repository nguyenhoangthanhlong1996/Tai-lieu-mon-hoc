//package client.stages;
//
//import client.Config;
//import client.controllers.SignIn;
//import client.controllers.SignUp;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.image.Image;
//import javafx.scene.layout.Region;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//import java.io.IOException;
//
//public class Authenticate extends Application {
//
//    private static Authenticate instance;
//    public Stage stage;
//    private SignIn ctrSignIn;
//    private SignUp ctrSignUp;
//    Scene sceneSignIn, sceneSignUp;
//    Alert alert;
//
//    public static Authenticate getInstance() {
//        return instance;
//    }
//
//    public Authenticate() {
//        instance = this;
//        //Khởi tạo Alert Dialog
//        alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Thông báo");
//        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        instance = this;
//        this.stage = stage;
//        FXMLLoader loaderSignIn = new FXMLLoader(Config.getPathViewSignIn());
//        FXMLLoader loaderSignUp = new FXMLLoader(Config.getPathViewSignUp());
//        Parent rootSignIn = null;
//        Parent rootSignUp = null;
//        try {
//            rootSignIn = loaderSignIn.load();
//            rootSignUp = loaderSignUp.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ctrSignIn = loaderSignIn.getController();
//        ctrSignUp = loaderSignUp.getController();
//        ctrSignIn.setAuthenticate(this);
//        ctrSignUp.setAuthenticate(this);
//
//        sceneSignIn = new Scene(rootSignIn);
//        sceneSignUp = new Scene(rootSignUp);
//
//        goToSignIn();
//
//        stage.setResizable(false);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.getIcons().add(new Image(Config.getPathIcon().toString()));
//        stage.show();
//    }
//
//    @Override
//    public void stop() throws Exception {
//        super.stop();
//    }
//
//    public void goToSignIn() {
//        stage.setScene(sceneSignIn);
//    }
//
//    public void goToSignUp() {
//        stage.setScene(sceneSignUp);
//    }
//
//    //Hàm xuất hiện alert dialog
//    public void showAlert(String headerText, String contentText) {
//        alert.setHeaderText(headerText);
//        alert.setContentText(contentText);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                alert.showAndWait();
//            }
//        });
//    }
//
//    //Hàm làm mới form đăng nhập
//    public void clearFormSignIn() {
//
//    }
//
//    //Hàm làm mới form đăng nhập
//    public void clearFormSignUp() {
//        if (ctrSignUp != null) {
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    ctrSignUp.clearForm();
//                }
//            });
//        }
//    }
//
//    //Hàm xử lý khi mất kết nối tới Server
//    public void disconnect() {
//        alert.setHeaderText("Lỗi");
//        alert.setContentText("Mất kết nối tới Server");
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                alert.showAndWait();
//                Platform.exit();
//            }
//        });
//    }
//
//    //Đóng App
//    public void closeApp() {
//        Platform.exit();
//    }
//
//}
