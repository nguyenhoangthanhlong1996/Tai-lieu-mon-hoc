package client.controllers;

import client.stages.Authenticate;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import share.data.SignUpData;


import java.io.File;

public class SignUp {

    Authenticate authenticate;
    Stage stage;
    double x = 0, y = 0;
    File fileAvatar;
    SignUpData signUpData;

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

    public void setAuthenticate(Authenticate authenticate) {
        this.authenticate = authenticate;
        this.stage = authenticate.stage;
    }

    //region Xử lý khi controller được khởi chạy
    @FXML
    void initialize() {
        //Gán group cho giới tính
        ToggleGroup toggleGroup = new ToggleGroup();
        rdbtnMale.setToggleGroup(toggleGroup);
        rdbtnFemale.setToggleGroup(toggleGroup);
        //Lụa chọn mặc định
        rdbtnMale.setSelected(true);
        //Tạo đối tượng lưu dữ liệu và kiểm tra tính hợp lệ
        signUpData = new SignUpData();
    }
    //endregion

    //region Xử lý các sự kiện tương tác trên giao diện
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

    //Đóng ứng dụng
    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    //Minimize ứng dụng
    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

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
                authenticate.showAlert("Kích thước ảnh lớn hơn 500 KB", "Vui lòng chọn ảnh khác, ảnh đại diện của bạn sẽ không được lưu khi kích thước lớn hơn 500 KB");
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
        String avatar = (fileAvatar != null) ? fileAvatar.getAbsolutePath() : "";
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
            authenticate.showAlert("Dữ liệu nhập vào không hợp lệ", errs);
        } else {
            authenticate.showAlert("Đăng ký thành công","thành công" );
        }
    }

    //Chuyển về giao diện đăng nhập
    @FXML
    void linkToSignIn(ActionEvent event) {
        authenticate.goToSignIn();
    }
    //endregion
}
