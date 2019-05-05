package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.form.bkcourse.EnrollmentDetailsForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentDetails;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollmentDetails;
import vn.edu.hcmut.core.utilities.DateUtils;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("enrollmentDetailsHibernateDAO")
@Transactional(rollbackOn=Exception.class)
public class EnrollmentDetailsDAO extends HibernateGenericDao<EnrollmentDetails, Long> implements IEnrollmentDetails {
	@Autowired
	public EnrollmentDetailsDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EnrollmentDetails createNewEnrollmentDetail(EnrollmentDetailsForm form) throws Exception {
		try {
			EnrollmentDetails obj = new EnrollmentDetails();
			obj.setCourseId(form.getCourseId());
			obj.setEnrollmentId(form.getEnrollmentId());
			obj.setCreatedTime(DateUtils.dateTimeToString());
			obj.setSlMax(form.getSlMax());
			obj.setStt(0);
			Long id = this.save(obj);
			obj.setEnrollmentDetailId(id);
			return obj;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public EnrollmentDetails modifyEnrollmentDetailExists(EnrollmentDetailsForm form) throws Exception {
		try {
			EnrollmentDetails obj = new EnrollmentDetails();
			obj.setEnrollmentDetailId(form.getEnrollmentDetailId());
			obj.setCourseId(form.getCourseId());
			obj.setEnrollmentId(form.getEnrollmentId());
			obj.setCreatedTime(DateUtils.dateTimeToString());
			obj.setSlMax(form.getSlMax());
			obj.setStt(0);
			this.update(obj);
			return obj;
		}catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public EnrollmentDetails findEnrollmentDetailById(Long id) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("eq");
				con.setValue1(id);
				cons.add(con);
			}
			List<EnrollmentDetails> lst = this.findByCondition(EnrollmentDetails.class, cons, 0, 0);
			if(lst!=null && lst.size()>0) {
				return lst.get(0);
			}
			return null;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long removeEnrollmentDetailExist(Long id) throws Exception {
		try {
			this.delete(id);
			return id;
		}catch (Exception e) {
			throw e;
		}
	}

}
