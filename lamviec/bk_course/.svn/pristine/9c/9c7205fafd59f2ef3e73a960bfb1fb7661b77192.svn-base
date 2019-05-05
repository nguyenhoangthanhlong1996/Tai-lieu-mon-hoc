package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.form.bkcourse.EnrollmentForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Enrollments;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollments;
import vn.edu.hcmut.core.utilities.DateUtils;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("enrollmentsHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class EnrollmentsDAO extends HibernateGenericDao<Enrollments, Long> implements IEnrollments {

	@Autowired
	public EnrollmentsDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Enrollments createNewEnrollment(EnrollmentForm form) throws Exception {
		try {
			Enrollments enrollments = new Enrollments();
			enrollments.setCourseEnrollmentName(form.getEnrollmentName());
			enrollments.setCourseEnrollmentNbr(form.getEnrollmentNbr());
			enrollments.setCourseEnrollmentBegin(DateUtils.dateTimeToString(form.getEnrollmentBegin()));
			enrollments.setCourseEnrollmentEnd(DateUtils.dateTimeToString(form.getEnrollmentEnd()));
			enrollments.setCourseEnrollmentDesc(form.getEnrollmentDesc());
			enrollments.setCourseEnrollmentEnable(form.isEnrollmentEnable());
			enrollments.setCreatedTime(DateUtils.dateTimeToString());
			Long enrollmentId = this.save(enrollments);
			enrollments.setCourseEnrollmentId(enrollmentId);
			return enrollments;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Enrollments modifyEnrollmentExists(EnrollmentForm form) throws Exception {
		try {
			Enrollments enrollments = new Enrollments();
			enrollments.setCourseEnrollmentId(form.getEnrollmentId());
			enrollments.setCourseEnrollmentName(form.getEnrollmentName());
			enrollments.setCourseEnrollmentNbr(form.getEnrollmentNbr());
			enrollments.setCourseEnrollmentBegin(DateUtils.dateTimeToString(form.getEnrollmentBegin()));
			enrollments.setCourseEnrollmentEnd(DateUtils.dateTimeToString(form.getEnrollmentEnd()));
			enrollments.setCourseEnrollmentDesc(form.getEnrollmentDesc());
			enrollments.setCourseEnrollmentEnable(form.isEnrollmentEnable());
			enrollments.setCreatedTime(DateUtils.dateTimeToString());

			this.update(enrollments);
			return enrollments;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Enrollments findEnrollmentById(Long id) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("eq");
				con.setValue1(id);
				cons.add(con);
			}
			List<Enrollments> result = this.findByCondition(Enrollments.class, cons, 0, 0);
			if (result != null && result.size() > 0) {
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long deleteEnrollmentById(Long id) throws Exception {
		try {
			this.delete(id);
			return id;
		}catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Enrollments> getEnrollmentAll() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("courseEnrollmentName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<Enrollments> result = this.findByCondition(Enrollments.class, cons, 0, 0);
			if (result != null && result.size() > 0) {
				return result;
			}
			return null;
		}catch (Exception e) {
			throw e;
		}
	}

}
