package vn.edu.hcmut.admin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.edu.hcmut.core.repository.hibernate.IApplicationGeneral;

@Controller
@PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
public class MainController {
	@Autowired
	IApplicationGeneral applicationGeneralDAO;

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		try {
			// set breadcrumb
			model.addAttribute("breadcrumb", applicationGeneralDAO.getBreadcrumbByModuleName("categoies"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
}
