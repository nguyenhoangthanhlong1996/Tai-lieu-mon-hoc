package vn.edu.hcmut.core.validator;

import java.util.Locale;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.RegisterForm;
import vn.edu.hcmut.core.repository.hibernate.security.IUsersRaw;

@Component("registerValidator")
public class RegisterValidator implements Validator {
	@Autowired
	private MessageSource messageSource;
	// common-validator library.
	private EmailValidator emailValidator = EmailValidator.getInstance();
	private DateValidator dateValidator = DateValidator.getInstance();
	@Autowired
	private IUsersRaw usersRawHibernateDAO;

	// Các lớp được hỗ trợ bởi Validator này.
	public boolean supports(Class<?> clazz) {
		return clazz == RegisterForm.class;
	}

	public void validate(Object target, Errors errors) {
		try {
			RegisterForm registerForm = (RegisterForm) target;
			// Kiểm tra các field của RegisterForm.
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.fullName", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.gender", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.birthDate", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthPlace", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.birthPlace", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.phone", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPassport", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.idPassport", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPassportRetype", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.idPassportRetype", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPassportDate", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.idPassportDate", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPassportPlace", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.idPassportPlace", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPassportImageFront", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.idPassportImageFront", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPassportImageBack", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.idPassportImageBack", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.email", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailRetype", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.emailRetype", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityPlace", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.cityPlace", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "districtPlace", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.districtPlace", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wardPlace", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.wardPlace", null, Locale.getDefault()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetPlace", "field.required",
					messageSource.getMessage("NotEmpty.registerForm.streetPlace", null, Locale.getDefault()));

			if (!errors.hasFieldErrors("birthPlace")) {
				if ((registerForm.getBirthPlace().equalsIgnoreCase("65")
						|| registerForm.getBirthPlace().equalsIgnoreCase("81"))
						&& registerForm.getBirthPlaceOther().isEmpty()) {
					errors.rejectValue("birthPlaceOther", "field.required", messageSource
							.getMessage("NotEmpty.registerForm.birthPlaceOther", null, Locale.getDefault()));
				}
			}

			if (!this.emailValidator.isValid(registerForm.getEmail())) {
				// Email không hợp lệ.
				errors.rejectValue("email", "field.required",
						messageSource.getMessage("Pattern.registerForm.email", null, Locale.getDefault()));
			} else if (registerForm.getUserId() == null) {
				boolean exists = usersRawHibernateDAO.checkExistsEmail(registerForm.getEmail().trim());
				if (exists) {
					// Email đã được sử dụng bởi tài khoản khác.
					errors.rejectValue("email", "field.required",
							messageSource.getMessage("Duplicate.registerForm.email", null, Locale.getDefault()));
				}
			}

			if (!errors.hasFieldErrors("idPassport")) {
				if (registerForm.getIdPassport().length() < 8) {
					errors.rejectValue("idPassport", "field.required",
							messageSource.getMessage("NotEmpty.registerForm.idPassport", null, Locale.getDefault()));
				}
			} else if (!registerForm.getIdPassport().trim()
					.equalsIgnoreCase(registerForm.getIdPassportRetype().trim())) {
				errors.rejectValue("idPassportRetype", "field.required",
						messageSource.getMessage("NotEmpty.registerForm.idPassportRetype", null, Locale.getDefault()));
			}

			if (!errors.hasFieldErrors("idPassport")) {
				boolean exists = usersRawHibernateDAO.checkExistsUserName(registerForm.getIdPassport().trim(), 0L);
				if (exists) {
					// Tên tài khoản đã bị sử dụng bởi người khác.
					errors.rejectValue("idPassport", "field.required",
							messageSource.getMessage("Duplicate.registerForm.userName", null, Locale.getDefault()));
				}
			}

			if (!errors.hasFieldErrors("birthDate") && registerForm.getBirthDate().length() != 4) {
				if (this.dateValidator.validate(registerForm.getBirthDate(), "dd/MM/yyyy") == null) {
					errors.rejectValue("birthDate", "field.required",
							messageSource.getMessage("Pattern.registerForm.birthDate", null, Locale.getDefault()));
				}
			}

			if (!errors.hasFieldErrors("idPassportDate")) {
				if (this.dateValidator.validate(registerForm.getIdPassportDate(), "dd/MM/yyyy") == null) {
					errors.rejectValue("idPassportDate", "field.required",
							messageSource.getMessage("Pattern.registerForm.idPassportDate", null, Locale.getDefault()));
				}
			}

			if (!errors.hasErrors()) {
				if (!registerForm.getEmail().equals(registerForm.getEmailRetype())) {
					errors.rejectValue("emailRetype", "field.required",
							messageSource.getMessage("Match.registerForm.confirmEmail", null, Locale.getDefault()));
				}
			}

			if (!errors.hasErrors()) {
				if (!registerForm.getIdPassport().equals(registerForm.getIdPassportRetype())) {
					errors.rejectValue("idPassportRetype", "field.required", messageSource
							.getMessage("Match.registerForm.confirmIdPassport", null, Locale.getDefault()));
				}
			}

		} catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}
	}
}
