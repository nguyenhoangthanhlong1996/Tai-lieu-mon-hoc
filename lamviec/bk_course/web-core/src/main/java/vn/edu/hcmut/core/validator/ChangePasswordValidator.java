package vn.edu.hcmut.core.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.edu.hcmut.core.form.ChangePasswordForm;

public class ChangePasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ChangePasswordForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {
			ChangePasswordForm form = (ChangePasswordForm) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passWordLogin", "field.required", "Nhập mật khẩu mới");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rePassWordLogin", "field.required",
					"Nhập lại mật khẩu mới");
			if (!errors.hasErrors()) {
				if (form.getPassWordLogin().equals(form.getRePassWordLogin())) {
					errors.rejectValue("rePassWordLogin", "field.required", "Nhập lại mật khẩu mới");
				}
			}
		} catch (Exception e) {
			errors.reject("errorMessage", e.getMessage());
		}
	}

}
