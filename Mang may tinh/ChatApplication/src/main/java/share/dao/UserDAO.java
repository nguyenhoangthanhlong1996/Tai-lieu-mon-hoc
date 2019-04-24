package share.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import share.entity.User;
import share.util.HibernateUtils;

import javax.persistence.NoResultException;
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
        open();
        List<User> list = session.createQuery("from User").list();
        close();
        return list;
    }

    //Kiểm tra 1 username đã tồn tại
    public boolean existUsername(String username) {
        open();
        boolean exist = session.get(User.class, username) != null;
        close();
        return (exist);
    }

    //Kiểm tra đăng nhập, nếu thành công thì sẽ trả về một User ngược lại null
    public User checkLogin(String username, String password) {
        open();
        User user = null;
        try {
            user =
                    (User) session.createQuery("from User u where u.username = :username and u.password = :password")
                            .setParameter("username", username)
                            .setParameter("password", password)
                            .getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        close();
        return user;
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
        } finally {
            close();
        }

    }

    //Cập nhật trạng thái online của 1 User
    public boolean updateStatusOnline(String username, boolean online) {
        open();
        try {
            session
                    .createQuery("update User set online = :online where username = :username")
                    .setParameter("online", online)
                    .setParameter("username", username)
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            close();
        }
    }
}
