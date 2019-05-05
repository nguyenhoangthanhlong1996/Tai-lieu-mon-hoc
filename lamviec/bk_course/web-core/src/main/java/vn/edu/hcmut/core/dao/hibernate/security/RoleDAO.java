package vn.edu.hcmut.core.dao.hibernate.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.security.Role;
import vn.edu.hcmut.core.repository.hibernate.security.IRole;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("roleHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class RoleDAO extends HibernateGenericDao<Role, Long> implements IRole {

	@Autowired
	public RoleDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Role findRoleByName(String roleName) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("roleName");
				con.setOperator("eq");
				con.setValue1(roleName);
				cons.add(con);
			}
			List<Role> result = this.findByCondition(Role.class, cons, 0, 0);
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
	public Role findRoleById(Long id) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("eq");
				con.setValue1(id);
				cons.add(con);
			}
			List<Role> result = this.findByCondition(Role.class, cons, 0, 0);
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
	public List<Role> getListRole() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("roleName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<Role> lst = this.findByCondition(Role.class, cons, 0, 0);
			return lst;
		}catch (Exception e) {
			throw e;
		}
	}

}
