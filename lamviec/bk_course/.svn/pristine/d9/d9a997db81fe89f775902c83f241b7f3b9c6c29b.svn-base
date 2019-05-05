package vn.edu.hcmut.admin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmut.core.enums.Errors;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.model.hibernate.security.User;
import vn.edu.hcmut.core.repository.hibernate.security.IUser;

@RestController
@RequestMapping("/api/")
@PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
public class SecurityRestController {
	@Autowired
	IUser userHibernateDAO;
	
	@GetMapping("getInfoUserLogin")
	public MessageResponse getUserByUserName(Principal principal) {
		try {
			User obj = userHibernateDAO.findUserByUserName(principal.getName());
			if (obj == null) {	
				return new MessageResponse(Errors.NOT_FOUND_DATA.getCode(), Errors.NOT_FOUND_DATA.getText());
			}
			obj.setPasswordLogin("");
			return new MessageResponse("200", "ok", obj);			
		}catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@GetMapping("getUser/{id}")
	public MessageResponse getUserById(@PathVariable Long id) {
		try {
			User obj = userHibernateDAO.findUserById(id);
			if (obj == null) {	
				return new MessageResponse(Errors.NOT_FOUND_DATA.getCode(), Errors.NOT_FOUND_DATA.getText());
			}
			obj.setPasswordLogin("");
			return new MessageResponse("200", "ok", obj);			
		}catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@PostMapping(value = "deleteUser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public MessageResponse deleteUserById(Long id) {
		try {
			Long userId = userHibernateDAO.moveUserExistsById(id);
			return new MessageResponse("200", "ok", userId);
		}catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}
}
