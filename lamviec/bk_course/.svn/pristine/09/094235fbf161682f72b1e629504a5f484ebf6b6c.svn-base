package vn.edu.hcmut.core.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.ProfilesRaw;
import vn.edu.hcmut.core.repository.hibernate.IProfilesRaw;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("profilesRawHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class ProfilesRawDAO extends HibernateGenericDao<ProfilesRaw, Long> implements IProfilesRaw {

	@Autowired
	public ProfilesRawDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ProfilesRaw getInfoProfile(String soCMND) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("cmndSo");
				con.setOperator("eq");
				con.setValue1(soCMND);
				cons.add(con);
			}
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("order");
				con.setValue1("desc");
				cons.add(con);
			}
			List<ProfilesRaw> ls = this.findByCondition(ProfilesRaw.class, cons, 1, 1);
			if (ls != null && ls.size() > 0) {
				return ls.get(0);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

}
