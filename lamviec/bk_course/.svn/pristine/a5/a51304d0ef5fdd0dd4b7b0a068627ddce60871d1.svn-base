package vn.edu.hcmut.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
//@PreAuthorize("hasAuthority('READ')")
@Controller
public class AdminController {
//	@GetMapping("/admin")
//	public String adminPage(Model model, Principal principal) {
//		org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
//		String userInfo = WebUtils.toString(loginedUser);
//		model.addAttribute("userInfo", userInfo);
//		return "admin";
//		//@PreAuthorize(“hasAuthority(‘READ_PRIVILEGE’)”)
//	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}

	@GetMapping("/logoutSuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		//return "logoutSuccessful";
		return "redirect:/login";
	}
}
