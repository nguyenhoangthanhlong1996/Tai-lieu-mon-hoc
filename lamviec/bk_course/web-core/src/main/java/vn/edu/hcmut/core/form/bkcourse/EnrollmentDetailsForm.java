package vn.edu.hcmut.core.form.bkcourse;

import lombok.Data;

@Data
public class EnrollmentDetailsForm {
	private Long enrollmentDetailId;
	private int slMax;
	private Long enrollmentId;
	private Long courseId;
}
