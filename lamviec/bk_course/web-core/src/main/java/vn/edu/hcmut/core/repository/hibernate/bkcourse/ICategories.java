package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import java.util.List;

import vn.edu.hcmut.core.form.bkcourse.CategoriesForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;

public interface ICategories {
	Categories createNewCategory(CategoriesForm obj) throws Exception;
	Categories modifyCategoryExists(CategoriesForm obj) throws Exception;
	Categories findCategoryById(Long categoryId) throws Exception;
	List<Categories> getAllCategories() throws Exception;
	Long removeCategoryExists(Long categoryId) throws Exception;
}
