package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentsRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollmentsRaw;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("enrollmentsRawHibernateDAO")
@Transactional(rollbackOn=Exception.class)
public class EnrollmentsRawDAO extends HibernateGenericDao<EnrollmentsRaw, Long> implements IEnrollmentsRaw {
	@Autowired
	public EnrollmentsRawDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EnrollmentsRaw> getListEnrollmentRaw() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("courseEnrollmentName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<EnrollmentsRaw> result = this.findByCondition(EnrollmentsRaw.class, cons, 0, 0);
			if (result != null && result.size() > 0) {
				return result;
			}
			return null;
		}catch (Exception e) {
			throw e;
		}
	}

}
