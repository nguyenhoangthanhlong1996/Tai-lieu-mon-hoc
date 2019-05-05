package vn.edu.hcmut.core.dao.hibernate.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.form.UsersForm;
import vn.edu.hcmut.core.model.hibernate.security.Role;
import vn.edu.hcmut.core.model.hibernate.security.User;
import vn.edu.hcmut.core.repository.hibernate.security.IRole;
import vn.edu.hcmut.core.repository.hibernate.security.IUser;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("userHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class UserDAO extends HibernateGenericDao<User, Long> implements IUser {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	IRole roleHibernateDAO;

	@Autowired
	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User createNewUser(UsersForm form) throws Exception {
		try {
			User obj = new User();
			obj.setUserName(form.getUserName());
			obj.setFirstName(form.getFirstName());
			obj.setLastName(form.getLastName());
			obj.setEmail(form.getEmail());
			obj.setActive(form.isActive());
			obj.setPasswordLogin(passwordEncoder.encode(form.getPasswordLogin()));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleHibernateDAO.findRoleById(form.getRole()));
			obj.setRoles(roles);
			Long id = this.save(obj);
			obj.setUserId(id);
			if (id != null) {
				return obj;
			} else {
				throw new Exception("Không lưu được thông tin");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User findUserByUserName(String userName) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("userName");
				con.setOperator("eq");
				con.setValue1(userName);
				cons.add(con);
			}
			List<User> result = this.findByCondition(User.class, cons, 0, 0);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User findUserById(Long id) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("eq");
				con.setValue1(id);
				cons.add(con);
			}
			List<User> result = this.findByCondition(User.class, cons, 0, 0);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User modifyUserExists(UsersForm form) throws Exception {
		try {
			User obj = new User();
			obj.setUserName(form.getUserName());
			obj.setFirstName(form.getFirstName());
			obj.setLastName(form.getLastName());
			obj.setEmail(form.getEmail());
			obj.setActive(form.isActive());
			obj.setPasswordLogin(form.getPasswordLogin());
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleHibernateDAO.findRoleById(form.getRole()));
			obj.setRoles(roles);
			obj.setUserId(form.getUserId());
			this.update(obj);
			return obj;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long moveUserExistsById(Long id) throws Exception {
		try {
			this.delete(id);
			return id;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User changePasswordLogin(String userName, String passwordLogin) throws Exception {
		try {
			User obj = this.findUserByUserName(userName);
			if (obj != null) {
				obj.setPasswordLogin(passwordEncoder.encode(passwordLogin));
				this.update(obj);
				return obj;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

}
