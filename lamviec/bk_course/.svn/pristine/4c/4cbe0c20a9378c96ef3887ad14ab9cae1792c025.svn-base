package vn.edu.hcmut.admin.controller;

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
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Courses;
import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentDetails;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Enrollments;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategories;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICourses;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollmentDetails;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollments;

@RestController
@RequestMapping("/api/")
@PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
public class BKCourseRestController {
	@Autowired
	ICategories categoriesHibernateDAO;
	
	@Autowired
	ICourses courseHibernateDAO;
	
	@Autowired
	IEnrollments enrollmentsHibernateDAO;
	
	@Autowired
	IEnrollmentDetails enrollmentDetailsHibernateDAO;

	@GetMapping("getCategory/{id}")
	public MessageResponse getCategoryById(@PathVariable Long id) {
		try {
			Categories obj = categoriesHibernateDAO.findCategoryById(id);
			if (obj == null) {
				return new MessageResponse(Errors.NOT_FOUND_DATA.getCode(), Errors.NOT_FOUND_DATA.getText());
			}
			return new MessageResponse("200", "ok", obj);
		} catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@PostMapping(value = "deleteCategory", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public MessageResponse deleteCategoryById(Long id) {
		try {
			Long categoryId = categoriesHibernateDAO.removeCategoryExists(id);
			return new MessageResponse("200", "ok", categoryId);
		}catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@GetMapping("getCourse/{id}")
	public MessageResponse getCourseById(@PathVariable Long id) {
		try {
			Courses obj = courseHibernateDAO.findCourseById(id);
			if (obj == null) {
				return new MessageResponse(Errors.NOT_FOUND_DATA.getCode(), Errors.NOT_FOUND_DATA.getText());
			}
			return new MessageResponse("200", "ok", obj);
		} catch (Exception e) {
			//e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@PostMapping(value = "deleteCourse", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public MessageResponse deleteCourseById(Long id) {
		try {
			Long courseId = courseHibernateDAO.removeCourseExists(id);
			return new MessageResponse("200", "ok", courseId);
		}catch (Exception e) {
			//e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@GetMapping("getEnrollment/{id}")
	public MessageResponse getEnrollmentById(@PathVariable Long id) {
		try {
			Enrollments obj = enrollmentsHibernateDAO.findEnrollmentById(id);
			if (obj == null) {
				return new MessageResponse(Errors.NOT_FOUND_DATA.getCode(), Errors.NOT_FOUND_DATA.getText());
			}
			return new MessageResponse("200", "ok", obj);
		} catch (Exception e) {
			//e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@PostMapping(value = "deleteEnrollment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public MessageResponse deleteEnrollmentById(Long id) {
		try {
			Long courseId = enrollmentsHibernateDAO.deleteEnrollmentById(id);
			return new MessageResponse("200", "ok", courseId);
		}catch (Exception e) {
			//e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@GetMapping("getEnrollmentDetail/{id}")
	public MessageResponse getEnrollmentDetailById(@PathVariable Long id) {
		try {
			EnrollmentDetails obj = enrollmentDetailsHibernateDAO.findEnrollmentDetailById(id);
			if (obj == null) {
				return new MessageResponse(Errors.NOT_FOUND_DATA.getCode(), Errors.NOT_FOUND_DATA.getText());
			}
			return new MessageResponse("200", "ok", obj);
		} catch (Exception e) {
			//e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@PostMapping(value = "deleteEnrollmentDetail", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public MessageResponse deleteEnrollmentDetailById(Long id) {
		try {
			Long enrollmentDetailId = enrollmentDetailsHibernateDAO.removeEnrollmentDetailExist(id);
			return new MessageResponse("200", "ok", enrollmentDetailId);
		}catch (Exception e) {
			//e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}
}
