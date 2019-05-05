package vn.edu.hcmut.core.validator;

import java.util.Locale;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.UsersForm;
import vn.edu.hcmut.core.repository.hibernate.security.IUsersRaw;

@Component("usersValidator")
public class UsersValidator implements Validator {
	@Autowired
	private MessageSource messageSource;
	private EmailValidator emailValidator = EmailValidator.getInstance();
	@Autowired
	private IUsersRaw usersRawHibernateDAO;
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UsersForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {
			UsersForm usersForm = (UsersForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required",
					messageSource.getMessage("NotEmpty.usersForm.userName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required",
					messageSource.getMessage("NotEmpty.usersForm.firstName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required",
					messageSource.getMessage("NotEmpty.usersForm.lastName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required",
					messageSource.getMessage("NotEmpty.usersForm.email", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "field.required",
					messageSource.getMessage("NotEmpty.usersForm.role", null, Locale.getDefault()));
			if(usersForm.getUserId()==0) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordLogin", "field.required",
						messageSource.getMessage("NotEmpty.usersForm.passwordLogin", null, Locale.getDefault()));
			}
			if(!errors.hasFieldErrors("email")) {
				if(!emailValidator.isValid(usersForm.getEmail())) {
					errors.rejectValue("email", "field.required",
							messageSource.getMessage("Format.usersForm.email", null, Locale.getDefault()));
				}
			}
			boolean checkExists = usersRawHibernateDAO.checkExistsUserName(usersForm.getUserName(), usersForm.getUserId());
			if(checkExists) {
				errors.rejectValue("userName", "field.required",
						messageSource.getMessage("Duplicate.usersForm.userName", null, Locale.getDefault()));
			}
			
		}catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}
	}

}
