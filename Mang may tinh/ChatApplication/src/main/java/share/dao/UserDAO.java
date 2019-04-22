package share.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import share.entity.User;
import share.util.HibernateUtils;

import java.util.List;

public class UserDAO {
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    public UserDAO() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public void open() {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
    }

    public void close() {
        session.close();
    }

    //Lấy danh sách tất cả user
    public List<User> getAllUser() {
        transaction.begin();
        return session.createQuery("from User").list();
    }

    //Kiểm tra 1 username đã tồn tại
    public boolean existUsername(String username) {
        open();
        boolean exist = session.get(User.class, username)  != null;
        close();
        return (exist);
    }

    //Tạo user mới
    public boolean createUser(User user) {
       open();
        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            close();
        }

    }
}
