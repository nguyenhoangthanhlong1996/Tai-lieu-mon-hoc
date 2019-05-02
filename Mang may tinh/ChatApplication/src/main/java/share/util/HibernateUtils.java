package share.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import share.entity.Conversation;
import share.entity.ConversationUser;
import share.entity.Message;
import share.entity.User;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    // Hibernate 5:
    private static SessionFactory buildSessionFactory() {
        try {
            // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Conversation.class)
                    .addAnnotatedClass(ConversationUser.class)
                    .addAnnotatedClass(Message.class)
                    .getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Giải phóng cache và Connection Pools.
        getSessionFactory().close();
    }
}

