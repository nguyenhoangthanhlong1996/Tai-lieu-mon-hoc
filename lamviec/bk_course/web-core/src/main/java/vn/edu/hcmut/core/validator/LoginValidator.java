package vn.edu.hcmut.core.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.LoginForm;

@Component
public class LoginValidator implements Validator {
	@Autowired
	private MessageSource messageSource;

	public boolean supports(Class<?> clazz) {
		return clazz == LoginForm.class;
	}

	public void validate(Object target, Errors errors) {
		try {
			// LoginForm loginForm = (LoginForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
					messageSource.getMessage("NotEmpty.loginForm.userName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					messageSource.getMessage("NotEmpty.loginForm.password", null, Locale.getDefault()));
		} catch (Exception e) {
			errors.reject("system", e.getMessage());
		}
	}

}
