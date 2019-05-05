package vn.edu.hcmut.admin.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.hcmut.core.form.UsersForm;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.model.hibernate.security.Role;
import vn.edu.hcmut.core.model.hibernate.security.User;
import vn.edu.hcmut.core.model.hibernate.security.UserRaw;
import vn.edu.hcmut.core.repository.hibernate.IApplicationGeneral;
import vn.edu.hcmut.core.repository.hibernate.security.IRole;
import vn.edu.hcmut.core.repository.hibernate.security.IUser;
import vn.edu.hcmut.core.repository.hibernate.security.IUsersRaw;
import vn.edu.hcmut.core.utilities.WebUtils;
import vn.edu.hcmut.core.validator.UsersValidator;

@Controller
@PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
public class SecurityController {
	@Autowired
	IApplicationGeneral applicationGeneralDAO;
	@Autowired
	IUser userHibernateDAO;
	@Autowired
	IUsersRaw usersRawHibernateDAO;
	@Autowired
	IRole roleHibernateDAO;
	@Autowired
	UsersValidator usersValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form mục tiêu
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == UsersForm.class) {
			dataBinder.setValidator(usersValidator);
		}
	}
	
	@GetMapping("/userInfo")
	public String userInfo(Model model, Principal principal) {
		// Sau khi user login thanh cong se co principal
		//String userName = principal.getName();
		// System.out.println("User Name: " + userName);
		org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "userInfo";
	}

	@GetMapping("/403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "errors/403";
	}

	@GetMapping("/users")
	public String userForm(Model model) {
		try {
			UsersForm userForm = new UsersForm();
			userForm.setUserId(0L);
			model.addAttribute("usersForm", userForm);
			//List role
			List<Role> lstRole = roleHibernateDAO.getListRole();
			model.addAttribute("Roles", lstRole);
			//List User
			//List<UserRaw> lstUser = new ArrayList<UserRaw>();
			List<UserRaw> lstUser = usersRawHibernateDAO.getListUsersRaw();
			model.addAttribute("Users", lstUser);
			// set breadcrumb
			model.addAttribute("breadcrumb", applicationGeneralDAO.getBreadcrumbByModuleName("users"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "views/users";
	}
	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse saveUserFormAjax(Model model,
			@ModelAttribute("enrollmentDetailsForm") @Validated UsersForm userForm, BindingResult result) {
		try{
			if (result.hasErrors()) {
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				return new MessageResponse("500", "errors", errors);
			} else {
				User user = null;
				Long userId = userForm.getUserId();
				if(userId==null || userId==0) {
					user = userHibernateDAO.createNewUser(userForm);
					return new MessageResponse("200", "add", user);
				}else {
					//Get old passwd
					User userOld = userHibernateDAO.findUserById(userForm.getUserId());
					userForm.setPasswordLogin(userOld.getPasswordLogin());
					user = userHibernateDAO.modifyUserExists(userForm);
					user.setPasswordLogin("");
					return new MessageResponse("200", "update", user);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
}
