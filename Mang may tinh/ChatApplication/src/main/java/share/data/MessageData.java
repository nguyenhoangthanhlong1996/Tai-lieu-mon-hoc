package share.data;

import java.io.Serializable;
import java.sql.Timestamp;


public class MessageData implements Serializable {

    String sender;
    String avatar;
    String content;
    Timestamp time;

    public MessageData(String sender, String avatar, String content, Timestamp time) {
        this.sender = sender;
        this.avatar = avatar;
        this.content = content;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "sender='" + sender + '\'' +
                //"avatar='" + avatar + '\'' +
                "avatar='" + "base64_encode" + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
