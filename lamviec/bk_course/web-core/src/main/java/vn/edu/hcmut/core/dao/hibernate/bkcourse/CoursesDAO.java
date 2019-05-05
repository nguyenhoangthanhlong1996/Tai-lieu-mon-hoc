package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.form.bkcourse.CoursesForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Courses;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategories;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICourses;
import vn.edu.hcmut.core.utilities.DateUtils;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("coursesHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class CoursesDAO extends HibernateGenericDao<Courses, Long> implements ICourses {
	@Autowired
	private ICategories categoriesHibernateDAO;
	
	@Autowired
	public CoursesDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Courses createNewCourse(CoursesForm form) throws Exception {
		try {
			Courses course = new Courses();
			course.setCourseId(0L);
			course.setCourseName(form.getCourseName());
			course.setCourseNbr(form.getCourseNbr());
			course.setCourseLearningHour(form.getCourseLearningHour());
			course.setCoursePrice(Double.valueOf(form.getCoursePrice()));
			course.setCourseEnable(form.isCourseEnable());
			course.setCourseDesc(form.getCourseDesc());
			course.setCreatedTime(DateUtils.dateTimeToString());
			course.setCourseCategory(categoriesHibernateDAO.findCategoryById(form.getCourseCategory()));
			Long corseId = this.save(course);
			course.setCourseId(corseId);
			return course;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean checkExistCourseNbr(Long courseId, String courseNbr) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("ne");
				con.setValue1(courseId);
				cons.add(con);
			}
			{
				ConditionReport con = new ConditionReport();
				con.setCol("courseNbr");
				con.setOperator("eq");
				con.setValue1(courseNbr);
				cons.add(con);
			}
			List<Courses> lstCourses = this.findByCondition(Courses.class, cons, 0, 0);
			if (lstCourses != null && lstCourses.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Courses modifyCourseExists(CoursesForm form) throws Exception {
		try {
			Courses course = new Courses();
			course.setCourseId(form.getCourseId());
			course.setCourseName(form.getCourseName());
			course.setCourseNbr(form.getCourseNbr());
			course.setCourseLearningHour(form.getCourseLearningHour());
			course.setCoursePrice(Double.valueOf(form.getCoursePrice()));
			course.setCourseEnable(form.isCourseEnable());
			course.setCourseDesc(form.getCourseDesc());
			course.setCreatedTime(DateUtils.dateTimeToString());
			course.setCourseCategory(categoriesHibernateDAO.findCategoryById(form.getCourseCategory()));
			this.update(course);
			return course;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Courses findCourseById(Long id) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("eq");
				con.setValue1(id);
				cons.add(con);
			}
			List<Courses> lstCourses = this.findByCondition(Courses.class, cons, 0, 0);
			if (lstCourses != null && lstCourses.size() > 0) {
				Courses obj = lstCourses.get(0);
				Long categoryId = obj.getCourseCategory().getCategoryId();
				Categories category = new Categories();
				category.setCategoryId(categoryId);
				obj.setCourseCategory(category);
				return obj;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Long removeCourseExists(Long id) throws Exception {
		try {
			this.delete(id);
			return id;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Courses> getAllCourses() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
