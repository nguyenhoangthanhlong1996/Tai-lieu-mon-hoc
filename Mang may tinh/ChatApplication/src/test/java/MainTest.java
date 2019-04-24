
import share.dao.UserDAO;
import share.entity.User;

import java.util.List;


public class MainTest {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        System.out.println(userDAO.updateStatusOnline("thanhlong", false));
    }
}
