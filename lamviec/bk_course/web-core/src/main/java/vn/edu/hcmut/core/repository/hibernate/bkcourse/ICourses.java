package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import java.util.List;

import vn.edu.hcmut.core.form.bkcourse.CoursesForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Courses;

public interface ICourses {
	Courses createNewCourse(CoursesForm form) throws Exception;
	boolean checkExistCourseNbr(Long courseId, String courseNbr) throws Exception;
	Courses modifyCourseExists(CoursesForm form) throws Exception;
	List<Courses> getAllCourses() throws Exception;
	List<Courses> getAllCoursesOpended() throws Exception;
	List<Courses> getAllCoursesNotOpened() throws Exception;
	Courses findCourseById(Long id) throws Exception;
	List<Courses> findCoursesByCategoryId(Long id) throws Exception;
	List<Courses> findCoursesByCategoryIdExceptCourseId(Long categoryId, Long courseId) throws Exception;
	Integer countCourseByCategoryId(Long id) throws Exception;
	Long removeCourseExists(Long id) throws Exception;
}
