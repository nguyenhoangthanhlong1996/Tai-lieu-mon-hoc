package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.bkcourse.CoursesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICoursesRaw;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("coursesRawHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class CoursesRawDAO extends HibernateGenericDao<CoursesRaw, Long> implements ICoursesRaw {

	@Autowired
	public CoursesRawDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CoursesRaw> getAllCoursesRaw() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("courseName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<CoursesRaw> result = this.findByCondition(CoursesRaw.class, cons, 0, 0);
			if (result != null && result.size() > 0) {
				return result;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

}
