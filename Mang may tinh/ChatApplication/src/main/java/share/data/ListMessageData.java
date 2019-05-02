package share.data;

import java.io.Serializable;
import java.util.List;

public class ListMessageData implements Serializable {
    int conversationId;
    List<MessageData> listMessageData;

    public ListMessageData(int conversationId, List<MessageData> listMessageData) {
        this.conversationId = conversationId;
        this.listMessageData = listMessageData;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public List<MessageData> getListMessageData() {
        return listMessageData;
    }

    public void setListMessageData(List<MessageData> listMessageData) {
        this.listMessageData = listMessageData;
    }
}
