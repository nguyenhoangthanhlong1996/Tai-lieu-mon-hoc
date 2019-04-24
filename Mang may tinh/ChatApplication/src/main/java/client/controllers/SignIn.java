package client.controllers;

import client.socket.SingletonConnect;
import client.stages.ClientApp;
import client.stages.ScenceOption;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import share.data.SignInData;
import share.protocol.Request;
import share.protocol.RequestType;

public class SignIn {
    //region Biến
    ClientApp app;
    SingletonConnect connect;
    Stage stage;
    double x = 0, y = 0;
    SignInData signInData;
    //endregion

    //region FXML Đối tượng UI
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    //endregion

    //region Phương thức khởi tạo
    public SignIn(ClientApp app, Stage stage) {
        this.app = app;
        this.stage = stage;
        connect = SingletonConnect.getInstance();
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
    void close(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            btnSignInClick(null);
        }
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác chính
    @FXML
    void btnSignInClick(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        signInData = new SignInData();
        signInData.setUsername(username);
        signInData.setPassword(password);

        if (!signInData.isValid()) {
            String errs = "";
            for (String s : signInData.getErrors())
                errs += s + "\n";
            app.showAlert("Dữ liệu nhập vào không hợp lệ", errs);
        } else {
            //Gửi resquest login lên Server
            connect.sendRequest(new Request(RequestType.LOGIN, signInData));
        }
    }

    @FXML
    void linkToSignUp(ActionEvent event) {
        app.setDisplayScence(ScenceOption.SIGN_UP);
    }
    //endregion

    //region Các hàm xử lý
    //Hàm làm mới form
    public void clearForm() {
        txtUsername.setText("");
        txtPassword.setText("");
    }
    //endregion
}
