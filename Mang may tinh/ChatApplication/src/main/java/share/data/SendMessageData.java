package share.data;

import java.io.Serializable;

public class SendMessageData implements Serializable {
    int conversationId;
    String content;
    byte[] attachment;
    String attachmentName;
    String attachmentExtension;

    public SendMessageData(int conversationId, String content, byte[] attachment, String attachmentName, String attachmentExtension) {
        this.conversationId = conversationId;
        this.content = content;
        this.attachment = attachment;
        this.attachmentName = attachmentName;
        this.attachmentExtension = attachmentExtension;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentExtension() {
        return attachmentExtension;
    }

    public void setAttachmentExtension(String attachmentExtension) {
        this.attachmentExtension = attachmentExtension;
    }
}
