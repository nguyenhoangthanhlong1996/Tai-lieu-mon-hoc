package client.controllers;

import client.socket.SingletonConnect;
import client.stages.ClientApp;
import client.stages.ScenceOption;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import share.data.SignUpData;
import share.protocol.Request;
import share.protocol.RequestType;
import share.util.Base64Utils;

import java.io.File;

public class SignUp {
    //region Biến
    ClientApp app;
    SingletonConnect connect;
    Stage stage;
    double x = 0, y = 0;
    File fileAvatar;
    SignUpData signUpData;
    //endregion

    //region FXML Đối tượng UI
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtFullName;
    @FXML
    private RadioButton rdbtnMale;
    @FXML
    private RadioButton rdbtnFemale;
    @FXML
    private ImageView ivAvatar;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtRetypePassword;
    //endregion

    //region Phương thức khởi tạo
    public SignUp(ClientApp app, Stage stage) {
        this.app = app;
        this.stage = stage;
        connect = SingletonConnect.getInstance();
    }
    //endregion

    //region @FXML init
    @FXML
    void initialize() {
        //Gán group cho giới tính
        ToggleGroup toggleGroup = new ToggleGroup();
        rdbtnMale.setToggleGroup(toggleGroup);
        rdbtnFemale.setToggleGroup(toggleGroup);
        //Lụa chọn mặc định
        rdbtnMale.setSelected(true);
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác với cửa sổ ứng dụng
    //Kéo thanh tiêu đề
    @FXML
    void titleBarDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    //Nhấn chuột vào thanh tiêu đề
    @FXML
    void titleBarPresssed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    //Minimize ứng dụng
    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

    //Đóng ứng dụng
    @FXML
    void close(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        //Nhấn phím enter
        if (event.getCode() == KeyCode.ENTER) {
            btnSignUpClick(null);
        }
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác chính
    //Chọn file ảnh đại diện
    @FXML
    void chooseAvatar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //Thiết lập bộ lọc file
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Chọn file ảnh (<=500 KB)", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Chọn ảnh đại diện");
        //Hiện cửa sổ chọn file
        fileAvatar = fileChooser.showOpenDialog(stage);
        if (fileAvatar != null) {
            if (fileAvatar.length() > 512000) {
                app.showAlert("Kích thước ảnh lớn hơn 500 KB", "Vui lòng chọn ảnh khác, ảnh đại diện của bạn sẽ không được lưu khi kích thước lớn hơn 500 KB");
                fileAvatar = null;
                ivAvatar.setImage(null);
            } else {
                ivAvatar.setImage(new Image("file:" + fileAvatar.getAbsolutePath()));
            }
        } else {
            ivAvatar.setImage(null);
        }
    }

    //Đăng ký
    @FXML
    void btnSignUpClick(ActionEvent event) {
        String username = txtUsername.getText();
        String name = txtFullName.getText();
        boolean gender = rdbtnMale.isSelected() ? true : false;
        String password = txtPassword.getText();
        String retypePassword = txtRetypePassword.getText();
        String avatar = (fileAvatar != null) ? Base64Utils.encoder(fileAvatar.getAbsolutePath()) : "";
        signUpData = new SignUpData();
        signUpData.setUsername(username);
        signUpData.setName(name);
        signUpData.setGender(gender);
        signUpData.setPassword(password);
        signUpData.setPassword(password);
        signUpData.setRetypePassword(retypePassword);
        signUpData.setAvatar(avatar);
        if (!signUpData.isValid()) {
            String errs = "";
            for (String s : signUpData.getErrors())
                errs += s + "\n";
            app.showAlert("Dữ liệu nhập vào không hợp lệ", errs);
        } else {
            //Gửi resquest register lên Server
            connect.sendRequest(new Request(RequestType.REGISTER, signUpData));
        }
    }

    //Chuyển về giao diện đăng nhập
    @FXML
    void linkToSignIn(ActionEvent event) {
        app.setDisplayScence(ScenceOption.SIGN_IN);
    }
    //endregion

    //region Các hàm xử lý
    //Hàm làm mới form
    public void clearForm() {
        txtUsername.setText("");
        txtFullName.setText("");
        txtPassword.setText("");
        txtRetypePassword.setText("");
        rdbtnMale.setSelected(true);
        fileAvatar = null;
        ivAvatar.setImage(null);
    }
    //endregion
}
