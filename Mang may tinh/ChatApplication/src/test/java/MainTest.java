
import share.dao.UserDAO;
import share.entity.User;

import java.util.List;


public class MainTest {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        List<User> list = userDAO.getAllUserExceptUsername("asASas");
        for (User u : list)
            System.out.println(u);

    }
}
