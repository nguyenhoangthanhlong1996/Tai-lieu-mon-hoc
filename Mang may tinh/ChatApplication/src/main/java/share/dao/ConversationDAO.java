package share.dao;

import client.objectUI.ConversationItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import share.data.ConversationData;
import share.entity.Conversation;
import share.entity.ConversationUser;
import share.entity.Message;
import share.entity.User;
import share.util.HibernateUtils;

import javax.persistence.NoResultException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConversationDAO {
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    public ConversationDAO() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public void open() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
    }

    public void close() {
        session.close();
    }

    public Conversation getConversationById(int idConversation) {
        open();
        Conversation conversation = session.get(Conversation.class, idConversation);
        close();
        return conversation;
    }

    //Hảm kiểm tra xem một cuộc hội thoại cá nhân có tồn tại hay chưa, với tham số truyền vào là 2 username
    public boolean existConversationPrivate(String username1, String username2) {
        open();
        boolean exist = false;
        try {
            //Lấy ra danh sách các cuộc hội thoại cá nhân
            List<Conversation> listConversation = session.createQuery("from Conversation where group = false").list();
            for (Conversation conversation : listConversation) {
                List<ConversationUser> list = session
                        .createQuery("from ConversationUser where conversationId = :id")
                        .setParameter("id", conversation.getId())
                        .list();
                if (list.size() == 2) {
                    if (list.get(0).getUsername().equals(username1) && list.get(1).getUsername().equals(username2)) {
                        exist = true;
                        break;
                    }else if (list.get(0).getUsername().equals(username2) && list.get(1).getUsername().equals(username1)) {
                        exist = true;
                        break;
                    }
                    else
                        exist = false;
                } else
                    exist = false;
            }
        } catch (NoResultException e) {
            exist = false;
        }
        close();
        return exist;
    }

    //Thêm mới một conversation riêng tư
    public boolean createConversationPrivate(Conversation conversation, String user1, String user2) {
        open();
        try {
            session.save(conversation);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            close();
        }
    }

    //Lấy danh sách tất cả cuộc hội thoại của 1 user
    public List<ConversationData> getAllConversation(String username) {
        return null;
    }


}
