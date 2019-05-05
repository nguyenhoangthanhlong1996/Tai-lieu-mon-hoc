package vn.edu.hcmut.core.dao.hibernate.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.security.UserRaw;
import vn.edu.hcmut.core.repository.hibernate.security.IUsersRaw;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("usersRawHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class UsersRawDAO extends HibernateGenericDao<UserRaw, Long> implements IUsersRaw {

	@Autowired
	public UsersRawDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UserRaw> getListUsersRaw() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("lastName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<UserRaw> lst = this.findByCondition(UserRaw.class, cons, 0, 0);
			return lst;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean checkExistsUserName(String userName, Long id) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("ne");
				con.setValue1(id);
				cons.add(con);
			}
			{
				ConditionReport con = new ConditionReport();
				con.setCol("userName");
				con.setOperator("eq");
				con.setValue1(userName);
				cons.add(con);
			}
			List<UserRaw> lst = this.findByCondition(UserRaw.class, cons, 0, 0);
			if (lst != null && lst.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean checkExistsEmail(String email) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("email");
				con.setOperator("eq");
				con.setValue1(email);
				cons.add(con);
			}
			List<UserRaw> lst = this.findByCondition(UserRaw.class, cons, 0, 0);
			if (lst != null && lst.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}

}
