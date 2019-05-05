package vn.edu.hcmut.admin.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.hcmut.core.form.bkcourse.CategoriesForm;
import vn.edu.hcmut.core.form.bkcourse.CoursesForm;
import vn.edu.hcmut.core.form.bkcourse.EnrollmentDetailsForm;
import vn.edu.hcmut.core.form.bkcourse.EnrollmentForm;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.model.hibernate.bkcourse.CategoriesRaw;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Courses;
import vn.edu.hcmut.core.model.hibernate.bkcourse.CoursesRaw;
import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentDetails;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Enrollments;
import vn.edu.hcmut.core.model.hibernate.bkcourse.EnrollmentsRaw;
import vn.edu.hcmut.core.repository.hibernate.IApplicationGeneral;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategories;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategoriesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICourses;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICoursesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollmentDetails;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollments;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.IEnrollmentsRaw;
import vn.edu.hcmut.core.validator.bkcourse.CategoriesValidator;
import vn.edu.hcmut.core.validator.bkcourse.CoursesValidator;
import vn.edu.hcmut.core.validator.bkcourse.EnrollmentDetailsValidator;
import vn.edu.hcmut.core.validator.bkcourse.EnrollmentValidator;

@Controller
@PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
public class BKCourseController {
	@Autowired
	CategoriesValidator categoriesValidator;
	@Autowired
	CoursesValidator coursesValidator;
	@Autowired
	EnrollmentValidator enrollmentValidator;
	@Autowired
	EnrollmentDetailsValidator enrollmentDetailValidator;
	@Autowired
	ICategories categoriesHibernateDAO;
	@Autowired
	ICategoriesRaw categoriesRawHibernateDAO;
	@Autowired
	ICourses coursesHibernateDAO;
	@Autowired
	ICoursesRaw coursesRawHibernateDAO;
	@Autowired
	IEnrollments enrollmentsHibernateDAO;
	@Autowired
	IEnrollmentsRaw enrollmentsRawHibernateDAO;
	@Autowired
	IEnrollmentDetails enrollmentDetailsHibernateDAO;
	@Autowired
	vn.edu.hcmut.core.repository.bkcourse.IEnrollmentDetails enrollmentDetailsDAO;
	@Autowired
	IApplicationGeneral applicationGeneralDAO;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form mục tiêu
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == CategoriesForm.class) {
			dataBinder.setValidator(categoriesValidator);
		} else if (target.getClass() == CoursesForm.class) {
			dataBinder.setValidator(coursesValidator);
		} else if (target.getClass() == EnrollmentForm.class) {
			dataBinder.setValidator(enrollmentValidator);
		} else if (target.getClass() == EnrollmentDetailsForm.class) {
			dataBinder.setValidator(enrollmentDetailValidator);
		}
	}

	@GetMapping("/categories")
	public String categoriesForm(Model model) {
		try {
			CategoriesForm categoriesForm = new CategoriesForm();
			categoriesForm.setCategoryId(0L);
			model.addAttribute("categoriesForm", categoriesForm);
			// Get list exists
			List<CategoriesRaw> lstCategories = categoriesRawHibernateDAO.getAllCategoriesRaw();
			model.addAttribute("Categories", lstCategories);
			// set breadcrumb
			model.addAttribute("breadcrumb", applicationGeneralDAO.getBreadcrumbByModuleName("categoies"));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "views/bkcourse/categories";
	}

	@PostMapping(value = "/categories", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse saveCategoriesFormAjax(Model model,
			@ModelAttribute("categoriesForm") @Validated CategoriesForm categoriesForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		try {
			// Validate result
			if (result.hasErrors()) {
				// Get error message
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				return new MessageResponse("500", "errors", errors);
			} else {
				Categories category = null;
				Long categoryId = categoriesForm.getCategoryId();
				if (categoryId == null || categoryId == 0) {
					category = categoriesHibernateDAO.createNewCategory(categoriesForm);
					category.setCategoryDesc("");
					category.setCategoryImage("");
					return new MessageResponse("200", "add", category);
				} else {
					category = categoriesHibernateDAO.modifyCategoryExists(categoriesForm);
					category.setCategoryDesc("");
					category.setCategoryImage("");
					return new MessageResponse("200", "update", category);
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

	@GetMapping("/courses")
	public String coursesForm(Model model) {
		try {
			CoursesForm coursesForm = new CoursesForm();
			coursesForm.setCourseId(0L);
			model.addAttribute("coursesForm", coursesForm);
			// Get list categories
			List<CategoriesRaw> lstCategories = categoriesRawHibernateDAO.getAllCategoriesRaw();
			model.addAttribute("Categories", lstCategories);
			// Get list courses
			List<CoursesRaw> lstCourses = coursesRawHibernateDAO.getAllCoursesRaw();
			model.addAttribute("Courses", lstCourses);
			// set breadcrumb
			model.addAttribute("breadcrumb", applicationGeneralDAO.getBreadcrumbByModuleName("courses"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "views/bkcourse/courses";
	}

	@PostMapping(value = "/courses", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse saveCourseFormAjax(Model model,
			@ModelAttribute("coursesForm") @Validated CoursesForm coursesForm, BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				return new MessageResponse("500", "errors", errors);
			} else {
				Courses course = null;
				Long courseId = coursesForm.getCourseId();
				if (courseId == null || courseId == 0) {
					course = coursesHibernateDAO.createNewCourse(coursesForm);
					course.setCourseDesc("");
					course.setCourseCategory(null);
					return new MessageResponse("200", "add", course);
				} else {
					course = coursesHibernateDAO.modifyCourseExists(coursesForm);
					course.setCourseDesc("");
					course.setCourseCategory(null);
					return new MessageResponse("200", "update", course);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

	@GetMapping("/enrollments")
	public String enrollmentForm(Model model) {
		try {
			EnrollmentForm enrollmentsForm = new EnrollmentForm();
			enrollmentsForm.setEnrollmentId(0L);
			model.addAttribute("enrolmentsForm", enrollmentsForm);
			// Get list enrollmentv
			List<Enrollments> lstenrollments = enrollmentsHibernateDAO.getEnrollmentAll();
			model.addAttribute("Enrollments", lstenrollments);
			// set breadcrumb
			model.addAttribute("breadcrumb", applicationGeneralDAO.getBreadcrumbByModuleName("enrollments"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "views/bkcourse/enrollments";
	}

	@PostMapping(value = "/enrollments", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse saveEnrollmentFormAjax(Model model,
			@ModelAttribute("enrolmentsForm") @Validated EnrollmentForm enrolmentsForm, BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				return new MessageResponse("500", "errors", errors);
			} else {
				Enrollments enrollment = null;
				Long enrollmentId = enrolmentsForm.getEnrollmentId();
				if (enrollmentId == null || enrollmentId == 0) {
					enrollment = enrollmentsHibernateDAO.createNewEnrollment(enrolmentsForm);
					enrollment.setCourseEnrollmentDesc("");
					return new MessageResponse("200", "add", enrollment);
				} else {
					enrollment = enrollmentsHibernateDAO.modifyEnrollmentExists(enrolmentsForm);
					enrollment.setCourseEnrollmentDesc("");
					return new MessageResponse("200", "update", enrollment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

	@GetMapping("/enrollmentDetails")
	public String enrollmentDetailsForm(Model model) {
		try {
			EnrollmentDetailsForm enrollmentDetailsForm = new EnrollmentDetailsForm();
			enrollmentDetailsForm.setEnrollmentDetailId(0L);
			model.addAttribute("enrollmentDetailsForm", enrollmentDetailsForm);
			// Get list enrollments
			List<EnrollmentsRaw> lstEnrollments = enrollmentsRawHibernateDAO.getListEnrollmentRaw();
			model.addAttribute("Enrollments", lstEnrollments);
			// Get list course
			List<CoursesRaw> lstCourses = coursesRawHibernateDAO.getAllCoursesRaw();
			model.addAttribute("Courses", lstCourses);
			//Get list enrollmentDetail
			List<Map<String, Object>> lst = enrollmentDetailsDAO.getListEnrollmentDetails();
			model.addAttribute("EnrollmentDetails", lst);
			// set breadcrumb
			model.addAttribute("breadcrumb", applicationGeneralDAO.getBreadcrumbByModuleName("enrollmentdetails"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "views/bkcourse/enrollmentDetails";
	}

	@PostMapping(value = "/enrollmentDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MessageResponse saveEnrollmentDetailsFormAjax(Model model,
			@ModelAttribute("enrollmentDetailsForm") @Validated EnrollmentDetailsForm enrollmentDetailsForm,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				return new MessageResponse("500", "errors", errors);
			} else {
				EnrollmentDetails enrollmentDetail = null;
				Long enrollmentDetailId = enrollmentDetailsForm.getEnrollmentDetailId();
				if (enrollmentDetailId == null || enrollmentDetailId == 0) {
					enrollmentDetail = enrollmentDetailsHibernateDAO.createNewEnrollmentDetail(enrollmentDetailsForm);
					return new MessageResponse("200", "add", enrollmentDetail);
				} else {
					enrollmentDetail = enrollmentDetailsHibernateDAO.modifyEnrollmentDetailExists(enrollmentDetailsForm);
					return new MessageResponse("200", "update", enrollmentDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResponse("500", e.getMessage());
		}
	}

}
