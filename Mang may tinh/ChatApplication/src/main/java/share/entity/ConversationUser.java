package share.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "conversation_user")
public class ConversationUser implements Serializable {
    private Integer conversationId;
    private String username;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ConversationUser() {
    }

    public ConversationUser(Integer conversationId, String username) {
        this.conversationId = conversationId;
        this.username = username;
        this.createdAt = new Timestamp(new Date().getTime());
        ;
        this.updatedAt = new Timestamp(new Date().getTime());
        ;
    }

    @Id
    @Column(name = "conversation_id")
    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        ConversationUser that = (ConversationUser) o;
        return Objects.equals(conversationId, that.conversationId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, username, createdAt, updatedAt);
    }
}
