package share.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import share.entity.Message;
import share.entity.User;
import share.util.HibernateUtils;

import javax.persistence.NoResultException;
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
    public List<Message> getAllMessageByConversationId(int conversationId) {
       return null;
    }

   //Lấy tin nhắn cuối cùng của 1 cuộc hội thoại
    public Message getLastMessageByConversationId(int conversationId) {
        return null;
    }
}
