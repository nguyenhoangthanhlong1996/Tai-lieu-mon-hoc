package client.stages;

import client.Config;
import client.controllers.SignIn;
import client.controllers.SignUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Authenticate extends Application {

    public Stage stage;
    Scene sceneSignIn, sceneSignUp;
    Alert alert;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        FXMLLoader loaderSignIn = new FXMLLoader(Config.getPathViewSignIn());
        FXMLLoader loaderSignUp = new FXMLLoader(Config.getPathViewSignUp());
        Parent rootSignIn = null;
        Parent rootSignUp = null;
        try {
            rootSignIn = loaderSignIn.load();
            rootSignUp = loaderSignUp.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SignIn ctrSignIn = loaderSignIn.getController();
        SignUp ctrSignUp = loaderSignUp.getController();
        ctrSignIn.setAuthenticate(this);
        ctrSignUp.setAuthenticate(this);

        sceneSignIn = new Scene(rootSignIn);
        sceneSignUp = new Scene(rootSignUp);

        goToSignIn();

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Config.getPathIcon().toString()));
        stage.show();

        //Khởi tạo Alert Dialog
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    }

    public void goToSignIn() {
        stage.setScene(sceneSignIn);
    }

    public void goToSignUp() {
        stage.setScene(sceneSignUp);
    }

    //Hàm xuất hiện alert dialog
    public void showAlert(String headerText, String contentText) {
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}
