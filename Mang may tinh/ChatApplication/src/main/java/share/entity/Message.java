package share.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Message implements Serializable {
    private Integer id;
    private String sender;
    private Integer conversationId;
    private String content;
    private byte[] attachment;
    private String attachmentName;
    private String attachmentExtension;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Message() {
    }

    public Message(String sender, Integer conversationId, String content, byte[] attachment, String attachmentName, String attachmentExtension) {
        this.id = id;
        this.sender = sender;
        this.conversationId = conversationId;
        this.content = content;
        this.attachment = attachment;
        this.attachmentName = attachmentName;
        this.attachmentExtension = attachmentExtension;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "conversation_id")
    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    @Basic
    @Column(name = "attachment")
    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Basic
    @Column(name = "attachment_name")
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Basic
    @Column(name = "attachment_extension")
    public String getAttachmentExtension() {
        return attachmentExtension;
    }

    public void setAttachmentExtension(String attachmentExtension) {
        this.attachmentExtension = attachmentExtension;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(conversationId, message.conversationId) &&
                Objects.equals(content, message.content) &&
                Objects.equals(createdAt, message.createdAt) &&
                Objects.equals(updatedAt, message.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, conversationId, content, createdAt, updatedAt);
    }
}
