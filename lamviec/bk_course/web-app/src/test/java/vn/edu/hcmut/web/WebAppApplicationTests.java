package vn.edu.hcmut.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.edu.hcmut.core.dao.GeneralDAO;
import vn.edu.hcmut.core.dao.hibernate.bkcourse.CategoriesDAO;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.model.hibernate.bkcourse.CategoriesRaw;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Courses;
import vn.edu.hcmut.core.model.hibernate.bkcourse.CoursesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategories;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategoriesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICourses;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAppApplicationTests {

	@Autowired
	ICategoriesRaw categoriesRawDAO;
	@Autowired
	ICourses coursesDAO;


	@Test
	public void contextLoads() {
		try {
			List<Courses> list = coursesDAO.findCoursesByCategoryIdExceptCourseId(Long.valueOf(1),Long.valueOf(2));
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
