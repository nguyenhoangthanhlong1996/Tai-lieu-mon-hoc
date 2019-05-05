package vn.edu.hcmut.core.dao.bkcourse;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.repository.bkcourse.IEnrollmentDetails;

@Repository("enrollmentDetailsDAO")
public class EnrollmentDetailsDAO extends JdbcDaoSupport implements IEnrollmentDetails {
	@Autowired
	public EnrollmentDetailsDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public List<Map<String, Object>> getListEnrollmentDetails() throws Exception {
		try {
			final String strQuerySQL = "select ls.id,slMax,en.courseEnrollmentName,co.courseName from courseenrollment_lst ls inner join courseenrollment en on ls.courseEnrollmentId=en.id inner join course co on ls.courseId=co.id";
			return this.getJdbcTemplate().queryForList(strQuerySQL);
		} catch (Exception e) {
			throw e;
		}
	}

}
