package vn.edu.hcmut.web.controller;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.hcmut.core.form.ChangePasswordForm;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.model.hibernate.ProfilesRaw;
import vn.edu.hcmut.core.repository.hibernate.IProfilesRaw;
import vn.edu.hcmut.core.repository.hibernate.security.IUser;
import vn.edu.hcmut.core.utilities.WebUtils;

@Controller
public class SecurityController {
	@Autowired
	IUser userHibernateDAO;
	@Autowired
	IProfilesRaw profilesRawHibernateDAO;

	@GetMapping("/admin")
	public String adminPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "adminPage";
	}

	@GetMapping("/changePasswd")
	public String changePassword(Model model, Principal principal) {
		try {
			ChangePasswordForm changePasswordForm = new ChangePasswordForm();
			model.addAttribute("changePasswordForm", changePasswordForm);
			ProfilesRaw profile = profilesRawHibernateDAO.getInfoProfile(principal.getName());
			model.addAttribute("fullName", profile.getHoLot() + " " + profile.getTen());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "changePasswd";
	}

	@PostMapping(value = "/changePasswd", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse changePasswordAjax(Model model,
			@ModelAttribute("changePasswordForm") @Validated ChangePasswordForm changePasswordForm,
			BindingResult result, Principal principal) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				return new MessageResponse("500", "errors", errors);
			} else {
				vn.edu.hcmut.core.model.hibernate.security.User obj = userHibernateDAO
						.changePasswordLogin(principal.getName(), changePasswordForm.getPassWordLogin());
				if (obj == null) {
					return new MessageResponse("500", "Không tìm thấy thông tin tài khoản");
				}
				return new MessageResponse("200", "ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		return "loginPage";
	}

	@GetMapping("/logoutSuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "redirect:/home";
	}

	@GetMapping("/userInfo")
	public String userInfo(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "userInfoPage";
	}

	@GetMapping("/403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "403Page";
	}
}
