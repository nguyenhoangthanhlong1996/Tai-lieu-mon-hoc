package client.objectUI;

import client.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import share.data.ConversationData;
import share.util.Base64Utils;

import java.io.IOException;

public class ConversationItem extends ListCell<ConversationData> {

    private int idConversation;
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
    private boolean group;
    FXMLLoader mLLoader;

    @FXML
    private HBox mainNode;
    @FXML
    private ImageView ivAvatar;
    @FXML
    private Label lblName;
    @FXML
    private Label lblShortentContent;
    @FXML
    private Label lblLastMessageTime;
    @FXML
    private ImageView ivGroup;

//    public ConversationItem(int idConversation, String avatar, String username, String name, String shortenContent, String lastMessageTime, int numberUnreadMessage) {
//        FXMLLoader loader = new FXMLLoader(Config.getPathViewConversationItem());
//        loader.setRoot(this);
//        loader.setController(this);
//        try {
//            loader.load();
//            setIdConversation(idConversation);
//            setAvatar(avatar);
//            setUsername(username);
//            setName(name);
//            setShortenContent(shortenContent);
//            setLastMessageTime(lastMessageTime);
//            setNumberUnreadMessage(numberUnreadMessage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void updateItem(ConversationData item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(Config.getPathViewConversationItem());
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            setIdConversation(item.getIdConversation());
            setAvatar(item.getAvatar());
            setUsername(item.getUsername());
            setName(item.getName());
            setShortenContent(item.getShortenContent());
            if (item.getLastMessageTime() != null) {
                setLastMessageTime(item.getLastMessageTime().toString());
            }
            setGroup(item.isGroup());
            setText(null);
            setGraphic(mainNode);
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        if (avatar == "") {
            ivAvatar.setImage(null);
        } else {
            try {
                ivAvatar.setImage(Base64Utils.getImageFromBase64String(avatar));
            } catch (IOException e) {
                ivAvatar.setImage(null);
                e.printStackTrace();
            }
        }
    }

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
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

    public void setGroup(boolean isGroup) {
        this.group = isGroup;
        if (group == true) {
            ivGroup.setVisible(true);
        } else {
            ivGroup.setVisible(false);
        }

    }

    @Override
    public String toString() {
        return "ConversationItem{" +
                "avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", shortenContent='" + shortenContent + '\'' +
                ", lastMessageTime='" + lastMessageTime + '\'' +
                ", group=" + group +
                '}';
    }
}
