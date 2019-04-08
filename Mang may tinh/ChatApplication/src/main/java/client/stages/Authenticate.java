package client.stages;

import client.Config;
import client.controllers.SignIn;
import client.controllers.SignUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Authenticate extends Application {

    public Stage stage;
    Scene sceneSignIn, sceneSignUp;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loaderSignIn = new FXMLLoader(Config.getPathViewSignIn());
        FXMLLoader loaderSignUp = new FXMLLoader(Config.getPathViewSignUp());
        Parent rootSignIn = loaderSignIn.load();
        Parent rootSignUp = loaderSignUp.load();
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
    }

    public void goToSignIn() {
        stage.setScene(sceneSignIn);
    }

    public void goToSignUp() {
        stage.setScene(sceneSignUp);
    }
}
