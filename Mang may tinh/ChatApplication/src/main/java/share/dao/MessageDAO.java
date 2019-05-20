package share.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import share.data.FileInfoData;
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
                .createQuery("select m.id,m.sender, m.content, m.attachmentName, m.attachmentExtension, m.createdAt,u.avatar from Message m, User u where m.conversationId = :conversationId and m.sender = u.username order by id asc")
                .setParameter("conversationId", conversationId)
                .list();
        close();
        for (Object[] o : listMessage) {
            MessageData data = new MessageData(
                    (int) o[0],
                    (String) o[1],
                    (String) o[6],
                    (String) o[2],
                    o[3] != null ? (String) o[3] + "." + (String) o[4] : null,
                    (Timestamp) o[5]
            );
            list.add(data);
        }
        return list;
    }

    //Thêm một tin nhắn vào cuộc hội thoại
    public boolean createMessage(int conversationId, String sender, String content, byte[] attachment, String attachmentName, String attachmentExtension) {
        open();
        Message message = new Message(sender, conversationId, content, attachment, attachmentName, attachmentExtension);
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

    public Message getMessageById(int id) {
        Message message = null;
        open();
        message = (Message) session
                .createQuery("from Message m where m.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        close();
        return message;
    }

    //Lấy ra fle đính kèm của 1 tin nhắn
    public FileInfoData getAttachmentById(int id) {
        FileInfoData fileInfoData = new FileInfoData();
        open();
        Message message = (Message) session
                .createQuery("from Message m where m.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        close();
        if (message != null) {
            fileInfoData.setFileName(message.getAttachmentName());
            fileInfoData.setFileExtension(message.getAttachmentExtension());
            fileInfoData.setDataBytes(message.getAttachment());
        }
        return fileInfoData;
    }
}
