package client.object;

import client.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Message extends HBox {

    String avatar;
    String content;
    String time;
    String status;
    boolean isMyMessage;

    @FXML
    private ImageView ivAvatar;

    @FXML
    private Label lblContent;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblStatus;

    public Message(String avatar, String content, String time, String status, boolean isMyMessage) {
        FXMLLoader loader = new FXMLLoader(Config.getPathViewMessageItem());
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            setAvatar(avatar);
            setContent(content);
            setTime(time);
            setStatus(status);
            setMyMessage(isMyMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        if (avatar == "") {
            ivAvatar.setImage(new Image(Config.getAvatarMan().toString()));
        } else {
            ivAvatar.setImage(new Image(avatar));
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String contet) {
        this.content = contet;
        lblContent.setText(contet);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        lblTime.setText(time);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        lblStatus.setText(status);
    }

    public boolean isMyMessage() {
        return isMyMessage;
    }

    public void setMyMessage(boolean myMessage) {
        isMyMessage = myMessage;
        if (myMessage == true) {
            this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            this.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }
}
