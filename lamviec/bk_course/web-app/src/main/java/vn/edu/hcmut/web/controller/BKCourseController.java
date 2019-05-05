package vn.edu.hcmut.web.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.hcmut.core.dao.bkcourse.BuyPaymentCourses;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.model.hibernate.ProfilesRaw;
import vn.edu.hcmut.core.repository.hibernate.IProfilesRaw;

@Controller
public class BKCourseController {
	@Autowired
	BuyPaymentCourses buyPaymentCourses;
	@Autowired
	IProfilesRaw profilesRawHibernateDAO;
	
	@RequestMapping("single-course")
	public String SingleCourse() {
		return "singleCoursePage";
	}

	// Login
	// Buy course
	@GetMapping("/buy-course")
	public String buyCourse(@RequestParam(value = "courseid", required = true) Long courseId, Principal principal,
			RedirectAttributes redirectAttributes) {
		try {
			String userName = principal.getName();
			buyPaymentCourses.BuyCourse(userName, courseId);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addAttribute("msg", e.getMessage());
			return "redirect:/errorPage";
		}
		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String cart(Model model, Principal principal, RedirectAttributes redirectAttributes) {
		try {
			String userName = principal.getName();
			List<Map<String, Object>> ls = buyPaymentCourses.getListCourseInCart(userName);
			model.addAttribute("Courses", ls);
			ProfilesRaw profile = profilesRawHibernateDAO.getInfoProfile(principal.getName());
			model.addAttribute("fullName", profile.getHoLot() + " " + profile.getTen());
			return "cart";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addAttribute("msg", e.getMessage());
			return "redirect:/errorPage";
		}
	}

	@PostMapping(value = "/api/deleteCourse", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse deleteCourseInCart(Long id, Principal principal) {
		try {
			String userName = principal.getName();
			buyPaymentCourses.deleteCourseInCart(userName, id);
			return new MessageResponse("200", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

	@PostMapping(value = "/api/successRegister", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse successRegister(String id, Principal principal) {
		try {
			String userName = principal.getName();
			buyPaymentCourses.successRegister(userName);
			return new MessageResponse("200", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

	@GetMapping("/payments")
	public String getListPayment(Model model, Principal principal) {
		try {
			String userName = principal.getName();
			List<Map<String, Object>> ls = buyPaymentCourses.getListPayment(userName);
			model.addAttribute("Payments", ls);
			ProfilesRaw profile = profilesRawHibernateDAO.getInfoProfile(principal.getName());
			model.addAttribute("fullName", profile.getHoLot() + " " + profile.getTen());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payments";
	}
}
