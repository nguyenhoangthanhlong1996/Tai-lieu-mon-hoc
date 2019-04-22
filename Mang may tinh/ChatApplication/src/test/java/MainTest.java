import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import share.entity.User;
import share.util.HibernateUtils;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();

        User user = new User("longgao","Thanh",false,"2121212 dfjsdf");
        try {
            session.save(user);
            transaction.commit();

        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("dfsdfsdfdf");
        }

//        List<User> listUsers = session.createQuery("from User").list();
//        for (User user : listUsers) {
//            System.out.println(user);
//        }


    }
}
