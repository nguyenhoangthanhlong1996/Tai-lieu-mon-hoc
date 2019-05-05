package vn.edu.hcmut.core.validator.bkcourse;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.bkcourse.EnrollmentForm;
import vn.edu.hcmut.core.utilities.DateUtils;

@Component("enrollmentValidator")
public class EnrollmentValidator implements Validator {
	@Autowired
	private MessageSource messageSource;
	
	public boolean supports(Class<?> clazz) {
		return clazz == EnrollmentForm.class;
	}

	public void validate(Object target, Errors errors) {
		try {
			EnrollmentForm enrollmentForm = (EnrollmentForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enrollmentName", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentForm.enrollmentName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enrollmentNbr", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentForm.enrollmentNbr", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enrollmentBegin", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentForm.enrollmentBegin", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enrollmentEnd", "field.required",
					messageSource.getMessage("NotEmpty.enrollmentForm.enrollmentEnd", null, Locale.getDefault()));

			if(!errors.hasFieldErrors("enrollmentBegin")) {
				Date date = DateUtils.stringToDatetime(enrollmentForm.getEnrollmentBegin());
				if(date == null) {
					errors.rejectValue("enrollmentBegin", "field.format",
							messageSource.getMessage("Format.enrollmentForm.enrollmentBegin", null, Locale.getDefault()));
				}
			}
			
			if(!errors.hasFieldErrors("enrollmentEnd")) {
				Date date = DateUtils.stringToDatetime(enrollmentForm.getEnrollmentEnd());
				if(date == null) {
					errors.rejectValue("enrollmentEnd", "field.format",
							messageSource.getMessage("Format.enrollmentForm.enrollmentEnd", null, Locale.getDefault()));
				}
			}
			
		}catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}
	}

}
