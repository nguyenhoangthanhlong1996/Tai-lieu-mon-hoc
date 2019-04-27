
import client.objectUI.ConversationItem;
import share.dao.ConversationDAO;
import share.dao.MessageDAO;
import share.dao.UserDAO;
import share.data.ConversationData;
import share.entity.Conversation;
import share.entity.Message;
import share.entity.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainTest {
    public static void main(String[] args) throws ParseException {

        ConversationDAO conversationDAO = new ConversationDAO();
        System.out.println(conversationDAO.existConversationPrivate("long","hue"));
    }
}
