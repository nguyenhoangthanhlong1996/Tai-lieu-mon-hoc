package share.protocol;

public enum ResponseType {
    //Loại phản hồi cho yêu cầu của client
    LOGIN,
    LOGOUT,
    REGISTER,
    GET_LIST_USER,
    CREATE_CONVERSATION_PRIVATE,
    CREATE_CONVERSATION_GROUP,
    GET_LIST_CONVERSATION,
    GET_LIST_MESSAGE,
    SEND_MESSAGE,
    //Loại phản hồi nhằm thông báo sự thay đổi cho client
    NOTIFY_LIST_USER,
    NOTIFY_LIST_CONVERSATION,
    NOTIFY_LIST_MESSAGE;
}
