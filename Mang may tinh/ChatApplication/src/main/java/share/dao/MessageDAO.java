package share.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import share.data.MessageData;
import share.entity.Message;
import share.entity.User;
import share.util.HibernateUtils;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    public MessageDAO() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public void open() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
    }

    public void close() {
        session.close();
    }

    //Lấy tất cả các tin nhắn của 1 cuộc hội thoại
    public List<MessageData> getAllMessageByConversationId(int conversationId) {
        List<MessageData> list = new ArrayList<>();
        open();
        List<Object[]> listMessage = session
                .createQuery("select m.conversationId,m.sender, m.content,m.createdAt,u.avatar from Message m, User u where m.conversationId = :conversationId and m.sender = u.username order by id asc")
                .setParameter("conversationId", conversationId)
                .list();
        close();
        for (Object[] o : listMessage) {
            MessageData data = new MessageData(
                    (String) o[1],
                    (String) o[4],
                    (String) o[2],
                    (Timestamp) o[3]
            );
            list.add(data);
        }
        return list;
    }

    //Thêm một tin nhắn vào cuộc hội thoại
    public boolean createMessage(int conversationId, String sender, String content) {
        open();
        Message message = new Message(sender,conversationId,content);
        try {
            session.save(message);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            close();
        }
    }

    //Lấy tin nhắn cuối cùng của 1 cuộc hội thoại
    public Message getLastMessageByConversationId(int conversationId) {
        return null;
    }
}
