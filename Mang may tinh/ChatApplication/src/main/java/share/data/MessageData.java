package share.data;

import java.io.Serializable;
import java.sql.Timestamp;


public class MessageData implements Serializable {

    int messageId;
    String sender;
    String avatar;
    String content;
    String attachmentName;
    Timestamp time;

    public MessageData(int messageId, String sender, String avatar, String content, String attachmentName, Timestamp time) {
        this.messageId = messageId;
        this.sender = sender;
        this.avatar = avatar;
        this.content = content;
        this.attachmentName = attachmentName;
        this.time = time;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
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
                "messageId=" + messageId +
                ", sender='" + sender + '\'' +
                ", avatar='" + "base64_encode" + '\'' +
                ", content='" + content + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", time=" + time +
                '}';
    }
}
