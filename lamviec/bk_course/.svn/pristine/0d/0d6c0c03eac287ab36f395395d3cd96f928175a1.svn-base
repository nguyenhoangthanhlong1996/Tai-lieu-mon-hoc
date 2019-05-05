package vn.edu.hcmut.core.repository.hibernate.security;

import vn.edu.hcmut.core.form.UsersForm;
import vn.edu.hcmut.core.model.hibernate.security.User;

public interface IUser {
	User createNewUser(UsersForm form) throws Exception;

	User findUserByUserName(String userName) throws Exception;

	User findUserById(Long id) throws Exception;

	User modifyUserExists(UsersForm form) throws Exception;

	Long moveUserExistsById(Long id) throws Exception;

	User changePasswordLogin(String userName, String passwordLogin) throws Exception;
}
