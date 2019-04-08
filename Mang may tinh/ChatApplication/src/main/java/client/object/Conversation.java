package client.object;

import client.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Conversation extends HBox {
    //Đường dẫn ảnh
    private String avatar;
    //Tên tài khoản
    private String username;
    //Tên hiển thị
    private String name;
    //Nội dung ngắn của tin nhắn cuối
    private String shortenContent;
    //Thời gian nhận tin nhắn cuói
    private String lastMessageTime;
    //Số tin nhắn chưa đọc
    private int numberUnreadMessage;

    @FXML
    private ImageView ivAvatar;

    @FXML
    private Label lblName;

    @FXML
    private Label lblShortentContent;

    @FXML
    private Label lblLastMessageTime;

    @FXML
    private Label lblNumberUnreadMessage;

    public Conversation(String avatar, String username, String name, String shortenContent, String lastMessageTime, int numberUnreadMessage) {
//        this.avatar = avatar;
//        this.username = username;
//        this.name = name;
//        this.shortenContent = shortenContent;
//        this.lastMessageTime = lastMessageTime;
//        this.numberUnreadMessage = numberUnreadMessage;
        FXMLLoader loader = new FXMLLoader(Config.getPathViewConversationItem());
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            setAvatar(avatar);
            setUsername(username);
            setName(name);
            setShortenContent(shortenContent);
            setLastMessageTime(lastMessageTime);
            setNumberUnreadMessage(numberUnreadMessage);
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

    public String getShortenContent() {
        return shortenContent;
    }

    public void setShortenContent(String shortenContent) {
        this.shortenContent = shortenContent;
        lblShortentContent.setText(shortenContent);
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
        lblLastMessageTime.setText(lastMessageTime);
    }

    public int getNumberUnreadMessage() {
        return numberUnreadMessage;
    }

    public void setNumberUnreadMessage(int numberUnreadMessage) {
        this.numberUnreadMessage = numberUnreadMessage;
        if (numberUnreadMessage == 0) {
            lblNumberUnreadMessage.setVisible(false);
        } else {
            lblNumberUnreadMessage.setVisible(true);
            lblNumberUnreadMessage.setText(numberUnreadMessage+"");
        }

    }
}
