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
import java.sql.Timestamp;
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
                    } else if (list.get(0).getUsername().equals(username2) && list.get(1).getUsername().equals(username1)) {
                        exist = true;
                        break;
                    } else
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
    public boolean createConversationPrivate(String user1, String user2) {
        open();
        try {
            //Tạo cuộc hội thoại
            Conversation conversation = new Conversation(user1, null, false);
            session.save(conversation);
            //Thêm 2 user trên vào cuộc hội thoại
            int id = conversation.getId();
            ConversationUser cu1 = new ConversationUser(id, user1);
            ConversationUser cu2 = new ConversationUser(id, user2);
            session.save(cu1);
            session.save(cu2);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            close();
        }
    }

    //Thêm mới một conversation nhóm
    public boolean createConversationGroup(String creator, String nameGroup, String avatar, List<String> users) {
        open();
        try {
            //Tạo cuộc hội thoại nhóm
            Conversation conversation = new Conversation(creator, avatar, true);
            conversation.setNameGroup(nameGroup);
            session.save(conversation);
            //Thêm từng user vào cuộc hội thoại này
            int conversationId = conversation.getId();
            //Thêm người tạo nhóm này
            session.save(new ConversationUser(conversationId, creator));
            //Thêm tất cả mọi người trong list
            for (String u : users) {
                ConversationUser cu = new ConversationUser(conversationId, u);
                session.save(cu);
            }
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
        List<ConversationData> list = new ArrayList<>();
        //Lấy tất cả cuộc hội thoại riêng tư
        open();
        List<Object[]> listConversationPrivate = session
                .createSQLQuery("call sp_GetAllConversationPrivateByUsername(:username)")
                .setParameter("username", username)
                .list();
        List<Object[]> listConversationGroup = session
                .createSQLQuery("call sp_GetAllConversationGroupByUsername(:username)")
                .setParameter("username", username)
                .list();
        close();
        //Thêm vào tất cả cuộc hội thoại riêng tư
        for (Object[] o : listConversationPrivate) {
            ConversationData data = new ConversationData(
                    (Integer) o[0],
                    false,
                    (String) o[5],
                    (String) o[1],
                    (String) o[4],
                    (String) o[6],
                    (Timestamp) o[7],
                    0);
            list.add(data);
        }
        //Thêm vào tất cả cuộc hội thoại nhóm
        for (Object[] o : listConversationGroup) {
            ConversationData data = new ConversationData(
                    (Integer) o[0],
                    true,
                    (String) o[3],
                    null,
                    (String) o[2],
                    (String) o[7],
                    (Timestamp) o[8],
                    0);
            list.add(data);
        }
        return list;
    }

    //Lấy danh sách tất cả các username có trong conversation
    public List<String> getAllUsernameByConversation(int conversationId) {
        open();
        List<String> list = session
                .createQuery("select username from ConversationUser where conversationId = :conversationId")
                .setParameter("conversationId", conversationId)
                .list();
        close();
        return list;
    }


}
