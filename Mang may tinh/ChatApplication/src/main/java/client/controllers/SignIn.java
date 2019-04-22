package client.controllers;

import client.stages.Authenticate;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignIn {

    Authenticate authenticate;
    Stage stage;
    double x = 0, y = 0;

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtPassword;


    public void setAuthenticate(Authenticate authenticate) {
        this.authenticate = authenticate;
        this.stage = authenticate.stage;
    }


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
    void close(MouseEvent event) {
        System.exit(0);
    }


    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

    @FXML
    void btnSignInClick(ActionEvent event) {

    }

    @FXML
    void linkToSignUp(ActionEvent event) {
        authenticate.goToSignUp();
    }
}
