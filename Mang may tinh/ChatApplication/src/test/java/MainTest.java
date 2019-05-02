
import share.dao.UserDAO;
import share.entity.User;

import java.util.List;


public class MainTest {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        List<User> list = null;
        try {
            list = userDAO.getAllUserExceptUsername("asASas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User u : list)
            System.out.println(u);

    }
}
