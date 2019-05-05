package vn.edu.hcmut.core.validator.bkcourse;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.apache.commons.validator.routines.DoubleValidator;

import vn.edu.hcmut.core.form.bkcourse.CoursesForm;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICourses;

@Component("coursesValidator")
public class CoursesValidator implements Validator {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ICourses coursesHibernateDAO;

	private DoubleValidator doubleValidator = DoubleValidator.getInstance();

	public boolean supports(Class<?> clazz) {
		return clazz == CoursesForm.class;
	}

	public void validate(Object target, Errors errors) {
		try {
			CoursesForm coursesForm = (CoursesForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseName", "field.required",
					messageSource.getMessage("NotEmpty.coursesForm.courseName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseCategory", "field.required",
					messageSource.getMessage("NotEmpty.coursesForm.courseCategory", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseNbr", "field.required",
					messageSource.getMessage("NotEmpty.coursesForm.courseNbr", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseLearningHour", "field.required",
					messageSource.getMessage("NotEmpty.coursesForm.courseLearningHour", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coursePrice", "field.required",
					messageSource.getMessage("NotEmpty.coursesForm.coursePrice", null, Locale.getDefault()));
			// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseDesc",
			// "field.required",
			// messageSource.getMessage("NotEmpty.coursesForm.courseDesc", null,
			// Locale.getDefault()));
			// Check price
			if (!errors.hasFieldErrors("coursePrice")) {
				if (!this.doubleValidator.isValid(coursesForm.getCoursePrice())) {
					errors.rejectValue("coursePrice", "field.format",
							messageSource.getMessage("Format.coursesForm.coursePrice", null, Locale.getDefault()));
				}
			}
			// Check exist Nbr
			if (!errors.hasErrors()) {
				if (coursesHibernateDAO.checkExistCourseNbr(coursesForm.getCourseId(),
						coursesForm.getCourseNbr().trim())) {
					errors.rejectValue("courseNbr", "field.duplicate",
							messageSource.getMessage("Duplicate.coursesForm.courseNbr", null, Locale.getDefault()));
				}
			}
		} catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}
	}

}
