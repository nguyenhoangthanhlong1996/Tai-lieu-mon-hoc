package share.data;

import java.io.Serializable;

public class SendMessageData implements Serializable {
    int conversationId;
    String content;

    public SendMessageData(int conversationId, String content) {
        this.conversationId = conversationId;
        this.content = content;
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
}
