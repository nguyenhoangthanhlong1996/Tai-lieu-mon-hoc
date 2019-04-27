package share.data;

import share.entity.Conversation;

import java.io.Serializable;
import java.sql.Timestamp;

public class ConversationData implements Serializable {
    //id cuộc hội thoại
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
    private Timestamp lastMessageTime;
    //Số tin nhắn chưa đọc
    private int numberUnreadMessage;

    @Override
    public String toString() {
        return "ConversationData{" +
                //"avatar='" + avatar + '\'' +
                "avatar='" + "base64_encode" + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", shortenContent='" + shortenContent + '\'' +
                ", lastMessageTime=" + lastMessageTime +
                ", numberUnreadMessage=" + numberUnreadMessage +
                '}';
    }

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
    }

    public String getShortenContent() {
        return shortenContent;
    }

    public void setShortenContent(String shortenContent) {
        this.shortenContent = shortenContent;
    }

    public Timestamp getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Timestamp lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public int getNumberUnreadMessage() {
        return numberUnreadMessage;
    }

    public void setNumberUnreadMessage(int numberUnreadMessage) {
        this.numberUnreadMessage = numberUnreadMessage;
    }

    public ConversationData(int idConversation, String avatar, String username, String name, String shortenContent, Timestamp lastMessageTime, int numberUnreadMessage) {
        this.idConversation = idConversation;
        this.avatar = avatar;
        this.username = username;
        this.name = name;
        this.shortenContent = shortenContent;
        this.lastMessageTime = lastMessageTime;
        this.numberUnreadMessage = numberUnreadMessage;
    }
}
