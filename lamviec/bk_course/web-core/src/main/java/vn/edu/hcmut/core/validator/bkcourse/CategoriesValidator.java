package vn.edu.hcmut.core.validator.bkcourse;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.bkcourse.CategoriesForm;

@Component("categoriesValidator")
public class CategoriesValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	public boolean supports(Class<?> clazz) {
		return clazz == CategoriesForm.class;
	}

	public void validate(Object target, Errors errors) {
		try {
			// CategoriesForm categoriesForm = (CategoriesForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "field.required",
					messageSource.getMessage("NotEmpty.categoriesForm.categoryName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryNbr", "field.required",
					messageSource.getMessage("NotEmpty.categoriesForm.categoryNbr", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryDesc", "field.required",
					messageSource.getMessage("NotEmpty.categoriesForm.categoryDesc", null, Locale.getDefault()));
		} catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}

	}

}
