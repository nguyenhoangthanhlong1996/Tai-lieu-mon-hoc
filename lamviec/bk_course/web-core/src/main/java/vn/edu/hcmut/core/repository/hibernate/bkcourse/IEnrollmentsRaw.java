package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import java.util.List;

import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentsRaw;

public interface IEnrollmentsRaw {
	List<EnrollmentsRaw> getListEnrollmentRaw() throws Exception;
}
