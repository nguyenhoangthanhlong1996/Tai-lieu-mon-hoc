package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import vn.edu.hcmut.core.form.bkcourse.EnrollmentDetailsForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentDetails;

public interface IEnrollmentDetails {
	EnrollmentDetails createNewEnrollmentDetail(EnrollmentDetailsForm form) throws Exception;
	EnrollmentDetails modifyEnrollmentDetailExists(EnrollmentDetailsForm form) throws Exception;
	EnrollmentDetails findEnrollmentDetailById(Long id) throws Exception;
	Long removeEnrollmentDetailExist(Long id) throws Exception;
}
