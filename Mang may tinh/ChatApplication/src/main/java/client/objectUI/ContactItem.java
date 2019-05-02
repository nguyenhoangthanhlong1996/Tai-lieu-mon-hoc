package client.objectUI;

import client.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import share.util.Base64Utils;

import java.io.IOException;

public class ContactItem extends HBox {

    //base64
    private String avatar;
    //Tên tài khoản
    private String username;
    //Giới tính (true -> name, false -> nữ)
    private boolean gender;
    //Tên hiển thị
    private String name;
    //Thời gian truy cập gần nhất
    private String lastVisitTime;
    //Trạng thái trực tuyến
    private boolean online;
    @FXML
    private ImageView ivAvatar;
    @FXML
    private Circle circleStatus;
    @FXML
    private Label lblName;
    @FXML
    private Label lblLastVisitTime;

    public ContactItem(String avatar, String username, String name, boolean gender, String lastVisitTime, boolean online) {
        FXMLLoader loader = new FXMLLoader(Config.getPathViewContactItem());
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            setAvatar(avatar);
            setUsername(username);
            setName(name);
            setLastVisitTime(lastVisitTime);
            setOnline(online);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        if (avatar == "") { //không có hình
            if (gender) { //Nam
                ivAvatar.setImage(new Image(Config.getAvatarMan().toString()));
            } else {
                ivAvatar.setImage(new Image(Config.getAvatarWoman().toString()));
            }
        } else {
            try {
                ivAvatar.setImage(Base64Utils.getImageFromBase64String(avatar));
            } catch (IOException e) {
                if (gender) { //Nam
                    ivAvatar.setImage(new Image(Config.getAvatarMan().toString()));
                } else {
                    ivAvatar.setImage(new Image(Config.getAvatarWoman().toString()));
                }
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        lblName.setText(name);
    }

    public boolean getGender() {return gender;}

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(String lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
        lblLastVisitTime.setText(lastVisitTime);
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
        if (online) {
            circleStatus.setFill(Color.web("#00da8e"));
        } else {
            circleStatus.setFill(Color.web("#e8e8e8"));
        }
    }
}
