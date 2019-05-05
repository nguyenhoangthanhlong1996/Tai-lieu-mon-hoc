package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import java.util.List;

import vn.edu.hcmut.core.form.bkcourse.EnrollmentForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Enrollments;

public interface IEnrollments {
	Enrollments createNewEnrollment(EnrollmentForm form) throws Exception;
	Enrollments modifyEnrollmentExists(EnrollmentForm form) throws Exception;
	Enrollments findEnrollmentById(Long id) throws Exception;
	Long deleteEnrollmentById(Long id) throws Exception;
	List<Enrollments> getEnrollmentAll() throws Exception;
}
