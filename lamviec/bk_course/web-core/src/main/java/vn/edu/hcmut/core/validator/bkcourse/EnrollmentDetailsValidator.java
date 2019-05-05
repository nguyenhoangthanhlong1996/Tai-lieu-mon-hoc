package vn.edu.hcmut.core.validator.bkcourse;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.bkcourse.EnrollmentDetailsForm;

@Component("enrollmentDetailValidator")
public class EnrollmentDetailsValidator implements Validator {
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == EnrollmentDetailsForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {
			//EnrollmentDetailsForm form = (EnrollmentDetailsForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slMax", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentDetailForm.slMax", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enrollmentId", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentDetailForm.enrollmentId", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseId", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentDetailForm.courseId", null, Locale.getDefault()));
		}catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}
		
	}

}
